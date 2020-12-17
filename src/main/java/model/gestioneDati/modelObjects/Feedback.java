package model.gestioneDati.modelObjects;

import java.util.Date;

public class Feedback {
    private Cittadino cittadino;
    private Segnalazione segnalazione;
    private String descrizione;
    private int valutazione;
    private Date dataFeedback;

    public Feedback(Cittadino cittadino, Segnalazione segnalazione, String descrizione, int valutazione, Date dataFeedback) {
        this.cittadino = cittadino;
        this.segnalazione = segnalazione;
        this.descrizione = descrizione;
        this.valutazione = valutazione;
        this.dataFeedback = dataFeedback;
    }

    public Cittadino getCittadino() {
        return cittadino;
    }

    public void setCittadino(Cittadino cittadino) {
        this.cittadino = cittadino;
    }

    public Segnalazione getSegnalazione() {
        return segnalazione;
    }

    public void setSegnalazione(Segnalazione segnalazione) {
        this.segnalazione = segnalazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public Date getDataFeedback() {
        return dataFeedback;
    }

    public void setDataFeedback(Date dataFeedback) {
        this.dataFeedback = dataFeedback;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "cittadino=" + cittadino +
                ", segnalazione=" + segnalazione +
                ", descrizione='" + descrizione + '\'' +
                ", valutazione=" + valutazione +
                ", dataFeedback=" + dataFeedback +
                '}';
    }
}
