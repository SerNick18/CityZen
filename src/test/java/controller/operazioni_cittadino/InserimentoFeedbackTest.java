package controller.operazioni_cittadino;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InserimentoFeedbackTest extends InserimentoFeedback {
    InserimentoFeedback servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    static FacadeDAO service;
    static Cittadino cittadino;
    static Segnalazione segnalazione;
    static Segnalazione segnalazioneConFeedback;
    static int idSegnalazione;
    static int idSegnalazioneConfFeedback;
    static Feedback feedback;


    @BeforeAll
    static void setUp() {

        service = new FacadeDAO();
        cittadino = new Cittadino("SCRGNN80A01I483A", "Giovanni", "Scorti", "fa6bdd241d66911a0f121904b968f19ab3a80dd2",
                "Roma",2,"Scafati","scorti@gmail.com",0,0);
        service.registraCittadino(cittadino);
        segnalazione = new Segnalazione();
        segnalazione.setOggetto("Buca in strada");
        segnalazione.setVia("roma");
        segnalazione.setCivico(1);
        segnalazione.setPriorita(0);
        segnalazione.setNumSolleciti(0);
        segnalazione.setStato("chiusa");
        segnalazione.setDataSegnalazione(new Date());
        segnalazione.setFoto("immagineee.jpg");
        segnalazione.setDescrizione("Una buca in via roma");
        segnalazione.setCittadino(cittadino);
        segnalazione.setRiaperta(0);
        service.inserisciSegnalazione(segnalazione);

        List<SegnalazioneInterface> segnalazioni = service.getSegnalazioneByCittadino(cittadino.getCF(),0);
        idSegnalazione = segnalazioni.get(0).getId();

    }

    @BeforeEach
    void setUp_v2(){
        servlet = new InserimentoFeedback();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
        request.getSession().setAttribute("Cittadino", cittadino);

    }

    @Test
    public void provenienzaPresente(){
        request.addParameter("provenienza", "listaChiuse");
        assertDoesNotThrow(() -> {servlet.doPost(request, response);});
    }
    @Test
    public void provenienzaNonDaChiuse(){
        request.addParameter("provenienza", "lista");
        request.addParameter("id", String.valueOf(idSegnalazione));
        request.addParameter("descrizione", "Bravi avete risolto il problema");
        request.addParameter("valutazione", "5");
        assertDoesNotThrow(() -> {servlet.doPost(request, response);});
    }
    @Test
    public void provenienzaNonPresente(){
        request.addParameter("provenienza",(String) null);
        request.addParameter("id", String.valueOf(idSegnalazione));
        request.addParameter("descrizione", "Bravi avete risolto il problema");
        request.addParameter("valutazione", "5");
        assertDoesNotThrow(() -> {servlet.doPost(request, response);});
    }

    @Test
    public void idSegnalazioneNonValido(){
        request.addParameter("id", "5i");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("id della segnalazione non valido"
                ,exception.getMessage());
    }
    @Test
    public void segnalazioneNonPresente(){
        request.addParameter("id", "1000");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La segnalazione cercata non esiste"
                ,exception.getMessage());
    }

    @Test
    public void cittadinoLoggato(){
        request.setParameter("id", String.valueOf(idSegnalazione));
        request.addParameter("descrizione", "Bravi avete risolto il problema");
        request.addParameter("valutazione", "5");
        assertDoesNotThrow(() -> {servlet.doPost(request, response);});
    }
    @Test
    void testDescrizioneTroppoCorta() {
        request.addParameter("id", String.valueOf(idSegnalazione));
        request.addParameter("descrizione", "bravi");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La descrizione deve essere lunga minimo 10 caratteri e massimo 500"
                ,exception.getMessage());
    }
    @Test
    void testDescrizioneTroppoLunga() {
        request.setParameter("id", String.valueOf(idSegnalazione));
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
        request.setParameter("id", String.valueOf(idSegnalazione));
        request.setParameter("descrizione", "");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La descrizione deve essere lunga minimo 10 caratteri e massimo 500"
                ,exception.getMessage());
    }



    @Test
    void testValutazioneNonValida() {
        request.addParameter("id", String.valueOf(idSegnalazione));
        request.addParameter("descrizione", "bravi, problema risolto!");
        request.addParameter("valutazione","");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Devi selezionare una valutazione."
                ,exception.getMessage());
    }

    @Test
    void testValutazioneMinoreDiUno() {
        request.addParameter("id", String.valueOf(idSegnalazione));
        request.addParameter("descrizione", "bravi, problema risolto!");
        request.addParameter("valutazione","0");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La valutazione non può essere minore di 1 o maggiore di 5"
                ,exception.getMessage());
    }

    @Test
    void testValutazioneMaggioreDiCinque() {
        request.addParameter("id", String.valueOf(idSegnalazione));
        request.addParameter("descrizione", "bravi, problema risolto!");
        request.addParameter("valutazione","6");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La valutazione non può essere minore di 1 o maggiore di 5"
                ,exception.getMessage());
    }

    @Test
    void testInserimentoFeedbackPass() {
        request.addParameter("id", String.valueOf(idSegnalazione));
        request.addParameter("descrizione", "bravi, problema risolto!");
        request.addParameter("valutazione","5");
        assertDoesNotThrow(() -> {servlet.doPost(request, response);});
    }

    @Test //test cittadino non loggato
    public void cittadinoNonLoggato(){
        cittadino=null;
        request.getSession().setAttribute("Cittadino",cittadino);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Effettua il login per poter inserire un feedback"
                ,exception.getMessage());
       cittadino = new Cittadino("SCRGNN80A01I483A", "Giovanni", "Scorti", "fa6bdd241d66911a0f121904b968f19ab3a80dd2",
                "Roma",2,"Scafati","scorti@gmail.com",0,0);
        request.getSession().setAttribute("Cittadino",cittadino);
    }

    @AfterAll
    public static void clearDB(){
        try {
            service.eliminaCittadino(cittadino.getCF());
            //eliminando il cittadino si cancella anche la segnalazione
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }
}