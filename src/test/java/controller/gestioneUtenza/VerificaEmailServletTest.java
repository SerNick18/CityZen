package controller.gestioneUtenza;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import org.junit.jupiter.api.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class VerificaEmailServletTest extends VerificaEmailServlet {
    VerificaEmailServlet servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    static FacadeDAO service;
    static Cittadino cittadino;

    @BeforeAll
    static void setUpAll(){
        service=new FacadeDAO();
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Fresco", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Scafati","fresco@gmail.com",0,0);
        service.registraCittadino(cittadino);
    }

    @BeforeEach
    void setUp() {
        servlet = new VerificaEmailServlet();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
    }

    @Test
    public void emailOk(){
        request.addParameter("email", "abcdef@gmail.com");
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @Test
    public void emailPatternFail(){
        request.addParameter("email", "abcdef");
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @Test
    public void emailPresenteDB(){
        request.addParameter("email", "fresco@gmail.com");
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @Test
    public void emailNull(){
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @AfterAll
    public static void clearDB(){
        try {
            service.eliminaCittadino(cittadino.getCF());
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }

}