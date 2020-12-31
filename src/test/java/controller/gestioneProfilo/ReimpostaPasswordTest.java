package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
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

public class ReimpostaPasswordTest extends ReimpostaPassword{

    ReimpostaPassword servlet;
    MockHttpServletRequest req;
    MockHttpServletResponse resp;
    static Cittadino cittadino;
    static Impiegato impiegato;
    static FacadeDAO service;

    @BeforeEach
    void  setUp(){
        servlet = new ReimpostaPassword();
        req = new MockHttpServletRequest();
        resp = new MockHttpServletResponse();
        req.addParameter("provenienza","reimposta-password");
    }

    @BeforeAll
    public static void setUpAll(){
        service = new FacadeDAO();
        cittadino = new Cittadino("SBAFNC98T26H700G","Giovanni","Fresco","Prova123","Roma",123,"Scafati","ghy98@gmail.com",0,0);
        service.registraCittadino(cittadino);
        impiegato = new Impiegato("asdf@scafati.it","155","Password1!","AGBC67DTU87YUHS6","Antonio","Sabato","europa",12,"Scafati",0,0);
        service.inserisciImpiegato(impiegato);
    }

    @Test
    void testEmailNull(){
        req.addParameter("utente","cittadino");
        req.addParameter("email", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {servlet.doPost(req, resp);});
        assertEquals("Inserisci un email valida", exception.getMessage());
    }

    @Test
    void testEmailNotFound(){
        req.addParameter("utente","cittadino");
        req.addParameter("email", "abcd@gmail.com");
        req.addParameter("pwd", "Prova124");
        req.addParameter("pwd2","Prova124");
        MyServletException exception = assertThrows(MyServletException.class, () -> {servlet.doPost(req, resp);});
        assertEquals("Non sei registrato", exception.getMessage());
    }

    @Test
    void testPwdNull(){
        req.addParameter("utente","cittadino");
        req.addParameter("email", "ghy98@gmail.com");
        req.addParameter("pwd", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {servlet.doPost(req, resp);});
        assertEquals("La password deve contenere almeno 8 caratteri, "
                + "almeno una lettera maiuscola, "
                + "una lettera minuscola,\n"
                + " * un numero ed un carattere speciale.", exception.getMessage());
    }

    @Test
    void testPwd2Null(){
        req.addParameter("utente","cittadino");
        req.addParameter("email", "ghy98@gmail.com");
        req.addParameter("pwd", "Prova123");
        req.addParameter("pwd2","");
        MyServletException exception = assertThrows(MyServletException.class, () -> {servlet.doPost(req, resp);});
        assertEquals("Le password non corrispondono", exception.getMessage());
    }

    @Test
    void testPwd2NotMatch(){
        req.addParameter("utente","cittadino");
        req.addParameter("email", "ghy98@gmail.com");
        req.addParameter("pwd", "Prova123");
        req.addParameter("pwd2","asdf");
        MyServletException exception = assertThrows(MyServletException.class, () -> {servlet.doPost(req, resp);});
        assertEquals("Le password non corrispondono", exception.getMessage());
    }

    @Test
    void testPasswordModified(){
        req.addParameter("utente","cittadino");
        req.addParameter("email", "ghy98@gmail.com");
        req.addParameter("pwd", "Prova124");
        req.addParameter("pwd2","Prova124");
        assertDoesNotThrow(() -> {servlet.doPost(req, resp);});
    }

    @Test
    void testEmailNotFoundImp(){
        req.addParameter("utente","impiegato");
        req.addParameter("email", "qwer@scafati.it");
        req.addParameter("pwd", "Prova124");
        req.addParameter("pwd2","Prova124");
        MyServletException exception = assertThrows(MyServletException.class, () -> {servlet.doPost(req, resp);});
        assertEquals("Non sei registrato", exception.getMessage());
    }

    @Test
    void testPasswordModifiedImp(){
        req.addParameter("utente","impiegato");
        req.addParameter("email", "asdf@scafati.it");
        req.addParameter("pwd", "Prova124!");
        req.addParameter("pwd2","Prova124!");
        assertDoesNotThrow(() -> {servlet.doPost(req, resp);});
    }

    @AfterAll
    public static void clearDB() {
        try {
            service.eliminaCittadino(cittadino.getCF());
            service.eliminaImpiegato(impiegato.getMatricola());
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }
}
