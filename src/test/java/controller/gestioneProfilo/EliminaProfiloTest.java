package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EliminaProfiloTest extends EliminaProfilo {
    EliminaProfilo servlet;
    MockHttpServletRequest req;
    MockHttpServletResponse resp;

    @BeforeEach
    void setUp() {
        servlet = new EliminaProfilo();
        req = new MockHttpServletRequest();
        resp = new MockHttpServletResponse();
    }
    @Test
    void testNessunCittadinoLoggato() {
        req.getSession().setAttribute("Cittadino", null);
        MyServletException myServletException =
                Assertions.assertThrows(MyServletException.class, () -> {
                    servlet.doPost(req, resp);
                });
        Assertions.assertEquals("Effettua il Login per visualizzare questa pagina!",
                myServletException.getMessage());
    }
    @Test
    void testCittadinoLoggatoPresenteNelDb() {
        FacadeDAO facadeDAO = new FacadeDAO();
        Cittadino cittadino = facadeDAO.getCittadinoByCf("CPNLLD00S19A489D");
        req.getSession().setAttribute("Cittadino", cittadino);
        try {
            servlet.doPost(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertNull(facadeDAO.getCittadinoByCf(cittadino.getCF()));
        facadeDAO.registraCittadino(cittadino);
    }
    @Test
    void testCittadinoLoggatoNonPresenteNelDb() {
        FacadeDAO facadeDAO = new FacadeDAO();
        Cittadino cittadino = new Cittadino("cf", "nome", "cognome", "pwd1", "via",
                3, "citta", "email", 0, 0);
        req.getSession().setAttribute("Cittadino", cittadino);
        MyServletException myServletException =
                Assertions.assertThrows(MyServletException.class, () -> {
                    servlet.doGet(req, resp);
                });
        Assertions.assertEquals("C'è stato un errore nell'eliminazione del profilo",
                myServletException.getMessage());
    }
}