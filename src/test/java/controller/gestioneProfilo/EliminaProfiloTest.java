package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
import controller.operazioni_cittadino.ModificaSegnalazione;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import org.junit.jupiter.api.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EliminaProfiloTest extends EliminaProfilo {
    EliminaProfilo servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    static Cittadino cittadino;
    static FacadeDAO service;

    @BeforeEach
    void setUpEach () {
        servlet = new EliminaProfilo();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        request.getSession().setAttribute("Cittadino", cittadino);
    }

    @BeforeAll
    static void setUpAll() {
        service = new FacadeDAO();
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma", 3, "Fisciano", "cattaneo@gmail.com", 0, 0);
        service.registraCittadino(cittadino);
    }
    @Test
    void testNessunCittadinoLoggato() {
        cittadino = null;
        request.getSession().setAttribute("Cittadino", cittadino);
        MyServletException myServletException =
                assertThrows(MyServletException.class, () -> {
                    servlet.doPost(request, response);
                });
        assertEquals("Effettua il Login per visualizzare questa pagina!",
                myServletException.getMessage());
    }
    @Test
    void testCittadinoLoggatoPresenteNelDb() {
        request.getSession().setAttribute("Cittadino", cittadino);
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
        service.registraCittadino(cittadino);
    }
    @Test
    void testCittadinoLoggatoNonPresenteNelDb() {
        cittadino.setCF("PPPPPP99P99P999P");
        request.getSession().setAttribute("Cittadino", cittadino);
        MyServletException myServletException =
                assertThrows(MyServletException.class, () -> { servlet.doGet(request, response); });
        assertEquals("C'è stato un errore nell'eliminazione del profilo",
                myServletException.getMessage());
        cittadino.setCF("CPNLLD11S19A489D");
    }
    @Test
    void TestPass() {
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
        service.registraCittadino(cittadino);
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