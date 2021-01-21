package model.gestioneDati.modelObjects;
import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SegnalazioneProxyTest {
    static Cittadino cittadino;
    static Segnalazione segnalazione;
    static FacadeDAO service=new FacadeDAO();

    @BeforeAll
    public static void setUp(){
         cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Scafati","cattaneo@gmail.com",0,0);
        service.registraCittadino(cittadino);
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
        segnalazione.setCittadino(cittadino);
        service.inserisciSegnalazione(segnalazione);
    }

    @Test
    public void getId(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(1,"oggetto abcdfghe",1,cittadino,0);
        assertEquals(1,segnalazioneProxy.getId());
    }

    @Test
    public void getOggetto(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(1,"oggetto abcdfghe",1,cittadino,0);
        assertEquals("oggetto abcdfghe",segnalazioneProxy.getOggetto());
    }

    @Test
    public void getPriorita(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(1,"oggetto abcdfghe",1,cittadino,0);
        assertEquals(1,segnalazioneProxy.getPriorita());
    }

    @Test
    public void getCittadino(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(1,"oggetto abcdfghe",1,cittadino,0);
        assertEquals(cittadino.getNome(),segnalazioneProxy.getNomeCittadino());
    }

    @Test
    public void getNumSolleciti(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(1,"oggetto abcdfghe",1,cittadino,0);
        assertEquals(0,segnalazioneProxy.getNumSolleciti());
    }

    @Test
    public void getVia(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(segnalazione.getId(),"oggetto abcdfghe",1,cittadino,0);
        assertEquals(segnalazione.getVia(),segnalazioneProxy.getVia());
    }

    @Test
    public void getCivico(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(segnalazione.getId(),"oggetto abcdfghe",1,cittadino,0);
        assertEquals(segnalazione.getCivico(),segnalazioneProxy.getCivico());
    }

    @Test
    public void getC(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(segnalazione.getId(),"oggetto abcdfghe",1,cittadino,0);
        assertEquals(segnalazione.getCittadino(),segnalazioneProxy.getC());
    }

    @Test
    public void getStato(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(segnalazione.getId(),"oggetto abcdfghe",1,cittadino,0);
        assertEquals(segnalazione.getStato(),segnalazioneProxy.getStato());
    }

    @Test
    public void getFoto(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(segnalazione.getId(),"oggetto abcdfghe",1,cittadino,0);
        assertEquals(segnalazione.getFoto(),segnalazioneProxy.getFoto());
    }

    @Test
    public void getDescrizione(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(segnalazione.getId(),"oggetto abcdfghe",1,cittadino,0);
        assertEquals(segnalazione.getDescrizione(),segnalazioneProxy.getDescrizione());
    }

    @Test
    public void setId(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(2,"oggetto abcdfghe",1,cittadino,0);
        segnalazioneProxy.setId(1);
        assertEquals(1,segnalazioneProxy.getId());
    }

    @Test
    public void setOggetto(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(2,"",1,cittadino,0);
        segnalazioneProxy.setOggetto("oggetto abcdfghe");
        assertEquals("oggetto abcdfghe",segnalazioneProxy.getOggetto());
    }

    @Test
    public void setCittadino(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(segnalazione.getId(),"oggetto abcdfghe",1,null,0);
        Cittadino cittadino2 = new Cittadino("SBAFNC98T26H703S","Francesco","Sabia","Password1!","via europa",12,"Salerno","abcd98@gmail.com",0,0);
        segnalazioneProxy.setCittadino(cittadino2);
        assertEquals(cittadino2,segnalazioneProxy.getCittadino());
    }

    @Test
    public void setPriorita(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(2,"oggetto abcdfghe",2,cittadino,0);
        segnalazioneProxy.setPriorita(1);
        assertEquals(1,segnalazioneProxy.getPriorita());
    }

    @Test
    public void setNumSollecito(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(2,"oggetto abcdfghe",2,cittadino,0);
        segnalazioneProxy.setNumSolleciti(2);
        assertEquals(2,segnalazioneProxy.getNumSolleciti());
    }

    @Test
    public void setStato(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(segnalazione.getId(), "oggetto abcdfghe",2,cittadino,0);
        segnalazioneProxy.setStato("approvata");
        assertEquals("approvata",segnalazioneProxy.getStato());
    }

    @Test
    public void setDescrizione(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(segnalazione.getId(), "oggetto abcdfghe",2,cittadino,0);
        segnalazioneProxy.setDescrizione("oggetto 22");
        assertEquals("oggetto 22",segnalazioneProxy.getDescrizione());
    }

    @Test
    public void setFoto(){
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(segnalazione.getId(), "oggetto abcdfghe",2,cittadino,0);
        segnalazioneProxy.setFoto("foto.png");
        assertEquals("foto.png",segnalazioneProxy.getFoto());
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
