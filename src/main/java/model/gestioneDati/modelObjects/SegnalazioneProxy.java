package model.gestioneDati.modelObjects;

import model.gestioneDati.facadeDataAccess.FacadeDAO;

import java.util.Date;

/**
 * Classe per il design pattern proxy.
 */
public class SegnalazioneProxy implements SegnalazioneInterface {
    /**
     * attributo che contiene l'id di una segnalazione.
     */
    private int id;
    /**
     * attributo che contiene l'oggetto di una segnalazione.
     */
    private String oggetto;
    /**
     * attributo che contiene la priorità
     * (alta,media,bassa) di una segnalazione.
     */
    private int priorita;
    /**
     * attributo che contiene il Cittadino
     * che ha effettuato la segnalazione.
     */
    private Cittadino c;
    /**
     * attributo che contiene il numero di
     * solleciti di una segnalazione.
     */
    private int numSolleciti;
    /**
     *attributo che contiene una segnalazione.
     */
    private Segnalazione segnalazione;
    /**
     * attributo che contiene il riferimento all'oggetto FacadeDAO.
     */
    private FacadeDAO facadeDAO;
    /**.
     * Costruttore con parametri
     * @param id identificativo della segnalazione
     *           - precondizione: id>0
     * @param priorita priorità del problema segnalato
     *                 - precondizione: priorità>0 && priorità<5
     * @param numSolleciti numero di solleciti effettuato dai cittadini
     *                - precondizione: numSolleciti >= 0
     * @param oggetto oggetto della segnalazione
     *                - precondizione: oggetto!=null
     * @param c cittadino della segnalazione
     *                - precondizione: c != null
     */
    public SegnalazioneProxy(int id, String oggetto,
                             int priorita, Cittadino c, int numSolleciti) {
        this.id = id;
        this.oggetto = oggetto;
        this.priorita = priorita;
        this.c = c;
        this.numSolleciti = numSolleciti;
        facadeDAO = new FacadeDAO();
    }

    /**
     * Metodo per prendere lo stato di una segnalazione.
     * @return stato
     */
    public String getStato() {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getStato();
    }

    /**
     * Questo metodo sostituisce l'attuale stato della segnalazione
     * con lo stato passato come parametro esplicito.
     * @param stato nuovo stato - precondizione: stato=="inoltrata"
     * || stato=="approvata" || stato=="chiusa" || stato=="rifiutata".
     */
    public void setStato(String stato) {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        segnalazione.setStato(stato);
    }

    /**
     * Metodo per prendere la data di una segnalazione.
     * @return data
     */
    public Date getDataSegnalazione() {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getDataSegnalazione();
    }

    /**
     * Questo metodo sostituisce l'attuale data inoltro della segnalazione
     * con la data passata come parametro esplicito.
     * @param dataSegnalazione nuova data - precondizione: data!=null
     */
    public void setDataSegnalazione(Date dataSegnalazione) {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        segnalazione.setDataSegnalazione(dataSegnalazione);
    }

    /**
     * Metodo per prendere l descrizione
     * di una segnalazione.
     * @return descrizione
     */
    public String getDescrizione() {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getDescrizione();
    }

    /**
     * Questo metodo sostituisce l'attuale descrizione del problema segnalato
     * con la descrizione passata come parametro esplicito.
     * @param descrizione nuova descrizione - precondizione: descrizione!=null.
     */
    public void setDescrizione(String descrizione) {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        segnalazione.setDescrizione(descrizione);
    }

    /**
     * Metodo per prendere la foto di una segnalazione.
     * @return foto
     */
    public String getFoto() {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getFoto();
    }

    /**
     * Questo metodo sostituisce l'attuale foto del problema segnalato
     * con la foto passata come parametro esplicito.
     * @param foto nuova foto - precondizione: foto!=null.
     */
    public void setFoto(String foto) {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        segnalazione.setFoto(foto);
    }

    /**
     * Metodo per prendere il Cittadino
     * che ha effettuato una determinata segnalazione.
     * @return Cittadino
     */
    public Cittadino getCittadino() {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getCittadino();
    }

    /**
     * Questo metodo sostituisce l'attuale cittadino
     * che ha inoltrato la segnalazione
     * con il cittadino passato come parametro esplicito.
     * @param cittadino nuovo cittadino - precondizione: cittadino!=null.
     */
    public void setCittadino(Cittadino cittadino) {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        segnalazione.setCittadino(cittadino);
    }

    /**
     * Metodo per verificare se una
     * segnalazione è stata riaperta o no.
     * @return identificativo della segnalazione chiusa
     */
    public int getRiaperta() {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getRiaperta();
    }

    /**
     * Questo metodo sostituisce l'attuale identificativo
     * della segnalazione chiusa
     * con l'identificativo passato come parametro esplicito.
     * @param riaperta nuovo identificativo - precondizione: riaperta>0
     */
    public void setRiaperta(int riaperta) {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        segnalazione.setRiaperta(riaperta);
    }

    /**
     * Metodo per prendere l'id di una segnalazione.
     * @return identificativo della segnalazione
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Questo metodo sostituisce l'attuale identificativo della segnalazione
     * con l'identificativo passato come parametro esplicito.
     * @param id nuovo identificativo - precondizione: id>0
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo per prendere l'oggetto della segnalazione.
     * @return oggetto della segnalazione
     */
    @Override
    public String getOggetto() {
        return oggetto;
    }

    /**
     * Questo metodo sostituisce l'attuale oggetto della segnalazione.
     * con l'oggetto passato come parametro esplicito.
     * @param oggetto nuovo oggetto - precondizione: oggetto!=null.
     */
    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    /**
     * Metodo per verificare la priorità di una segnalazione.
     * @return
     */
    @Override
    public int getPriorita() {
        return priorita;
    }

    /**
     * Metodo per prendere il nome del Cittadino
     * che ha effettuata una determinata segnalazione.
     * @return
     */
    @Override
    public String getNomeCittadino() {
        return c.getNome();
    }

    /**
     * Questo metodo sostituisce l'attuale priorità del problema segnalato
     * con la priorità passata come parametro esplicito.
     * @param priorita nuova priorità - precondizione: priorità>0 && priorità<5
     */
    public void setPriorita(int priorita) {
        this.priorita = priorita;
    }

    /**
     * Metodo per prendere il Cittadino che ha
     * effettuato una determinata segnalazione.
     * @return Cittadino
     */
    public Cittadino getC() {
        return c;
    }

    /**
     * Questo metodo sostituisce l'attuale cittadino
     * che ha inoltrato la segnalazione
     * con il cittadino passato come parametro esplicito.
     * @param c nuovo cittadino - precondizione: cittadino!=null.
     */
    public void setC(Cittadino c) {
        this.c = c;
    }

    /**
     * Metodo per prendere il numero di solleciti.
     * @return numero solleciti
     */
    @Override
    public int getNumSolleciti() {
        return numSolleciti;
    }

    /**
     * Metodo per prendere la via di una
     * determinata segnalazione.
     * @return via della segnalazione
     */
    @Override
    public String getVia() {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getVia();
    }

    /**
     * Metodo per prendere il numero civico
     * di una determinata segnalazione.
     * @return civico della segnalazione
     */
    @Override
    public int getCivico() {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getCivico();
    }

    /**
     * Questo metodo sostituisce l'attuale numero di
     * solleciti effettuato dai cittadini
     * con il numero passato come parametro esplicito.
     * @param numSolleciti nuovo numero di solleciti
     */
    public void setNumSolleciti(int numSolleciti) {
        this.numSolleciti = numSolleciti;
    }
}
