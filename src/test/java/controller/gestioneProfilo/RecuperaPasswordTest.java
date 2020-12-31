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

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RecuperaPasswordTest extends MandaEmail{

    MandaEmail servlet;
    MockHttpServletRequest req;
    MockHttpServletResponse resp;
    static Cittadino cittadino;
    static Impiegato impiegato;
    static FacadeDAO service;

    @BeforeEach
    void  setUp(){
        servlet = new MandaEmail();
        req = new MockHttpServletRequest();
        resp = new MockHttpServletResponse();
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
    void testEmailNull() {
        req.addParameter("email", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {servlet.doPost(req, resp);});
        assertEquals("Inserisci un email valida", exception.getMessage());
    }

    @Test
    void testEmailFormatError() {
        req.addParameter("email","asdfg98");
        MyServletException exception = assertThrows(MyServletException.class, () -> {servlet.doPost(req, resp);});
        assertEquals("Inserisci un email valida", exception.getMessage());
    }

    @Test
    void testEmailCittadinoNotFound() {
        req.addParameter("email","cesco10@gmail.com");
        assertDoesNotThrow( () -> servlet.doPost(req, resp));
    }

    @Test
    void testEmailOK() throws ServletException, IOException {
        req.addParameter("email","ghy98@gmail.com");
        assertDoesNotThrow( () -> servlet.doPost(req, resp));
    }

    @Test
    void testEmailOKImp() throws ServletException, IOException {
        req.addParameter("email","asdf@scafati.it");
        assertDoesNotThrow( () -> servlet.doPost(req, resp));
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
