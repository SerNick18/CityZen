package controller.gestioneUtenza;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RegistrazioneServletTest extends RegistrazioneServlet {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    RegistrazioneServlet registrazioneServlet;
    static Cittadino cittadino;
    static Cittadino cittadino2;
    static FacadeDAO service;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        registrazioneServlet = new RegistrazioneServlet();
        service = new FacadeDAO();
        cittadino = new Cittadino("SBAFNC98T26H703S","Francesco","Sabia","Password1!","via europa",12,"Salerno","abcd98@gmail.com",0,0);
    }

    @BeforeAll
    public static void setUp_V2(){
        service = new FacadeDAO();
        cittadino2 = new Cittadino("SBAFNC98T26H700M","Francesco","Sabia","Password1!","via europa",12,"Salerno","asdf00@gmail.com",0,0);
        service.registraCittadino(cittadino2);
    }

    @Test //test tutti i parametri sono null
    void testAllNull() {
        request.addParameter("nome", "");
        request.addParameter("cognome", "");
        request.addParameter("email", "");
        request.addParameter("cf", "");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Il nome inserito non è valido!", exception.getMessage());
    }
    @Test //test nome errato - non rispetta pattern
    void testNomeFormatError() {
        request.addParameter("nome", "F1");
        request.addParameter("cognome", "");
        request.addParameter("email", "");
        request.addParameter("cf", "");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Il nome inserito non è valido!", exception.getMessage());
    }

    @Test //test cognome non inserito
    void testCognomeNull() {
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "");
        request.addParameter("email", "");
        request.addParameter("cf", "");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Il cognome inserito non è valido", exception.getMessage());
    }

    @Test //test cognome errato - non rispetta pattern
    void testCognomeFormatError() {
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "S1");
        request.addParameter("email", "");
        request.addParameter("cf", "");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Il cognome inserito non è valido", exception.getMessage());
    }

    @Test //test email non inserito
    void testEmailNull() {
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "");
        request.addParameter("cf", "");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Email errata!", exception.getMessage());
    }

    @Test //test email errato - non rispetta pattern
    void testEmailFormatError() {
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "asdf!gmailcom");
        request.addParameter("cf", "");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Email errata!", exception.getMessage());
    }

    @Test
    void testEmailError() {
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "asdf00@gmail.com");
        request.addParameter("cf", "");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Email errata!", exception.getMessage());
    }

    @Test //test cf non inserito
    void testCfNull() {
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Codice fiscale errato!", exception.getMessage());
    }

    @Test //test codice fiscale errore pattern
    void testCfFormatError() {
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "ASDFC98TH");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Codice fiscale errato!", exception.getMessage());
    }

    @Test //test codice fiscale errore pattern
    void testCfFormatError2() {
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "ASDFC98TH7HYO12GYU");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Codice fiscale errato!", exception.getMessage());
    }

    @Test
    void testCfFormatError3(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "ASDF@!23TY4HY67Y");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Codice fiscale errato!", exception.getMessage());
    }

    @Test
    void testCfError(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd8@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H700M");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Codice fiscale errato!", exception.getMessage());
    }

    @Test
    void testViaNull(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H703S");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Inserire una via valida!", exception.getMessage());
    }

    @Test
    void testViaNoPattern(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H703S");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "via12");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Inserire una via valida!", exception.getMessage());
    }

    @Test
    void testCivicoNull(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H703S");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "via europa");
        request.addParameter("civico", "");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Inserire un numero civico valido!", exception.getMessage());
    }

    @Test
    void testCivicoNoPattern(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H703S");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "via europa");
        request.addParameter("civico", "1234");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Inserire un numero civico valido!", exception.getMessage());
    }

    @Test
    void testCivicoNoPattern2(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H703S");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "via europa");
        request.addParameter("civico", "fff");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Inserire un numero civico valido!", exception.getMessage());
    }

    @Test
    void testCittaNull(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H703S");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "via europa");
        request.addParameter("civico", "12");
        request.addParameter("citta", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Inserire il nome di una città valido!", exception.getMessage());
    }

    @Test
    void testCittaNoPattern(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H703S");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "via europa");
        request.addParameter("civico", "12");
        request.addParameter("citta", "F2");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Inserire il nome di una città valido!", exception.getMessage());
    }

    @Test
    void testPassword1NoPattern(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H703S");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "");
        request.addParameter("via", "via europa");
        request.addParameter("civico", "12");
        request.addParameter("citta", "Salerno");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("La password deve contenere almeno 8 caratteri, "
                + "almeno una lettera maiuscola, "
                + "una lettera minuscola,\n"
                + " * un numero ed un carattere speciale.", exception.getMessage());
    }

    @Test
    void testPassword1NoPattern2(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H703S");
        request.addParameter("pwd1", "Passw");
        request.addParameter("pwd2", "");
        request.addParameter("via", "via europa");
        request.addParameter("civico", "12");
        request.addParameter("citta", "Salerno");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("La password deve contenere almeno 8 caratteri, "
                + "almeno una lettera maiuscola, "
                + "una lettera minuscola,\n"
                + " * un numero ed un carattere speciale.", exception.getMessage());
    }

    @Test
    void testPassword1NoPattern3(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H703S");
        request.addParameter("pwd1", "password1");
        request.addParameter("pwd2", "");
        request.addParameter("via", "via europa");
        request.addParameter("civico", "12");
        request.addParameter("citta", "Salerno");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("La password deve contenere almeno 8 caratteri, "
                + "almeno una lettera maiuscola, "
                + "una lettera minuscola,\n"
                + " * un numero ed un carattere speciale.", exception.getMessage());
    }

    @Test
    void testPassword2NoMatch(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H703S");
        request.addParameter("pwd1", "Password1!");
        request.addParameter("pwd2", "");
        request.addParameter("via", "via europa");
        request.addParameter("civico", "12");
        request.addParameter("citta", "Salerno");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Le due password non corrispondono", exception.getMessage());
    }

    @Test
    void testPassword2NoMatch2(){
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H703S");
        request.addParameter("pwd1", "Password1!");
        request.addParameter("pwd2", "passw");
        request.addParameter("via", "via europa");
        request.addParameter("civico", "12");
        request.addParameter("citta", "Salerno");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Le due password non corrispondono", exception.getMessage());
    }


    @Test
    void testRegistrazioneOk() throws ServletException, IOException {
        request.addParameter("nome", "Francesco");
        request.addParameter("cognome", "Sabia");
        request.addParameter("email", "abcd98@gmail.com");
        request.addParameter("cf", "SBAFNC98T26H703S");
        request.addParameter("pwd1", "Password1!");
        request.addParameter("pwd2", "Password1!");
        request.addParameter("via", "via europa");
        request.addParameter("civico", "12");
        request.addParameter("citta", "Salerno");
        registrazioneServlet.doGet(request, response);
        assertEquals(cittadino, request.getSession().getAttribute("Cittadino"));
        try {
            service.eliminaCittadino("SBAFNC98T26H703S");
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }

    @AfterAll
    public static void clearDB(){
        try {
            service.eliminaCittadino(cittadino2.getCF());
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }
}