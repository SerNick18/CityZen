package controller.operazioni_cittadino;

import controller.gestioneProfilo.ModificaPassword;
import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileUrlResource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import javax.servlet.http.Part;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ModificaSegnalazioneTest extends ModificaSegnalazione {
    ModificaSegnalazione servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    static FacadeDAO service;
    static Cittadino cittadino;


    @BeforeEach
    void setUp() {
        servlet = new ModificaSegnalazione();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
        service = new FacadeDAO();
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        request.getSession().setAttribute("Cittadino", cittadino);
        request.setParameter("oggetto","_");
        request.setParameter("descrizione","_");
        request.setParameter("via","_");
        request.setParameter("civico","_");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testOggettoNonValido() {
        request.setParameter("id", "2");
        request.setParameter("oggetto", "o!?()");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("L'oggetto deve essere lungo minimo 4 e massimo 25 caratteri. Non può contenere caratteri speciali."
                ,exception.getMessage());
    }
    @Test
    void testDescrizioneNonValida() {
        request.setParameter("id", "2");
        request.setParameter("oggetto", "Perdita d'acqua");
        request.setParameter("descrizione", "perdita");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La descrizione deve essere lunga minimo 10 caratteri e massimo 500"
                ,exception.getMessage());
    }
    @Test
    void testViaNonValida() {
        request.setParameter("id", "2");
        request.setParameter("oggetto", "Perdita d'acqua");
        request.setParameter("descrizione", "Perdita d’acqua in via roma, altezza supermercato, civico 3");
        request.setParameter("via", "Via roma!%%");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La via deve essere lunga minimo 2 e massimo 200 caratteri. Non può contenere caratteri speciali."
                ,exception.getMessage());
    }
    @Test
    void testRangeCivicoNonValido() {
        request.setParameter("id", "2");
        request.setParameter("oggetto", "Perdita d'acqua");
        request.setParameter("descrizione", "Perdita d’acqua in via roma, altezza supermercato, civico 3");
        request.setParameter("via", "Via roma");
        request.setParameter("civico", "-1");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Il numero civico non è valido."
                ,exception.getMessage());
    }
    @Test
    void testFormatoCivicoNonValido() {
        request.setParameter("id", "2");
        request.setParameter("oggetto", "Perdita d'acqua");
        request.setParameter("descrizione", "Perdita d’acqua in via roma, altezza supermercato, civico 3");
        request.setParameter("via", "Via roma");
        request.setParameter("civico", "a");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Il numero civico non è valido."
                ,exception.getMessage());
    }
    @Test
    void testFormatoFotoNonValido() throws IOException {
        request.setParameter("id", "2");
        request.setParameter("oggetto", "Perdita d'acqua");
        request.setParameter("descrizione", "Perdita d’acqua in via roma, altezza supermercato, civico 3");
        request.setParameter("via", "Via roma");
        request.setParameter("civico", "3");
        request.addPart(new Part() {
            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

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
            public void write(String s) throws IOException {

            }

            @Override
            public void delete() throws IOException {

            }

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
    void testModificaSegnalazionePass() throws IOException {
        request.setParameter("id", "2");
        request.setParameter("oggetto", "Perdita d'acqua");
        request.setParameter("descrizione", "Perdita d’acqua in via roma, altezza supermercato, civico 3");
        request.setParameter("via", "Via roma");
        request.setParameter("civico", "3");
        request.addPart(new Part() {
            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(new File("foto.png"));
            }

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
                return "foto.png";
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public void write(String s) throws IOException {}

            @Override
            public void delete() throws IOException {}

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
        assertEquals("Errore I/O nel caricamento della foto!" ,exception.getMessage());
    }
}