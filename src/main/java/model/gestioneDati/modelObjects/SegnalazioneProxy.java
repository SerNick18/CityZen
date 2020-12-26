package model.gestioneDati.modelObjects;

import model.gestioneDati.facadeDataAccess.FacadeDAO;

import java.util.Date;

/**
 *
 */
public class SegnalazioneProxy implements SegnalazioneInterface {
    /**
     *attributo che contiene l'id di una segnalazione.
     */
    private int id;
    /**
     * attributo che contiene l'oggetto di una segnalazione.
     */
    private String oggetto;
    /**
     *attributo che contiene la priorità
     * (alta,media,bassa) di una segnalazione.
     */
    private int priorita;
    /**
     *attributo che contiene il Cittadino
     * che ha effettuato la segnalazione.
     */
    private Cittadino c;
    /**
     *attributo che contiene il numero di
     * solleciti di una segnalazione.
     */
    private int numSolleciti;
    /**
     *attributo che contiene una segnalazione.
     */
    private Segnalazione segnalazione;
    /**
     *
     */
    private FacadeDAO facadeDAO;

    /**
     *
     * @param id
     * @param oggetto
     * @param priorita
     * @param c
     * @param numSolleciti
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
     * Metodo per prendere lo stato di una
     * segnalazione.
     * @return stato
     */
    public String getStato() {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getStato();
    }

    /**
     * Metodo per cambaire lo stato
     * di una segnalazione.
     * @param stato
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
     * Metodo per cambiare la data di una segnalazione.
     * @param dataSegnalazione
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
     * Metodo per settare la descrizione
     * di una segnalazione.
     * @param descrizione
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
     * Metodo per settare la foto di una segnalazione.
     * @param foto
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
     * Metodo per settare il Cittadino a una
     * determinata segnalazione.
     * @param cittadino
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
     * @return int 0 non riaperta 1 riaperta.
     */
    public int getRiaperta() {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getRiaperta();
    }

    /**
     * Metodo per settare la riapertura
     * della segnalazione.
     * 0 non riaperta 1 riaperta.
     * @param riaperta
     */
    public void setRiaperta(int riaperta) {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        segnalazione.setRiaperta(riaperta);
    }

    /**
     * Metodo per prendere l'id di una segnalazione.
     * @return
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Metodo per settare l'id di una segnalazione.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo per prendere l'oggetto della segnalazione.
     * @return
     */
    @Override
    public String getOggetto() {
        return oggetto;
    }

    /**
     * Metodo per settare l'oggetto di una segnalazione.
     * @param oggetto
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
     * Metodo per settare la priorità.
     * @param priorita
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
     * Metodo per settare un Cittadino ad una
     * determinata segnalazione.
     * @param c
     */
    public void setC(Cittadino c) {
        this.c = c;
    }

    /**
     * Metodo per prendere il numero di solleciti.
     * @return
     */
    @Override
    public int getNumSolleciti() {
        return numSolleciti;
    }

    /**
     * Metodo per prendere la via di una
     * determinata segnalazione.
     * @return
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
     * @return
     */
    @Override
    public int getCivico() {
        if (segnalazione == null) {
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getCivico();
    }

    /**
     * Metodo per settare il numero
     * di solleciti.
     * @param numSolleciti
     */
    public void setNumSolleciti(int numSolleciti) {
        this.numSolleciti = numSolleciti;
    }
}
