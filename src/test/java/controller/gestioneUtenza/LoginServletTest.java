package controller.gestioneUtenza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class LoginServletTest extends LoginServlet {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    LoginServlet loginServlet;
    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        loginServlet = new LoginServlet();
    }

    @Test
    void testParamNull() {
        request.setAttribute("email", null);
        request.setAttribute("pwd", null);
        MyServletException exception = assertThrows(MyServletException.class, () -> {loginServlet.doPost(request, response);});
        assertEquals("Si è verificato un errore", exception.getMessage());
    }

    @Test
    void testEmailNull(){
        request.setAttribute("email", null);
        MyServletException exception = assertThrows(MyServletException.class, () -> {loginServlet.doPost(request, response);});
        assertEquals("Si è verificato un errore", exception.getMessage());
    }

    @Test
    void testPwdNull(){
        request.setAttribute("pwd", null);
        MyServletException exception = assertThrows(MyServletException.class, () -> {loginServlet.doPost(request, response);});
        assertEquals("Si è verificato un errore", exception.getMessage());
    }

}