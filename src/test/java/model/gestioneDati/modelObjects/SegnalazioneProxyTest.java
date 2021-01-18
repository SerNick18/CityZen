package model.gestioneDati.modelObjects;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SegnalazioneProxyTest {
    Cittadino cittadino;

    @BeforeEach
    public void setUp(){
         cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
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
        SegnalazioneProxy segnalazioneProxy = new SegnalazioneProxy(2,"oggetto abcdfghe",1,null,0);
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



}
