package model.gestioneDati.modelDataAccess;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Feedback;
import model.gestioneDati.modelObjects.Segnalazione;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackDAOTest extends FeedbackDAO {

    FeedbackDAO feedbackdao;
    static FacadeDAO service;
    static Cittadino cittadino;
    static Segnalazione segnalazione;
    static Feedback feedback2;


    @BeforeEach
    void setUp() {
        feedbackdao = new FeedbackDAO();
    }

    @BeforeAll
    static void setUpAll() {
        service = new FacadeDAO();
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Freschetto", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","freschetto@gmail.com",0,0);

        service.registraCittadino(cittadino);

        segnalazione = new Segnalazione();
        segnalazione.setVia("roma");
        segnalazione.setCivico(3);
        segnalazione.setPriorita(0);
        segnalazione.setNumSolleciti(0);
        segnalazione.setStato("chiusa");
        segnalazione.setDataSegnalazione(new Date());
        segnalazione.setDescrizione("grossa fuoriuscita d'acqua");
        segnalazione.setOggetto("Perdita d'acqua");
        segnalazione.setFoto("immagine.png");
        segnalazione.setRiaperta(0);
        segnalazione.setCittadino(cittadino);
        service.inserisciSegnalazione(segnalazione);
        service.doInsertFeedback(new Feedback(cittadino,segnalazione,"bravi,ottimo lavoro",5,new Date()));
        feedback2 = new Feedback(cittadino,null,"bravi,ottimo lavoro",5,new Date());
    }

    @Test
    void testdoRetrieveFeedBackBySegnalazionePass(){
        assertDoesNotThrow(() -> {feedbackdao.doRetrieveFeedBackBySegnalazione(segnalazione.getId());});
    }


    @Test
    void testIsCittadinoFeedbackSegnalazione(){
        assertDoesNotThrow(() -> {feedbackdao.isCittadinoFeedbackSegnalazione(cittadino.getCF(),segnalazione.getId());});
    }

    @Test
    void testDoInsertFeedback(){
        assertThrows(Exception.class, () -> { feedbackdao.doInsertFeedback(feedback2); });
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