package controller.gestioneUtenza;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class LoginServletTest extends LoginServlet {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    LoginServlet servlet;
    static FacadeDAO service;
    static Cittadino cittadino;
    static Impiegato impiegato;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new LoginServlet();
    }

    @BeforeAll
    static void setUpAll() {
        service = new FacadeDAO();
        impiegato = new Impiegato("abc@scafati.it", "MAT365",
                "Cityzen10!", "MPLGEL80A09H387H", "Pippo", "Pippo", "mercato", 1, "Fisciano", 0, 0);
        service.inserisciImpiegato(impiegato);
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "Cityzen10!",
                "via roma", 3, "Fisciano", "cattaneo@gmail.com", 0, 0);
        service.registraCittadino(cittadino);
    }

    @Test
    void testEmailVuoto(){
        request.addParameter("email", "");
        request.addParameter("pwd", "Password00");
        MyServletException exception = assertThrows(MyServletException.class, () -> {
            servlet.doPost(request, response);});
        assertEquals("Controlla la correttezza campi", exception.getMessage());
    }

    @Test
    void testEmailNull(){
        request.addParameter("email", (String) null);
        request.addParameter("pwd", "Password00");
        MyServletException exception = assertThrows(MyServletException.class, () -> {
            servlet.doPost(request, response);});
        assertEquals("Si è verificato un errore", exception.getMessage());
    }

    @Test
    void testPwdVuoto(){
        request.addParameter("email", "alessio@scafati.it");
        request.addParameter("pwd", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {
            servlet.doPost(request, response);});
        assertEquals("Controlla la correttezza campi", exception.getMessage());
    }

    @Test
    void testPwdNull(){
        request.addParameter("email", "alessio@scafati.it");
        request.addParameter("pwd", (String) null);
        MyServletException exception = assertThrows(MyServletException.class, () -> {
            servlet.doPost(request, response);});
        assertEquals("Si è verificato un errore", exception.getMessage());
    }

    @Test
    void testEmailErrata(){
        request.addParameter("email", "alessioscafatiit");
        request.addParameter("pwd", "Password00");
        MyServletException exception = assertThrows(MyServletException.class, () -> {
            servlet.doPost(request, response);});
        assertEquals("Controlla la correttezza campi", exception.getMessage());
    }

    @Test
    void testPwdLunghezzaErrata(){
        request.addParameter("email", "alessio@scafati.it");
        request.addParameter("pwd", "passw");
        MyServletException exception = assertThrows(MyServletException.class, () -> {
            servlet.doPost(request, response);});
        assertEquals("Controlla la correttezza campi", exception.getMessage());
    }

    @Test
    void testPwdFormatoErrato(){
        request.addParameter("email", "alessio@scafati.it");
        request.addParameter("pwd", "password");
        MyServletException exception = assertThrows(MyServletException.class, () -> {
            servlet.doPost(request, response);});
        assertEquals("Controlla la correttezza campi", exception.getMessage());
    }

    @Test
    void testImpiegatoNull(){
        request.addParameter("email", "prova@scafati.it");
        request.addParameter("pwd", "Prova123");
        MyServletException exception = assertThrows(MyServletException.class, () -> {
            servlet.doPost(request, response);});
        assertEquals("Email o password errati", exception.getMessage());
    }

    @Test
    void testImpiegatoPass(){
        request.addParameter("email", "abc@scafati.it");
        request.addParameter("pwd", "Cityzen10!");
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }

    @Test
    void testCittadinoNull(){
        request.addParameter("email", "prova@gmail.com");
        request.addParameter("pwd", "Prova123");
        MyServletException exception = assertThrows(MyServletException.class, () -> {
            servlet.doPost(request, response);});
        assertEquals("Email o password errati", exception.getMessage());
    }

    @Test
    void testCittadinoPass(){
        request.addParameter("email", "cattaneo@gmail.com");
        request.addParameter("pwd", "Cityzen10!");
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }

    @AfterAll
    public static void clearDB(){
        try {
            service.eliminaImpiegato(impiegato.getMatricola());
            service.eliminaCittadino(cittadino.getCF());
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }
}