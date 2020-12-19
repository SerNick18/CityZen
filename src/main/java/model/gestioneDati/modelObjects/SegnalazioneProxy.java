package model.gestioneDati.modelObjects;

import model.gestioneDati.facadeDataAccess.FacadeDAO;

import java.util.Date;

public class SegnalazioneProxy implements SegnalazioneInterface{
    private int id;
    private String oggetto;
    private int priorita;
    private Cittadino c;
    private int numSolleciti;
    private Segnalazione segnalazione;
    private FacadeDAO facadeDAO;
    public SegnalazioneProxy(int id, String oggetto, int priorita, Cittadino c, int numSolleciti) {
        this.id = id;
        this.oggetto = oggetto;
        this.priorita = priorita;
        this.c = c;
        this.numSolleciti = numSolleciti;
        facadeDAO = new FacadeDAO();
    }
    public String getStato() {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getStato();
    }

    public void setStato(String stato) {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        segnalazione.setStato(stato);
    }

    public Date getDataSegnalazione() {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getDataSegnalazione();
    }

    public void setDataSegnalazione(Date dataSegnalazione) {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        segnalazione.setDataSegnalazione(dataSegnalazione);
    }

    public String getDescrizione() {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getDescrizione();
    }

    public void setDescrizione(String descrizione) {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        segnalazione.setDescrizione(descrizione);
    }

    public String getFoto() {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getFoto();
    }

    public void setFoto(String foto) {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        segnalazione.setFoto(foto);
    }

    public Cittadino getCittadino() {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getCittadino();
    }

    public void setCittadino(Cittadino cittadino) {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        segnalazione.setCittadino(cittadino);
    }

    public int getRiaperta() {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getRiaperta();
    }

    public void setRiaperta(int riaperta) {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        segnalazione.setRiaperta(riaperta);
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    @Override
    public int getPriorita() {
        return priorita;
    }

    @Override
    public String getNomeCittadino() {
        return c.getNome();
    }

    public void setPriorita(int priorita) {
        this.priorita = priorita;
    }

    public Cittadino getC() {
        return c;
    }

    public void setC(Cittadino c) {
        this.c = c;
    }

    @Override
    public int getNumSolleciti() {
        return numSolleciti;
    }

    @Override
    public String getVia() {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getVia();
    }

    @Override
    public int getCivico() {
        if (segnalazione==null){
            segnalazione = facadeDAO.getSegnalazioneById(this.id);
        }
        return segnalazione.getCivico();
    }

    public void setNumSolleciti(int numSolleciti) {
        this.numSolleciti = numSolleciti;
    }
}
