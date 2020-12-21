package controller.gestioneUtenza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class RegistrazioneServletTest extends RegistrazioneServlet {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    RegistrazioneServlet registrazioneServlet;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        registrazioneServlet = new RegistrazioneServlet();
    }

    @Test //test tutti i parametri sono null
    void testCfNull() {
        request.addParameter("cf", "");
        request.addParameter("nome", "Nicola");
        request.addParameter("cognome", "Serra");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "1");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "nicola@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Codice fiscale errato!", exception.getMessage());
    }
    @Test //test codice fiscale errato - non rispetta pattern
    void testCfErrato() {
        request.addParameter("cf", "FRSGNN97419B963R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "1");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "nicola@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Codice fiscale errato!", exception.getMessage());
    }

    @Test //test Nome non inserito
    void testNomeNull() {
        request.addParameter("cf", "FRSGNN97L19B963R");
        request.addParameter("nome", "");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "1");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "nicola@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Il nome inserito non è valido!", exception.getMessage());
    }

    @Test //test nome errato - non rispetta pattern
    void testNomeErrato() {
        request.addParameter("cf", "FRSGNN97L19B963R");
        request.addParameter("nome", "Giovann4i");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "1");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "nicola@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Il nome inserito non è valido!", exception.getMessage());
    }

    @Test //test cognome non inserito
    void testCognomeNull() {
        request.addParameter("cf", "FRSGNN97L19B963R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "1");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "nicola@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Il cognome inserito non è valido", exception.getMessage());
    }

    @Test //test cognome errato - non rispetta pattern
    void testCognomeErrato() {
        request.addParameter("cf", "FRSGNN97L29B963R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fre4sco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "1");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "nicola@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Il cognome inserito non è valido", exception.getMessage());
    }

    @Test //test email non inserita
    void testEmailNull() {
        request.addParameter("cf", "BRSGNN97L19B963R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "1");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Email errata!", exception.getMessage());
    }

    @Test //test email pattern
    void testEmail() {
        request.addParameter("cf", "SRTNCL91L18B963R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "1");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@gmailcom");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Email errata!", exception.getMessage());
    }

    @Test //test email con dominio @scafati.it
    void testEmailScafati() {
        request.addParameter("cf", "SPRNCL91L18B963R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "1");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@scafati.it");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Email errata!", exception.getMessage());
    }

    @Test
    void testCivicoNull(){
        request.addParameter("cf", "FQSGNN97L19B963R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Inserire un numero civico valido!", exception.getMessage());
    }

    @Test
    void testCivicoNoPattern(){
        request.addParameter("cf", "FQRGNN97L19B963R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "1234");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Inserire un numero civico valido!", exception.getMessage());
    }

    @Test
    void testViaNull(){
        request.addParameter("cf", "FQSGNN97L19B973R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "");
        request.addParameter("civico", "123");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Inserire una via valida!", exception.getMessage());
    }

    @Test
    void testViaNoPattern(){
        request.addParameter("cf", "FQRGNN97L19B953R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma4");
        request.addParameter("civico", "123");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Inserire una via valida!", exception.getMessage());
    }

    @Test
    void testCittaNull(){
        request.addParameter("cf", "FQSGNN97M19B973R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "123");
        request.addParameter("citta", "");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Inserire il nome di una città valido!", exception.getMessage());
    }

    @Test
    void testCittaNoPattern(){
        request.addParameter("cf", "FQRGNN87L19B953R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "123");
        request.addParameter("citta", "Sca fati");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals("Inserire il nome di una città valido!", exception.getMessage());
    }

    @Test
    void testPassword1Null(){
        String message="La password deve contenere almeno 8 caratteri, almeno una lettera maiuscola," +
                " una lettera minuscola,\n             * un numero ed un carattere speciale.";
        request.addParameter("cf", "FQRGNN87C19B953R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "123");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testPassword1NoPattern1(){
        String message="La password deve contenere almeno 8 caratteri, almeno una lettera maiuscola," +
                " una lettera minuscola,\n             * un numero ed un carattere speciale.";
        request.addParameter("cf", "FQRGNN87C19B953R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "ciao");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "123");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testPassword1NoPattern2(){
        String message="La password deve contenere almeno 8 caratteri, almeno una lettera maiuscola," +
                " una lettera minuscola,\n             * un numero ed un carattere speciale.";
        request.addParameter("cf", "FQRGNN87C19B953R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Ciao");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "123");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testPassword1NoPattern3(){
        String message="La password deve contenere almeno 8 caratteri, almeno una lettera maiuscola," +
                " una lettera minuscola,\n             * un numero ed un carattere speciale.";
        request.addParameter("cf", "FQRGNN87C19B953R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "ciao1");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "123");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testPassword1NoPattern4(){
        String message="La password deve contenere almeno 8 caratteri, almeno una lettera maiuscola," +
                " una lettera minuscola,\n             * un numero ed un carattere speciale.";
        request.addParameter("cf", "FQRGNN87C19B953R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "ciaone11");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "123");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testPassword1NoPattern5(){
        String message="La password deve contenere almeno 8 caratteri, almeno una lettera maiuscola," +
                " una lettera minuscola,\n             * un numero ed un carattere speciale.";
        request.addParameter("cf", "FQRGNN87C19B953R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "ciaoneeee");
        request.addParameter("pwd2", "Prova123");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "123");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testPassword2NoMatch(){
        String message="Le due password non corrispondono";
        request.addParameter("cf", "FQRGPN87C19B953R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova124");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "123");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doPost(request, response);});
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testPassword2NoMatch2(){
        String message="Le due password non corrispondono";
        request.addParameter("cf", "FQVGPN87C19B953R");
        request.addParameter("nome", "Giovanni");
        request.addParameter("cognome", "Fresco");
        request.addParameter("pwd1", "Prova123");
        request.addParameter("pwd2", "Prova124");
        request.addParameter("via", "Roma");
        request.addParameter("civico", "123");
        request.addParameter("citta", "Scafati");
        request.addParameter("email", "fresco@gmail.com");
        MyServletException exception = assertThrows(MyServletException.class, () -> {registrazioneServlet.doGet(request, response);});
        assertEquals(message, exception.getMessage());
    }
}