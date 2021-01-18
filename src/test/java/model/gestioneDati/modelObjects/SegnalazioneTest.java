package model.gestioneDati.modelObjects;

import controller.operazioni_cittadino.EliminaSegnalazione;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SegnalazioneTest{
    private Cittadino cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
            "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
    @Test
    void testSegnalazioneCostruttoreVuoto(){
        Segnalazione segnalazione = new Segnalazione();
        assertNotNull(segnalazione);
    }

    @Test
    void testGetId(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals(1, segnalazione.getId());
    }

    @Test
    void testSetId(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        segnalazione.setId(2);
        assertEquals(2, segnalazione.getId());
    }

    @Test
    void testGetVia(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals("Pirandello", segnalazione.getVia());
    }

    @Test
    void testSetVia(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        segnalazione.setVia("Leonardo");
        assertEquals("Leonardo", segnalazione.getVia());
    }

    @Test
    void testGetCivico(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals(1, segnalazione.getCivico());
    }

    @Test
    void testSetCivico(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        segnalazione.setCivico(2);
        assertEquals(2, segnalazione.getCivico());
    }

    @Test
    void testGetPriorita(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals(2, segnalazione.getPriorita());
    }

    @Test
    void testSetPriorita(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        segnalazione.setPriorita(1);
        assertEquals(1, segnalazione.getPriorita());
    }

    @Test
    void testGetNomeCittadino(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals("Giuseppe", cittadino.getNome());
    }

    @Test
    void testGetNumSolleciti(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals(3, segnalazione.getNumSolleciti());
    }

    @Test
    void testSetNumSolleciti(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        segnalazione.setNumSolleciti(5);
        assertEquals(5, segnalazione.getNumSolleciti());
    }

    @Test
    void testGetStato(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals("approvata", segnalazione.getStato());
    }

    @Test
    void testSetStato(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        segnalazione.setStato("chiusa");
        assertEquals("chiusa", segnalazione.getStato());
    }

    @Test
    void testGetDataSegnalazione(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals(new Date(2020-01-13), segnalazione.getDataSegnalazione());
    }

    @Test
    void testSetDataSegnalazione(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        segnalazione.setDataSegnalazione(new Date(2021-01-13));
        assertEquals(new Date(2021-01-13), segnalazione.getDataSegnalazione());
    }

    @Test
    void testGetOggetto(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals("buca", segnalazione.getOggetto());
    }

    @Test
    void testSetOggetto(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        segnalazione.setOggetto("perdita d'acqua");
        assertEquals("perdita d'acqua", segnalazione.getOggetto());
    }

    @Test
    void testGetDescrizione(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals("buca in via Pirandello", segnalazione.getDescrizione());
    }

    @Test
    void testSetDescrizione(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        segnalazione.setDescrizione("perdita d'acqua in via Pirandello");
        assertEquals("perdita d'acqua in via Pirandello", segnalazione.getDescrizione());
    }

    @Test
    void testGetFoto(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals("buca.jpg", segnalazione.getFoto());
    }

    @Test
    void testSetFoto(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        segnalazione.setFoto("perdita.jpg");
        assertEquals("perdita.jpg", segnalazione.getFoto());
    }

    @Test
    void testGetCittadino(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals(new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), segnalazione.getCittadino());
    }

    @Test
    void testSetCittadino(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        segnalazione.setCittadino(cittadino);
        assertEquals(cittadino, segnalazione.getCittadino());
    }

    @Test
    void testGetRiaperta(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals(0, segnalazione.getRiaperta());
    }

    @Test
    void testSetRiaperta(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        segnalazione.setRiaperta(1);
        assertEquals(1, segnalazione.getRiaperta());
    }

    @Test
    void testToString(){
        Segnalazione segnalazione = new Segnalazione(1, "Pirandello", 1, 2, 3, "approvata", new Date(2020-01-13), "buca", "buca in via Pirandello",
                "buca.jpg", new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0), 0);
        assertEquals("Segnalazione{"
                + "id=" + segnalazione.getId()
                + ", via='" + segnalazione.getVia() + '\''
                + ", civico=" + segnalazione.getCivico()
                + ", priorità=" + segnalazione.getPriorita()
                + ", numSolleciti=" + segnalazione.getNumSolleciti()
                + ", stato='" + segnalazione.getStato() + '\''
                + ", dataSegnalazione=" + segnalazione.getDataSegnalazione()
                + ", oggetto='" + segnalazione.getOggetto() + '\''
                + ", descrizione='" + segnalazione.getDescrizione() + '\''
                + ", foto='" + segnalazione.getFoto() + '\''
                + ", cittadino=" + segnalazione.getCittadino()
                + ", riaperta=" + segnalazione.getRiaperta()
                + '}', segnalazione.toString());
    }
}
