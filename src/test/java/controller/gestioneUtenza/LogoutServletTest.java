package controller.gestioneUtenza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LogoutServletTest extends LogoutServlet {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    LogoutServlet logoutServlet;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        logoutServlet = new LogoutServlet();
    }

    @Test
    void testLogout() {
        HttpSession session = request.getSession();
        String message = "Non sei autenticato";
        MyServletException exception = assertThrows(MyServletException.class, () -> {logoutServlet.doPost(request, response);});
        assertEquals("Controlla correttezza campi", exception.getMessage());
    }
}
