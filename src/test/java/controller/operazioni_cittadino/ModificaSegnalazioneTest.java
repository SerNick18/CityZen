package controller.operazioni_cittadino;

import controller.gestioneProfilo.ModificaPassword;
import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.*;
import org.springframework.core.io.FileUrlResource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import javax.servlet.http.Part;
import java.io.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ModificaSegnalazioneTest extends ModificaSegnalazione {
    ModificaSegnalazione servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    static Segnalazione segnInoltrata, segnApprovata;
    static FacadeDAO service;
    static Cittadino cittadino;

    @BeforeEach
    void setUpEach () {
        servlet = new ModificaSegnalazione();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        request.getSession().setAttribute("Cittadino", cittadino);
        request.setParameter("oggetto","Senza luce");
        request.setParameter("descrizione","non vi è corrente da tre giorni");
        request.setParameter("via","roma");
        request.setParameter("civico","12");
    }

    @BeforeAll
    static void setUpAll() {
        service = new FacadeDAO();
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        service.registraCittadino(cittadino);
        segnInoltrata = new Segnalazione();
        segnInoltrata.setVia("roma");
        segnInoltrata.setCivico(3);
        segnInoltrata.setPriorita(0);
        segnInoltrata.setNumSolleciti(0);
        segnInoltrata.setStato("inoltrata");
        segnInoltrata.setDataSegnalazione(new Date());
        segnInoltrata.setDescrizione("grossa fuoriuscita d'acqua");
        segnInoltrata.setOggetto("Perdita d'acqua");
        segnInoltrata.setFoto("immagine.png");
        segnInoltrata.setRiaperta(0);
        segnApprovata = new Segnalazione(31, "Gramsci", 10, 0, 0, "approvata", new Date(), "Buca in strada",
                "Buca al centro della carreggiata", "immagine2.jpg", null, 0);
        segnInoltrata.setCittadino(cittadino);
        segnApprovata.setCittadino(cittadino);
        service.inserisciSegnalazione(segnInoltrata);
        service.inserisciSegnalazione(segnApprovata);
    }
    @Test
    void TestParameterApprovaDiversoDaNull() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("approva", "Modifica");
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }
    @Test
    void TestCittadinoNonLoggato() {
        cittadino = null;
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.getSession().setAttribute("Cittadino", cittadino);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Effettua il login per"
                + " poter visualizzare questa pagina"
                ,exception.getMessage());
    }

    @Test
    void testOggettoNonValido() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("oggetto", "o!?()");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("L'oggetto deve essere lungo minimo 4 e massimo 25 caratteri. Non può contenere caratteri speciali."
                ,exception.getMessage());
    }

    @Test
    void testOggettoVuoto() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("oggetto", "");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Compilare tutti i campi richiesti!"
                ,exception.getMessage());
    }
    @Test
    void testOggettoNull() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("oggetto", (String) null);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Compilare tutti i campi richiesti!"
                ,exception.getMessage());
    }
    @Test
    void testDescrizioneTroppoCorta() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("descrizione", "perdita");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La descrizione deve essere lunga minimo 10 caratteri e massimo 500"
                ,exception.getMessage());
    }
    @Test
    void testDescrizioneTroppoLunga() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("descrizione", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La descrizione deve essere lunga minimo 10 caratteri e massimo 500"
                ,exception.getMessage());
    }

    @Test
    void testDescrizioneVuoto() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("descrizione", "");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Compilare tutti i campi richiesti!"
                ,exception.getMessage());
    }
    @Test
    void testDescrizioneNull() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("descrizione", (String) null);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Compilare tutti i campi richiesti!"
                ,exception.getMessage());
    }
    @Test
    void testViaNonValida() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("via", "Via roma!%%");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La via deve essere lunga minimo 2 e massimo 200 caratteri. Non può contenere caratteri speciali."
                ,exception.getMessage());
    }
    @Test
    void testViaTroppoCorta() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("via", "a");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La via deve essere lunga minimo 2 e massimo 200 caratteri. Non può contenere caratteri speciali."
                ,exception.getMessage());
    }
    @Test
    void testViaTroppoLunga() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("via", "Viaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La via deve essere lunga minimo 2 e massimo 200 caratteri. Non può contenere caratteri speciali."
                ,exception.getMessage());
    }

    @Test
    void testViaVuoto() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("via", "");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Compilare tutti i campi richiesti!"
                ,exception.getMessage());
    }
    @Test
    void testViaNull() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("via", (String) null);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Compilare tutti i campi richiesti!"
                ,exception.getMessage());
    }
    @Test
    void testRangeCivicoSuperiore() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("civico", "10000");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Il numero civico non è valido."
                ,exception.getMessage());
    }
    @Test
    void testRangeCivicoInferiore() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("civico", "-1");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Il numero civico non è valido."
                ,exception.getMessage());
    }

    @Test
    void testCivicoVuoto() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("civico", "");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Compilare tutti i campi richiesti!"
                ,exception.getMessage());
    }
    @Test
    void testCivicoNull() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("civico", (String) null);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Compilare tutti i campi richiesti!"
                ,exception.getMessage());
    }
    @Test
    void testFormatoCivicoNonValido() {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.setParameter("civico", "a");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Il numero civico non è valido."
                ,exception.getMessage());
    }
    @Test
    void testSegnalazioneNonPresente() {
        request.setParameter("id", 65535+"");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> { servlet.doPost(request,response);});
        assertEquals("La segnalazione non è "
                + "presente nel database.",exception.getMessage());
    }
    @Test
    void testSegnalazioneNonInoltrata() {
        request.setParameter("id", String.valueOf(segnApprovata.getId()));
        MyServletException exception =
                assertThrows(MyServletException.class, () -> { servlet.doPost(request,response);});
        assertEquals("La segnalazione non è nello stato inoltrata.",exception.getMessage());
    }
    @Test
    void testFormatoFotoNonValido() throws IOException {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.addPart(new Part() {
            @Override
            public InputStream getInputStream() throws IOException { return null; }
            @Override
            public String getContentType() {
                return "file";
            }
            @Override
            public String getName() {
                return "foto";
            }
            @Override
            public String getSubmittedFileName() {
                return "foto.exe";
            }
            @Override
            public long getSize() {
                return 0;
            }
            @Override
            public void write(String s) throws IOException { }
            @Override
            public void delete() throws IOException { }
            @Override
            public String getHeader(String s) {
                return null;
            }
            @Override
            public Collection<String> getHeaders(String s) {
                return null;
            }
            @Override
            public Collection<String> getHeaderNames() {
                return null;
            }
        });
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Formato della foto non accettato, I formati accettati sono .png, .jpeg, .jpg"
                ,exception.getMessage());
    }
    @Test
    void testErroreCaricamentoFoto() throws IOException {
        request.setParameter("id", String.valueOf(segnInoltrata.getId()));
        request.addPart(new Part() {
            @Override
            public InputStream getInputStream() throws IOException { return new FileInputStream(new File("foto.png")); }
            @Override
            public String getContentType() { return "file"; }
            @Override
            public String getName() { return "foto"; }
            @Override
            public String getSubmittedFileName() { return "foto.png"; }
            @Override
            public long getSize() { return 0; }
            @Override
            public void write(String s) throws IOException {}
            @Override
            public void delete() throws IOException {}
            @Override
            public String getHeader(String s) { return null; }
            @Override
            public Collection<String> getHeaders(String s) { return null; }
            @Override
            public Collection<String> getHeaderNames() { return null; }
        });
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Errore I/O nel caricamento della foto!" ,exception.getMessage());
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