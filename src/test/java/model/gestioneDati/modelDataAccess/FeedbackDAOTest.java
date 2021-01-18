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
    static Segnalazione segnalazione1;
    static DataSource dataSource;
    final static int id=547;

    @BeforeEach
    void setUp() {
        feedbackdao = new FeedbackDAO();
    }

    @BeforeAll
    static void setUpAll() {
        service = new FacadeDAO();
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Freschetto", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","freschetto@gmail.com",0,0);

        segnalazione1 = service.getSegnalazioneById(1);
        service.registraCittadino(cittadino);
        service.doInsertFeedback(new Feedback(cittadino,segnalazione1,"bravi,ottimo lavoro",5,new Date()));
    }

    @Test
    void testdoRetrieveFeedBackBySegnalazionePass(){
        assertDoesNotThrow(() -> {feedbackdao.doRetrieveFeedBackBySegnalazione(1);});
    }

 /*   @Test
    void testdoRetrieveFeedBackBySegnalazioneFail(){
        assertThrows(Exception.class, () -> { feedbackdao.doRetrieveFeedBackBySegnalazione(1); });
    } */

    @Test
    void testIsCittadinoFeedbackSegnalazione(){
        assertDoesNotThrow(() -> {feedbackdao.isCittadinoFeedbackSegnalazione(cittadino.getCF(),1);});
    }

    @Test
    void testDoInsertFeedback(){
        Feedback feedback = new Feedback(cittadino,null,"bravi,ottimo lavoro",5,new Date());
        assertThrows(Exception.class, () -> { feedbackdao.doInsertFeedback(feedback); });
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