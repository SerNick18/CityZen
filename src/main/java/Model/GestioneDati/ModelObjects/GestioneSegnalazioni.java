package Model.GestioneDati.ModelObjects;

import java.util.Date;

public class GestioneSegnalazioni {
    private Impiegato impiegato;
    private Segnalazione segnalazione;
    private String tipoLavorazione;
    private Date dataFeedback;

    public GestioneSegnalazioni(Impiegato impiegato, Segnalazione segnalazione, String tipoLavorazione, Date dataFeedback) {
        this.impiegato = impiegato;
        this.segnalazione = segnalazione;
        this.tipoLavorazione = tipoLavorazione;
        this.dataFeedback = dataFeedback;
    }


    public Impiegato getImpiegato() {
        return impiegato;
    }

    public void setImpiegato(Impiegato impiegato) {
        this.impiegato = impiegato;
    }

    public Segnalazione getSegnalazione() {
        return segnalazione;
    }

    public void setSegnalazione(Segnalazione segnalazione) {
        this.segnalazione = segnalazione;
    }

    public String getTipoLavorazione() {
        return tipoLavorazione;
    }

    public void setTipoLavorazione(String tipoLavorazione) {
        this.tipoLavorazione = tipoLavorazione;
    }

    public Date getDataFeedback() {
        return dataFeedback;
    }

    public void setDataFeedback(Date dataFeedback) {
        this.dataFeedback = dataFeedback;
    }

    @Override
    public String toString() {
        return "GestioneSegnalazioni{" +
                "impiegato=" + impiegato +
                ", segnalazione=" + segnalazione +
                ", tipoLavorazione='" + tipoLavorazione + '\'' +
                ", dataFeedback=" + dataFeedback +
                '}';
    }
}
