package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.modelObjects.Cittadino;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class VisualizzaEliminaProfiloTest extends VisualizzaEliminaProfilo {
    VisualizzaEliminaProfilo servlet;
    MockHttpServletRequest req;
    MockHttpServletResponse resp;

    @BeforeEach
    void setUp() {
        servlet = new VisualizzaEliminaProfilo();
        req = new MockHttpServletRequest();
        resp = new MockHttpServletResponse();
    }

    @Test
    void testNessunCittadinoLoggato() {
        req.getSession().setAttribute("Cittadino", null);
        MyServletException myServletException =
                Assertions.assertThrows(MyServletException.class, () -> {
            servlet.doGet(req, resp);
        });
        Assertions.assertEquals("Effettua il Login per visualizzare questa pagina!",
                myServletException.getMessage());
    }

    @Test
    void testCittadinoLoggato() {
        req.getSession().setAttribute("Cittadino", new Cittadino());
        Assertions.assertDoesNotThrow(() -> {
                    servlet.doGet(req, resp);
                });
    }
}