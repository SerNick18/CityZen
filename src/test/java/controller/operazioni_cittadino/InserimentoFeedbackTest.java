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
        request.setParameter("id", String.valueOf(idSegnalazione));
    }

    @Test
    void testDescrizioneNonValida() {
        request.setParameter("descrizione", "bravi");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doGet(request,response);});
        assertEquals("La descrizione deve essere lunga minimo 10 caratteri e massimo 500"
                ,exception.getMessage());
    }

    @Test
    void testValutazioneNonValida() {
        request.setParameter("descrizione", "bravi, problema risolto!");
        request.setParameter("valutazione","");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doGet(request,response);});
        assertEquals("Devi selezionare una valutazione."
                ,exception.getMessage());
    }

    @Test
    void testInserimentoFeedbackPass() {
        request.setParameter("descrizione", "bravi, problema risolto!");
        request.setParameter("valutazione","5");
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
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