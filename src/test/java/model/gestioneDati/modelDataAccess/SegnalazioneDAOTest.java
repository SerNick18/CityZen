package model.gestioneDati.modelDataAccess;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;
import model.gestioneDati.modelObjects.SegnalazioneProxy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SegnalazioneDAOTest extends SegnalazioneDAO {
    SegnalazioneDAO segnalazioneDAO;
    CittadinoDAO cittadinoDAO;
    Segnalazione segnalazione;
    Cittadino cittadino;
    @BeforeEach
    void setUp() {
        segnalazioneDAO = new SegnalazioneDAO();
        cittadinoDAO = new CittadinoDAO();
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
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "Cityzen10!",
                "via roma", 3, "Fisciano", "cattaneo@gmail.com", 0, 0);
        cittadinoDAO.doRegister(cittadino);
        segnalazione.setCittadino(cittadino);
        segnalazioneDAO.doInsert(segnalazione);
    }
    @AfterEach
    void tearDown() throws MyServletException {
        segnalazioneDAO.doDelete(segnalazione.getId());
        cittadinoDAO.doDelete(cittadino.getCF());
    }
    @Test
    void testDoRetrieveInoltrateProxy() {
        assertDoesNotThrow(()->{segnalazioneDAO.doRetrieveInoltrateProxy(0);});
    }
    @Test
    void testDoRetrieveInoltrateProxyFail() {
        assertThrows(Exception.class, ()->{segnalazioneDAO.doRetrieveInoltrateProxy(-1);});
    }
    @Test
    void testDoRetrieveByStato() {
        assertDoesNotThrow(()->{segnalazioneDAO.doRetrieveByStato("inoltrata",0);});
    }
    @Test
    void testDoRetrieveByStatoFail() {
        assertThrows(Exception.class, ()->{segnalazioneDAO.doRetrieveByStato("inoltrata", -1);});
    }
    @Test
    void testDoRetrieveById() {
        assertDoesNotThrow(()->{segnalazioneDAO.doRetrieveById(segnalazione.getId());});
    }
    @Test
    void testDoRetrieveByCittadino() {
        assertDoesNotThrow(()->{segnalazioneDAO.doRetrieveByCittadino(cittadino.getCF(), 0);});
    }
    @Test
    void testDoRetrieveByCittadinoFail() {
        assertThrows(Exception.class, ()->{segnalazioneDAO.doRetrieveByCittadino(cittadino.getCF(), -1);});
    }
    @Test
    void testDoInsert() throws MyServletException {
        segnalazioneDAO.doDelete(segnalazione.getId());
        assertDoesNotThrow(()->{segnalazioneDAO.doInsert(segnalazione);});
    }
    @Test
    void testDoUpdate() {
        segnalazione.setOggetto("nuovo oggetto");
        assertDoesNotThrow(()->{segnalazioneDAO.doUpdate(segnalazione);});
    }
    @Test
    void testDoDelete() {
        assertThrows(MyServletException.class, ()->{segnalazioneDAO.doDelete(-1);});
    }
}