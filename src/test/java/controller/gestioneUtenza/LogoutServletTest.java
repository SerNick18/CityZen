package controller.gestioneUtenza;

import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LogoutServletTest extends LogoutServlet {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    LogoutServlet logoutServlet;
    MockHttpSession session;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        logoutServlet = new LogoutServlet();
    }
    @Test
    void testLogoutCittadinoNull() {
        request.getSession().setAttribute("Cittadino", null);
        String message = "Non sei autenticato";
        MyServletException exception = assertThrows(MyServletException.class, () -> {logoutServlet.doGet(request, response);});
        assertEquals(message, exception.getMessage());
    }
    @Test
    void testLogoutCittadinoOk() throws ServletException, IOException {
        request.getSession().setAttribute("Cittadino", new Cittadino("FQSGNN97L19B973R","Giovanni",
                "Fresco","Prova123","Roma",4,
                "Salerno","fresco@gmail.com",0,0));
        logoutServlet.doPost(request,response);
        assertNotEquals(request.getSession().getAttribute("Cittadino"), new Cittadino());
    }
    @Test
    void testLogoutImpiegatoNull() {
        request.getSession().setAttribute("Impiegato", null);
        String message = "Non sei autenticato";
        MyServletException exception = assertThrows(MyServletException.class, () -> {logoutServlet.doGet(request, response);});
        assertEquals(message, exception.getMessage());
    }
    @Test
    void testLogoutImpiegatoOk() throws ServletException, IOException {
        request.getSession().setAttribute("Impiegato", new Impiegato("asd@scafati.it","MAT10",
                "Pippotto8.","NPLPQL45G10A509L" ,"Giovanni","Fresco","Roma",4,
                "Salerno",0,0));
        logoutServlet.doPost(request,response);
        assertNotEquals(request.getSession().getAttribute("Cittadino"), new Cittadino());
    }
}
