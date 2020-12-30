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
    void testEmailNull(){
        request.addParameter("email", "");
        request.addParameter("pwd", "Password00");
        MyServletException exception = assertThrows(MyServletException.class, () -> {loginServlet.doPost(request, response);});
        assertEquals("Controlla correttezza campi", exception.getMessage());
    }

    @Test
    void testEmailErrata(){
        request.addParameter("email", "alessioscafatiit");
        request.addParameter("pwd", "Password00");
        MyServletException exception = assertThrows(MyServletException.class, () -> {loginServlet.doPost(request, response);});
        assertEquals("Controlla correttezza campi", exception.getMessage());
    }

    @Test
    void testPwdLunghezzaErrata(){
        request.addParameter("email", "alessio@scafati.it");
        request.addParameter("pwd", "passw");
        MyServletException exception = assertThrows(MyServletException.class, () -> {loginServlet.doPost(request, response);});
        assertEquals("Controlla correttezza campi", exception.getMessage());
    }

    @Test
    void testPwdFormatoErrato(){
        request.addParameter("email", "alessio@scafati.it");
        request.addParameter("pwd", "password");
        MyServletException exception = assertThrows(MyServletException.class, () -> {loginServlet.doPost(request, response);});
        assertEquals("Controlla correttezza campi", exception.getMessage());
    }

    @Test
    void testImpiegatoNull(){
        request.addParameter("email", "prova@scafati.com");
        request.addParameter("pwd", "Prova123");
        MyServletException exception = assertThrows(MyServletException.class, () -> {loginServlet.doPost(request, response);});
        assertEquals("Email o password errati", exception.getMessage());
    }

    @Test
    void testCittadinoNull(){
        request.addParameter("email", "prova@gmail.com");
        request.addParameter("pwd", "Prova123");
        MyServletException exception = assertThrows(MyServletException.class, () -> {loginServlet.doPost(request, response);});
        assertEquals("Email o password errati", exception.getMessage());
    }


}