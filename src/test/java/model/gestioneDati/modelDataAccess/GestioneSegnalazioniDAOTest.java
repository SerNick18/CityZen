package model.gestioneDati.modelDataAccess;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;
import model.gestioneDati.modelObjects.Segnalazione;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GestioneSegnalazioniDAOTest extends GestioneSegnalazioniDAO {
    GestioneSegnalazioniDAO gestioneSegnalazioniDAO;
    Segnalazione segnalazione;
    Cittadino cittadino;
    Impiegato impiegato;
    FacadeDAO service;


    @BeforeEach
    void setUp() {
        service = new FacadeDAO();
        gestioneSegnalazioniDAO = new GestioneSegnalazioniDAO();
        segnalazione = new Segnalazione();
        segnalazione.setId(3333);
        segnalazione.setOggetto("aaaaaaaaaaaaaaaa");
        segnalazione.setVia("via roma");
        segnalazione.setCivico(3);
        segnalazione.setPriorita(0);
        segnalazione.setNumSolleciti(0);
        segnalazione.setStato("inoltrata");
        segnalazione.setDataSegnalazione(new Date());
        segnalazione.setDescrizione("descrizione della segnalazione");
        segnalazione.setFoto("aaaa");
        segnalazione.setRiaperta(0);
        impiegato = new Impiegato("leo33@scafati.it", "3", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "Cityzen10!",
                "via roma", 3, "Fisciano", "cattaneo@gmail.com", 0, 0);
        segnalazione.setCittadino(cittadino);
        service.registraCittadino(cittadino);
        service.inserisciImpiegato(impiegato);
        service.inserisciSegnalazione(segnalazione);
    }

    @AfterEach
    void tearDown() throws MyServletException {
        service.eliminaCittadino(cittadino.getCF());
        service.eliminaImpiegato(impiegato.getMatricola());
    }

    @Test
    void testDoInsert() {
        assertDoesNotThrow(()->{gestioneSegnalazioniDAO.doInsert(impiegato, segnalazione);});
    }

    @Test
    void testDoRetrieveImpiegatiOsservatori() {
        assertDoesNotThrow(()->{gestioneSegnalazioniDAO.doRetrieveImpiegatiOsservatori(segnalazione.getId());});
    }
}