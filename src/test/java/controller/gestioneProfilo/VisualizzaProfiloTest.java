package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.RequestDispatcher;

import static org.junit.jupiter.api.Assertions.*;

class VisualizzaProfiloTest extends VisualizzaProfilo {

    VisualizzaProfilo servlet;
    MockHttpServletRequest req;
    MockHttpServletResponse resp;

    @BeforeEach
    void setUp() {
        servlet=new VisualizzaProfilo();
        req=new MockHttpServletRequest();
        resp=new MockHttpServletResponse();
    }

    @Test
    void testConImpiegato() {
        req.getSession().setAttribute("Impiegato", new Impiegato());
        req.getSession().setAttribute("Cittadino", null);
        assertDoesNotThrow(()->{servlet.doGet(req, resp);});
    }

    @Test
    void testConCittadino() {
        req.getSession().setAttribute("Impiegato", null);
        req.getSession().setAttribute("Cittadino", new Cittadino());
        assertDoesNotThrow(()->{servlet.doGet(req, resp);});
    }

    @Test
    void testConNessuno() {
        req.getSession().setAttribute("Impiegato", null);
        req.getSession().setAttribute("Cittadino", null);
        MyServletException exception=assertThrows(MyServletException.class, ()->{servlet.doGet(req, resp);});
        assertEquals(exception.getMessage(), "Effettua il Login per visualizzare questa pagina");
    }

    @Test
    void testConTuttiEDue() {
        req.getSession().setAttribute("Impiegato", new Impiegato());
        req.getSession().setAttribute("Cittadino", new Cittadino());
        assertDoesNotThrow(()->{servlet.doGet(req, resp);});
    }

}