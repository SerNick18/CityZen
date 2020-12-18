package model.gestioneDati.modelObjects;

public class SegnalazioneProxy implements SegnalazioneInterface{
    private int id;
    private String oggetto;
    private int priorita;
    private Cittadino c;
    private int numSolleciti;

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

    public void setNumSolleciti(int numSolleciti) {
        this.numSolleciti = numSolleciti;
    }
}
