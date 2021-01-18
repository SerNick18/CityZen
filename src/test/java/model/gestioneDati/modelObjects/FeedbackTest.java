package model.gestioneDati.modelObjects;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FeedbackTest {
    private Cittadino cit;
    private Segnalazione segnalazione;

    @Before
    public void setUp(){
        cit = new Cittadino("SBAFNC98T26H703S","Francesco","Sabia","Password1!","via europa",12,"Salerno","abcd98@gmail.com",0,0);
         segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
    }

    @Test
    public void testCostruttoreVuoto(){
        Feedback fed = new Feedback();
        assertNotNull(fed);
    }

    @Test
    public void getCittadino(){
        Feedback fed = new Feedback(cit,segnalazione,"descrizione abcdfger",4,new Date(2020-01-14));
        assertEquals(fed.getCittadino(),cit);
    }

    @Test
    public void getSegnalazione(){
        Feedback fed = new Feedback(cit,segnalazione,"descrizione abcdfger",4,new Date(2020-01-14));
        assertEquals(fed.getSegnalazione(),segnalazione);
    }

    @Test
    public void getDescrizione(){
        Feedback fed = new Feedback(cit,segnalazione,"descrizione abcdfger",4,new Date(2020-01-14));
        assertEquals(fed.getDescrizione(),"descrizione abcdfger");
    }

    @Test
    public void getValutazione(){
        Feedback fed = new Feedback(cit,segnalazione,"descrizione abcdfger",4,new Date(2020-01-14));
        assertEquals(fed.getValutazione(),4);
    }

    @Test
    public void getDataFeedback(){
        Feedback fed = new Feedback(cit,segnalazione,"descrizione abcdfger",4,new Date(2020-01-14));
        assertEquals(fed.getDataFeedback(),new Date(2020-01-14));
    }

    @Test
    public void setCittadino(){
        Feedback fed = new Feedback(null,segnalazione,"abcdfgherrraa",3,new Date(2020-01-15));
        fed.setCittadino(cit);
        assertEquals(cit,fed.getCittadino());
    }

    @Test
    public void setSegnalazione(){
        Feedback fed = new Feedback(cit,null,"abcdfgherrraa",3,new Date(2020-01-15));
        fed.setSegnalazione(segnalazione);
        assertEquals(segnalazione,fed.getSegnalazione());
    }

    @Test
    public void setDescrizione(){
        Feedback fed = new Feedback(cit,segnalazione,"",3,new Date(2020-01-15));
        fed.setDescrizione("anididiedieidjid");
        assertEquals("anididiedieidjid",fed.getDescrizione());
    }

    @Test
    public void setValutazione(){
        Feedback fed = new Feedback(cit,segnalazione,"anididiedieidjid",0,new Date(2020-01-15));
        fed.setValutazione(4);
        assertEquals(4,fed.getValutazione());
    }

    @Test
    public void setData(){
        Feedback fed = new Feedback(cit,segnalazione,"anididiedieidjid",5,null);
       fed.setDataFeedback(new Date(2020-01-16));
        assertEquals(new Date(2020-01-16),fed.getDataFeedback());
    }



}
