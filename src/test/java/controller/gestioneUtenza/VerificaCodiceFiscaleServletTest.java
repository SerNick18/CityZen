package controller.gestioneUtenza;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import org.junit.jupiter.api.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class VerificaCodiceFiscaleServletTest extends VerificaCodiceFiscaleServlet {
    VerificaCodiceFiscaleServlet servlet;
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
        servlet = new VerificaCodiceFiscaleServlet();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
    }

    @Test
    public void cfOk(){
        request.addParameter("cf", "CPNLLD11S19A489D");
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @Test
    public void cfPatternFail(){
        request.addParameter("cf", "1");
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @Test
    public void cfPresenteDB(){
        request.addParameter("cf", "ABCDEF11S19A489D");
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @Test
    public void cfNonPresenteDB(){
        request.addParameter("cf", "CPNLLD11S20B489R");
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @Test
    public void cfNull(){
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