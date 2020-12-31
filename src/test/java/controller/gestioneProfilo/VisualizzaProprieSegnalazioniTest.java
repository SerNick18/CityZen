package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.modelObjects.Cittadino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

public class VisualizzaProprieSegnalazioniTest extends VisualizzaProprieSegnalazioni{
    VisualizzaProprieSegnalazioni servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new VisualizzaProprieSegnalazioni();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.getSession().setAttribute("Cittadino", new Cittadino());
    }

    @Test
    void TestCittadinoNull() {
        request.getSession().removeAttribute("Cittadino");
        MyServletException exception=assertThrows(MyServletException.class, ()->{servlet.doGet(request, response);});
        assertEquals(exception.getMessage(), "non sei loggato");
    }

    @Test
    void TestPass() {
        assertDoesNotThrow(()->{servlet.doGet(request, response);});
    }
}
