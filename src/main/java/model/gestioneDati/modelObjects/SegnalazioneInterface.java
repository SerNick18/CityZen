package model.gestioneDati.modelObjects;

import java.util.Date;

public interface SegnalazioneInterface {
    public int getId();
    public String getOggetto();
    public int getPriorita();
    public String getNomeCittadino();
    public int getNumSolleciti();
    public String getVia();
    public int getCivico();
    public String getStato();
    public Date getDataSegnalazione();
    public String getDescrizione();
    public String getFoto();
    public Cittadino getCittadino();
    public int getRiaperta();

}
