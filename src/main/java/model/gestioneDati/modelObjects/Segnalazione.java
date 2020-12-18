package model.gestioneDati.modelObjects;

import java.util.Date;

public class Segnalazione implements SegnalazioneInterface{
    private int id;
    private String via;
    private int civico;
    private int priorita;
    private int numSolleciti;
    private String stato;
    private Date dataSegnalazione;
    private String oggetto;
    private String descrizione;
    private String foto;
    private Cittadino cittadino;
    /**
     * ID della segnalazione chiusa
     */
    private int riaperta;


    public Segnalazione(int id, String via, int civico, int priorita, int numSolleciti,
                        String stato, Date dataSegnalazione, String oggetto,
                        String descrizione, String foto, Cittadino cittadino, int riaperta) {
        this.id = id;
        this.via = via;
        this.civico = civico;
        this.priorita = priorita;
        this.numSolleciti = numSolleciti;
        this.stato = stato;
        this.dataSegnalazione = dataSegnalazione;
        this.oggetto = oggetto;
        this.descrizione = descrizione;
        this.foto = foto;
        this.cittadino = cittadino;
        this.riaperta = riaperta;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getCivico() {
        return civico;
    }

    public void setCivico(int civico) {
        this.civico = civico;
    }

    public int getPriorita() {
        return priorita;
    }

    @Override
    public String getNomeCittadino() {
        return cittadino.getNome();
    }

    public void setPriorita(int priorita) {
        this.priorita = priorita;
    }

    public int getNumSolleciti() {
        return numSolleciti;
    }

    public void setNumSolleciti(int numSolleciti) {
        this.numSolleciti = numSolleciti;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Date getDataSegnalazione() {
        return dataSegnalazione;
    }

    public void setDataSegnalazione(Date dataSegnalazione) {
        this.dataSegnalazione = dataSegnalazione;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Cittadino getCittadino() {
        return cittadino;
    }

    public void setCittadino(Cittadino cittadino) {
        this.cittadino = cittadino;
    }

    public int getRiaperta() {
        return riaperta;
    }

    public void setRiaperta(int riaperta) {
        this.riaperta = riaperta;
    }

    @Override
    public String toString() {
        return "Segnalazione{" +
                "id=" + id +
                ", via='" + via + '\'' +
                ", civico=" + civico +
                ", priorità=" + priorita +
                ", numSolleciti=" + numSolleciti +
                ", stato='" + stato + '\'' +
                ", dataSegnalazione=" + dataSegnalazione +
                ", oggetto='" + oggetto + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", foto='" + foto + '\'' +
                ", cittadino=" + cittadino +
                ", riaperta=" + riaperta +
                '}';
    }
}
