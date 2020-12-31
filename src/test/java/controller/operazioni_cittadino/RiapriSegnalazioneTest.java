package controller.operazioni_cittadino;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RiapriSegnalazioneTest extends RiapriSegnalazione {
    RiapriSegnalazione servlet;
    MockHttpServletRequest req;
    MockHttpServletResponse resp;
    static Cittadino cittadino;
    static FacadeDAO service;
    static Segnalazione segnalazione;
    static Segnalazione segnalazioneInoltrata;

    @BeforeEach
    void setUp() {
        req = new MockHttpServletRequest();
        resp = new MockHttpServletResponse();
        servlet = new RiapriSegnalazione();
        service = new FacadeDAO();
        cittadino = new Cittadino("CPNLLD11S19A489G", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","test@gmail.com",0,0);
        service.registraCittadino(cittadino);
        req.getSession().setAttribute("Cittadino",cittadino);
        segnalazione = new Segnalazione();
        segnalazione.setOggetto("Buca in strada");
        segnalazione.setVia("roma");
        segnalazione.setCivico(1);
        segnalazione.setPriorita(0);
        segnalazione.setNumSolleciti(0);
        segnalazione.setStato("chiusa");
        segnalazione.setDataSegnalazione(new Date());
        segnalazione.setFoto("immagine.jpg");
        segnalazione.setDescrizione("Una buca in via roma");
        segnalazione.setCittadino(cittadino);
        segnalazione.setRiaperta(0);
        service.inserisciSegnalazione(segnalazione);

        segnalazioneInoltrata = new Segnalazione();
        segnalazione.setOggetto("Buca in strada");
        segnalazione.setVia("roma");
        segnalazione.setCivico(1);
        segnalazione.setPriorita(0);
        segnalazione.setNumSolleciti(0);
        segnalazione.setStato("inoltrata");
        segnalazione.setDataSegnalazione(new Date());
        segnalazione.setFoto("immagine.jpg");
        segnalazione.setDescrizione("Una buca in via roma");
        segnalazione.setCittadino(cittadino);
        segnalazione.setRiaperta(0);
        service.inserisciSegnalazione(segnalazione);

        req.setParameter("idSegnalazione",
                service.getSegnalazioneByCittadino(cittadino.getCF(),0).get(0).getId()+"");
    }
    @Test
    void testDescrizioneTroppoLunga() {
        req.setParameter("descrizione", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
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
                assertThrows(MyServletException.class, () -> {servlet.doPost(req,resp);});
        assertEquals("La descrizione deve essere lunga minimo 10 caratteri e massimo 500"
                ,exception.getMessage());
    }
    @Test
    void testDescrizioneNotValid() {
        req.setParameter("descrizione","a");
        MyServletException exception = assertThrows(MyServletException.class, () -> {servlet.doPost(req,resp);});
        assertEquals("La descrizione deve essere lunga minimo 10 caratteri e massimo 500",exception.getMessage());
    }
    @Test
    void testFormatoFotoNonValido() throws IOException {
        req.setParameter("descrizione", "Il problema si è ripresentato");
        req.addPart(new Part() {
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
                assertThrows(MyServletException.class, () -> {servlet.doPost(req,resp);});
        assertEquals("Formato della foto non accettato, I formati accettati sono .png, .jpeg, .jpg"
                ,exception.getMessage());
    }
    @AfterEach
    public void clearDB(){
        try {
            service.eliminaCittadino(cittadino.getCF());
            //eliminando il cittadino si elimina anche la segnalazione
            //service.eliminaSegnalazione(segnalazione.getId());
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }
    //methods for whitebox testing
    @Test
    void testSessionCittadinoNull() {
        req.getSession().removeAttribute("Cittadino");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(req,resp);});
        assertEquals("Effettua il login per"
                        + " poter visualizzare questa pagina"
                ,exception.getMessage());
    }
    @Test
    void testIdSegnalazioneNotValid() {
        req.setParameter("idSegnalazione","a");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(req,resp);});
        assertEquals("Errore nell'elaborazione "
                        + "della richiesta, riprovare!"
                ,exception.getMessage());
    }
    @Test
    void testRedirect() {
        req.setParameter("idSegnalazioneDaAprire","3");
        assertDoesNotThrow( () -> {servlet.doPost(req,resp);});
    }
    @Test
    void testInoltroSegnalazioneJpeg() throws IOException {
        req.setParameter("descrizione", "Perdita d’acqua in via roma, altezza supermercato, civico 3");
        req.addPart(new Part() {
            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(new File("foto.jpeg"));
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
                return "foto.jpeg";
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
                assertThrows(MyServletException.class, () -> {servlet.doPost(req,resp);});
        assertEquals("Errore I/O nel caricamento della foto!" ,exception.getMessage());
    }
    @Test
    void testInoltroSegnalazioneJpg() throws IOException {
        req.setParameter("descrizione", "Perdita d’acqua in via roma, altezza supermercato, civico 3");
        req.addPart(new Part() {
            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(new File("foto.jpg"));
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
                return "foto.jpg";
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
                assertThrows(MyServletException.class, () -> {servlet.doPost(req,resp);});
        assertEquals("Errore I/O nel caricamento della foto!" ,exception.getMessage());
    }
    @Test
    void testInoltroSegnalazionePng() throws IOException {
        req.setParameter("descrizione", "Perdita d’acqua in via roma, altezza supermercato, civico 3");
        req.addPart(new Part() {
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
                assertThrows(MyServletException.class, () -> {servlet.doPost(req,resp);});
        assertEquals("Errore I/O nel caricamento della foto!" ,exception.getMessage());
    }
}