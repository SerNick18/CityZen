package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
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

public class RecuperaPasswordTest extends MandaEmail{

    MandaEmail servlet;
    MockHttpServletRequest req;
    MockHttpServletResponse resp;
    static Cittadino cittadino;
    static FacadeDAO service;

    @BeforeEach
    void  setUp(){
        servlet = new MandaEmail();
        req = new MockHttpServletRequest();
        resp = new MockHttpServletResponse();
        service = new FacadeDAO();
        cittadino = new Cittadino("SBAFNC98T26H703K","Giovanni","Fresco","Prova123","Roma",123,"Scafati","Asdfgh99@gmail.com",0,0);
        service.registraCittadino(cittadino);
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
        req.addParameter("email","Asdfgh99@gmail.com");
        assertDoesNotThrow( () -> servlet.doPost(req, resp));
    }

    @AfterAll
    public static void clearDB() {
        try {
            service.eliminaCittadino("SBAFNC98T26H703K");
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }
}
