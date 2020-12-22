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
        request.addParameter("email", "");
        request.addParameter("pwd", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {loginServlet.doPost(request, response);});
        assertEquals("Controlla correttezza campi", exception.getMessage());
    }

    @Test
    void testEmailNull(){
        request.addParameter("email", "");
        request.addParameter("pwd", "Prova123");
        MyServletException exception = assertThrows(MyServletException.class, () -> {loginServlet.doPost(request, response);});
        assertEquals("Controlla correttezza campi", exception.getMessage());
    }

    @Test
    void testPwdNull(){
        request.addParameter("email", "prova@gmail.com");
        request.addParameter("pwd", "");
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

    @Test
    void testConImpiegato(){
        //per passare il test ci deve stare questo record nel db
        request.addParameter("email", "ugo@scafati.it");
        request.addParameter("pwd", "Prova123");
        assertDoesNotThrow(() -> {loginServlet.doPost(request, response);});
    }

    @Test
    void testConCittadino(){
        //per passare il test ci deve stare questo record nel db
        request.addParameter("email", "giuseppe@gmail.com");
        request.addParameter("pwd", "Prova123");
        assertDoesNotThrow(() -> {loginServlet.doPost(request, response);});
    }
}