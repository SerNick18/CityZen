package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

public class VisualizzaHomeTest extends VisualizzaHome{
    VisualizzaHome servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new VisualizzaHome();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.getSession().setAttribute("Cittadino", new Cittadino());
        request.getSession().setAttribute("Impiegato", new Impiegato());
    }

    @Test
    void TestCittadinoPass() {
        request.getSession().removeAttribute("Impiegato");
        assertDoesNotThrow(()->{servlet.doGet(request, response);});
    }

    @Test
    void TestImpiegatoPass() {
        request.getSession().removeAttribute("Cittadino");
        assertDoesNotThrow(()->{servlet.doGet(request, response);});
    }

    @Test
    void TestImpiegatoCittadinoDiversiNull() {
        MyServletException exception=assertThrows(MyServletException.class, ()->{servlet.doGet(request, response);});
        assertEquals(exception.getMessage(),"Non sei autorizzato a visualizzare questa pagina, "
                + "effettua prima il login.");
    }

    @Test
    void TestImpiegatoCittadinoNull() {
        request.getSession().removeAttribute("Impiegato");
        request.getSession().removeAttribute("Cittadino");
        MyServletException exception=assertThrows(MyServletException.class, ()->{servlet.doGet(request, response);});
        assertEquals(exception.getMessage(),"Non sei autorizzato a visualizzare questa pagina, "
                + "effettua prima il login.");
    }
}
