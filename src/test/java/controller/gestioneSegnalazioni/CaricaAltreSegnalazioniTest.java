package controller.gestioneSegnalazioni;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CaricaAltreSegnalazioniTest extends CaricaAltreSegnalazioni {
    CaricaAltreSegnalazioni servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    static FacadeDAO service;
    static Cittadino cittadino;
    static Segnalazione segnalazione;

    @BeforeAll
    static void setUpAll(){
        service = new FacadeDAO();
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Fresco", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Scafati","fresco@gmail.com",0,0);
        service.registraCittadino(cittadino);
        for(int i=0; i<20; i++){
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
        }
    }

    @BeforeEach
    void setUp() {
        servlet = new CaricaAltreSegnalazioni();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
        request.getSession().setAttribute("Cittadino", cittadino);
    }

    @Test
    public void caricaProprieSegnalazioni(){
        request.addParameter("offset", "0");
        request.addParameter("tipo", "proprie-segnalazioni");
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @Test
    public void caricaSegnalazioniApprovateCittadino(){
        request.addParameter("stato", "approvata");
        request.addParameter("offset", "0");
        request.addParameter("tipo", "approvate-cittadino");
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @Test
    public void caricaSegnalazioniChiuseCittadino(){
        request.addParameter("stato", "approvata");
        request.addParameter("offset", "0");
        request.addParameter("tipo", "chiuse-cittadino");
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @Test
    public void caricaSegnalazioniApprovateImpiegato(){
        request.addParameter("stato", "approvata");
        request.addParameter("offset", "0");
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @Test
    public void caricaSegnalazioniChiuseImpiegato(){
        request.addParameter("stato", "chiusa");
        request.addParameter("offset", "0");
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @Test
    public void tipoNull(){
        request.addParameter("stato", "approvata");
        request.addParameter("offset", "0");
        assertDoesNotThrow(()->servlet.doPost(request, response));
    }

    @Test
    public void nessunaSegnalazioneDaCaricare() throws MyServletException {
        service.eliminaCittadino(cittadino.getCF());
        request.addParameter("stato", "chiusa");
        request.addParameter("offset", "0");
        assertDoesNotThrow(()->servlet.doPost(request, response));
        setUpAll();
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