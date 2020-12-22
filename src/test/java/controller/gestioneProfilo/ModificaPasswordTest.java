package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class ModificaPasswordTest extends ModificaPassword {
    ModificaPassword servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    static Cittadino cittadino;
    static FacadeDAO service;

    @BeforeEach
    void setUp() {
        servlet = new ModificaPassword();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
        //Password del cittadino: "Password1!"
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        service = new FacadeDAO();
        service.registraCittadino(cittadino);
    }

    @Test
    void testPassVecchiaNonCorrispondente() {
        request.setParameter("oldPass","password1");
        request.getSession().setAttribute("Cittadino", cittadino);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La vecchia password non corrisponde con la password attuale"
                ,exception.getMessage());
    }

    @Test
    void testLunghezzaNuovaPassMinore8() {
        request.setParameter("oldPass","Password1!");
        request.setParameter("newPass","passw1");
        request.getSession().setAttribute("Cittadino", cittadino);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Le due password nuove non rispettano il formato (Almeno 8 caratteri, 1 " +
                        "lettera maiuscola, 1 minuscola, 1 numero ed 1 carattere speciale"
                ,exception.getMessage());
    }

    @Test
    void testFormatoNuovePassNonCorretto() {
        request.setParameter("oldPass","Password1!");
        request.setParameter("newPass","password!");
        request.setParameter("newPass2","password!");
        request.getSession().setAttribute("Cittadino", cittadino);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Le due password nuove non rispettano il formato (Almeno 8 caratteri, 1 " +
                        "lettera maiuscola, 1 minuscola, 1 numero ed 1 carattere speciale"
                ,exception.getMessage());
    }

    @Test
    void testFormatoNuovePassNonCorrispondenti() {
        request.setParameter("oldPass","Password1!");
        request.setParameter("newPass","Password4!");
        request.setParameter("newPass2","password!");
        request.getSession().setAttribute("Cittadino", cittadino);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Le password nuove non corrispondono"
                ,exception.getMessage());
    }

    @Test
    void testModificaPassword() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        request.setParameter("oldPass","Password1!");
        request.setParameter("newPass","Password4!");
        request.setParameter("newPass2","Password4!");
        request.getSession().setAttribute("Cittadino", cittadino);
        assertDoesNotThrow(() -> {servlet.doPost(request,response);});

        //controllo se la nuova password del cittadino è quella che ho settato
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update("Password4!".getBytes("utf8"));
        String sha1NewPassword = String.format("%040x", new BigInteger(1, digest.digest()));

        assertEquals(service.getCittadinoByCf(cittadino.getCF()).getPwd(),sha1NewPassword);
    }

    @AfterAll
    public static void clearDB(){
        try {
            service.eliminaCittadino(cittadino.getCF());
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }
}