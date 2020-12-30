package controller.operazioni_cittadino;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EliminaSegnalazioneTest extends EliminaSegnalazione {
    EliminaSegnalazione servlet;
    MockHttpServletRequest req;
    MockHttpServletResponse resp;
    static Segnalazione segnalazione;
    static FacadeDAO service;

    @BeforeEach
    void setUp() {
        servlet = new EliminaSegnalazione();
        req = new MockHttpServletRequest();
        resp = new MockHttpServletResponse();
        segnalazione = new Segnalazione(18, "Capuoldo", 10, 0, 0, "inoltrata", new Date(), "Buca in leoano",
                "Buca al centro della carreggiata", "immagine2.jpg", null, 0);
    }

    @Test
    void testNessunCittadinoLoggato(){
        String message = "Effettua il login per visualizzare questa pagina";
        req.getSession().setAttribute("Cittadino", null);
        MyServletException exception = assertThrows(MyServletException.class, () -> {servlet.doPost(req, resp);});
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testSegnalazionePresente(){
        Cittadino cittadino = service.getCittadinoByCf("LSSFDL00C01A509T");
        req.setParameter("id", segnalazione.getId()+"");
        req.getSession().setAttribute("Cittadino", cittadino);
        try {
            servlet.doPost(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNull(service.getSegnalazioneById(segnalazione.getId()));
    }

    @AfterAll
    public static void clearDB(){
        try {
            service.eliminaSegnalazione(segnalazione.getId());
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }
}