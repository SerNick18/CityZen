package controller.gestioneProfilo;

import model.gestioneDati.modelObjects.Impiegato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

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
    void testDoGet() {
        req.getSession().setAttribute("Impiegato", new Impiegato());
        req.getSession().setAttribute("Cittadino", null);
        assertDoesNotThrow(()->{servlet.doGet(req, resp);});
    }
}