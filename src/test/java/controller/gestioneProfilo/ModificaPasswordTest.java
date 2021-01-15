package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
import controller.operazioni_impiegato.ApprovaSegnalazione;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;
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
    static Impiegato impiegato;
    static FacadeDAO service;

    @BeforeEach
    void setUpEach () {
        servlet = new ModificaPassword();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
        impiegato = new Impiegato("abc@scafati.it","MAT365",
                "12278e5083958deac617de4865b7513308c0553e","MPLGEL80A09H387H","Pippo","Pippo","mercato",1,"Fisciano",0,0);
        request.getSession().setAttribute("Impiegato", impiegato);
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        request.getSession().setAttribute("Cittadino", cittadino);
    }

    @BeforeAll
    static void setUpAll() {
        service = new FacadeDAO();
        impiegato = new Impiegato("abc@scafati.it", "MAT365",
                "Cityzen1!", "MPLGEL80A09H387H", "Pippo", "Pippo", "mercato", 1, "Fisciano", 0, 0);
        service.inserisciImpiegato(impiegato);
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        service.registraCittadino(cittadino);
    }
    @Test
    void TestNessunUtenteLoggato() {
        request.getSession().removeAttribute("Cittadino");
        request.getSession().removeAttribute("Impiegato");
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }
    @Test
    void TestDueUtentiInSessione() {
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }
    @Test
    void testCittadinoPassVecchiaNonCorrispondente() {
        request.setParameter("oldPass","password1");
        request.getSession().setAttribute("Cittadino", cittadino);
        request.getSession().removeAttribute("Impiegato");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La vecchia password non corrisponde con la password attuale"
                ,exception.getMessage());
    }
    @Test
    void testImpiegatoPassVecchiaNonCorrispondente() {
        request.setParameter("oldPass","password1");
        request.getSession().removeAttribute("Cittadino");
        request.getSession().setAttribute("Impiegato", impiegato);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La vecchia password non corrisponde con la password attuale"
                ,exception.getMessage());
    }

    @Test
    void testCittadinoLungNewPwdMin8() {
        request.setParameter("oldPass","Password1!");
        request.setParameter("newPass","passw1");
        request.getSession().setAttribute("Cittadino", cittadino);
        request.getSession().removeAttribute("Impiegato");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Le due password nuove non rispettano il formato (Almeno 8 caratteri, 1 " +
                        "lettera maiuscola, 1 minuscola, 1 numero"
                ,exception.getMessage());
    }
    @Test
    void testImpiegatoLungNewPwdMin8() {
        request.setParameter("oldPass","Cityzen1!");
        request.setParameter("newPass","passw1");
        request.getSession().removeAttribute("Cittadino");
        request.getSession().setAttribute("Impiegato", impiegato);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Le due password nuove non rispettano il formato (Almeno 8 caratteri, 1 " +
                        "lettera maiuscola, 1 minuscola, 1 numero ed 1 carattere speciale"
                ,exception.getMessage());
    }

    @Test
    void testCittadinoFormatoNuovePassNonCorretto() {
        request.setParameter("oldPass","Password1!");
        request.setParameter("newPass","password!");
        request.setParameter("newPass2","password!");
        request.getSession().setAttribute("Cittadino", cittadino);
        request.getSession().removeAttribute("Impiegato");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Le due password nuove non rispettano il formato (Almeno 8 caratteri, 1 " +
                        "lettera maiuscola, 1 minuscola, 1 numero"
                ,exception.getMessage());
    }
    @Test
    void testImpiegatoFormatoNewPwdNonCorretto() {
        request.setParameter("oldPass","Cityzen1!");
        request.setParameter("newPass","password!");
        request.setParameter("newPass2","password!");
        request.getSession().removeAttribute("Cittadino");
        request.getSession().setAttribute("Impiegato", impiegato);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Le due password nuove non rispettano il formato (Almeno 8 caratteri, 1 " +
                        "lettera maiuscola, 1 minuscola, 1 numero ed 1 carattere speciale"
                ,exception.getMessage());
    }

    @Test
    void testCittadinoFormatoNewPwdNonCorrispondenti() {
        request.setParameter("oldPass","Password1!");
        request.setParameter("newPass","Password4!");
        request.setParameter("newPass2","password!");
        request.getSession().setAttribute("Cittadino", cittadino);
        request.getSession().removeAttribute("Impiegato");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Le password nuove non corrispondono"
                ,exception.getMessage());
    }
    @Test
    void testImpiegatoFormatoNewPwdNonCorrispondenti() {
        request.setParameter("oldPass","Cityzen1!");
        request.setParameter("newPass","Password4!");
        request.setParameter("newPass2","password!");
        request.getSession().removeAttribute("Cittadino");
        request.getSession().setAttribute("Impiegato", impiegato);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Le password nuove non corrispondono"
                ,exception.getMessage());
    }

    @Test
    void testCittadinoModificaPassword() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        request.setParameter("oldPass","Password1!");
        request.setParameter("newPass","Password4!");
        request.setParameter("newPass2","Password4!");
        request.getSession().setAttribute("Cittadino", cittadino);
        request.getSession().removeAttribute("Impiegato");
        assertDoesNotThrow(() -> {servlet.doPost(request,response);});
    }
    @Test
    void testImpiegatoModificaPassword() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        request.setParameter("oldPass","Cityzen1!");
        request.setParameter("newPass","Password4!");
        request.setParameter("newPass2","Password4!");
        request.getSession().removeAttribute("Cittadino");
        request.getSession().setAttribute("Impiegato", impiegato);
        assertDoesNotThrow(() -> {servlet.doPost(request,response);});
    }
    @Test
    void TestParameterProvenienzaPass() {
        request.setParameter("provenienza", "profilo");
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }
    @Test
    void TestParameterProvenienzaDiversoDaProfilo() {
        request.setParameter("provenienza", "modifica");
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }
    @Test
    void TestParameterProvenienzaNull() {
        request.setParameter("provenienza", null+"");
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }

    @AfterAll
    public static void clearDB(){
        try {
            service.eliminaImpiegato(impiegato.getMatricola());
            service.eliminaCittadino(cittadino.getCF());
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }
}