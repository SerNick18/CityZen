package model.gestioneDati.modelObjects;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GestioneSegnalazioniTest {

    private Impiegato  impiegato = new Impiegato("abc@scafati.it","MAT365",
            "Cityzen10!","MPLGEL80A09H387H","Pippo","Pippo","mercato",1,"Fisciano",0,0);
    private Segnalazione  segnalazione = new Segnalazione();
    private GestioneSegnalazioni gs;

    @Test
    public void testCostruttore(){
        GestioneSegnalazioni gs=new GestioneSegnalazioni(impiegato, segnalazione, "approvata", new Date());
        assertNotNull(gs);
    }

    @Test
    public void testGetImpiegato(){
        GestioneSegnalazioni gs=new GestioneSegnalazioni(impiegato, segnalazione, "approvata", new Date());
        assertEquals(impiegato, gs.getImpiegato());
    }

    @Test
    public void testGetSegnalazione(){
        GestioneSegnalazioni gs=new GestioneSegnalazioni(impiegato, segnalazione, "approvata", new Date());
        assertEquals(segnalazione, gs.getSegnalazione());
    }

    @Test
    public void testGetTipoLavorazione(){
        GestioneSegnalazioni gs=new GestioneSegnalazioni(impiegato, segnalazione, "approvata", new Date());
        assertEquals("approvata", gs.getTipoLavorazione());
    }

    @Test
    public void testGetDataLavorazione(){
        GestioneSegnalazioni gs=new GestioneSegnalazioni(impiegato, segnalazione, "approvata", new Date());
        assertEquals(new Date(), gs.getDataLavorazione());
    }

    @Test
    public void testSetImpiegato(){
        GestioneSegnalazioni gs=new GestioneSegnalazioni(impiegato, segnalazione, "approvata", new Date());
        Impiegato  impiegato = new Impiegato("def@scafati.it","MAT90",
                "Cityzen10!","MPLGEL80A09H387H","Pippo","Pippo2","mercato",1,"Fisciano",0,0);
        gs.setImpiegato(impiegato);
        assertEquals(impiegato, gs.getImpiegato());
    }

    @Test
    public void testSetSegnalazione(){
        GestioneSegnalazioni gs=new GestioneSegnalazioni(impiegato, segnalazione, "approvata", new Date());
        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setVia("roma");
        segnalazione.setCivico(3);
        segnalazione.setPriorita(0);
        segnalazione.setNumSolleciti(0);
        segnalazione.setStato("approvata");
        segnalazione.setDataSegnalazione(new Date());
        segnalazione.setDescrizione("grossa fuoriuscita d'acqua");
        segnalazione.setOggetto("Perdita d'acqua");
        segnalazione.setFoto("immagine.png");
        segnalazione.setRiaperta(0);
        gs.setSegnalazione(segnalazione);
        assertEquals(segnalazione, gs.getSegnalazione());
    }

    @Test
    public void testSetTipoLavorazione(){
        GestioneSegnalazioni gs=new GestioneSegnalazioni(impiegato, segnalazione, "approvata", new Date());
        gs.setTipoLavorazione("chiusa");
        assertEquals("chiusa", gs.getTipoLavorazione());
    }

    @Test
    public void testSetDataLavorazione(){
        GestioneSegnalazioni gs=new GestioneSegnalazioni(impiegato, segnalazione, "approvata", new Date());
        gs.setDataLavorazione(new Date());
        assertEquals(new Date(), gs.getDataLavorazione());
    }


}