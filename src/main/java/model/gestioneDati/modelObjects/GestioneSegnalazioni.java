package model.gestioneDati.modelObjects;

import java.util.Date;

/**
 * Classe che mantiene traccia dell'impiegato che lavora
 * una determinata segnalazione
 */
public class GestioneSegnalazioni {
    private Impiegato impiegato;
    private Segnalazione segnalazione;
    private String tipoLavorazione;
    private Date dataLavorazione;


    /**
     * Costruttore con parametri
     * @param impiegato Impiegato che ha lavorato la segnalazione
     * @param segnalazione Segnalazione lavorata
     * @param tipoLavorazione Tipo di lavoro effettuato dall'impiegato sulla segnalazione
     */
    public GestioneSegnalazioni(Impiegato impiegato, Segnalazione segnalazione, String tipoLavorazione, Date dataLavorazione) {
        this.impiegato = impiegato;
        this.segnalazione = segnalazione;
        this.tipoLavorazione = tipoLavorazione;
        this.dataLavorazione = dataLavorazione;
    }
    /**
     * Questo metodo restituisce l'impiegato che ha lavorato la segnalazione
     * @return impiegato che ha lavorato la segnalazione
     */
    public Impiegato getImpiegato() { return impiegato; }
    /**
     * Questo metodo sostituisce l'attuale impiegato che ha lavorato la segnalazione
     * con l'impiegato comunale passato come parametro esplicito
     * @param impiegato nuovo impiegato comunale
     */
    public void setImpiegato(Impiegato impiegato) { this.impiegato = impiegato; }
    /**
     * Questo metodo restituisce la segnalazione lavorata dall'impiegato
     * @return segnalazione lavorata dall'impiegato
     */
    public Segnalazione getSegnalazione() { return segnalazione; }
    /**
     * Questo metodo sostituisce l'attuale segnalazione lavorata dall'impiegato
     * con la segnalazione passata come parametro esplicito
     * @param segnalazione nuova segnalazione
     */
    public void setSegnalazione(Segnalazione segnalazione) { this.segnalazione = segnalazione; }
    /**
     * Questo metodo restituisce il tipo di lavoro effettuato dall'impiegato sulla segnalazione
     * @return tipo di lavoro effettuato dall'impiegato sulla segnalazione
     */
    public String getTipoLavorazione() { return tipoLavorazione; }
    /**
     * Questo metodo sostituisce l'attuale tipo di lavoro effettuato dall'impiegato
     * sulla segnalazione con il tipo di lavoro passato come parametro esplicito
     * @param tipoLavorazione nuovo tipo di lavoro
     */
    public void setTipoLavorazione(String tipoLavorazione) { this.tipoLavorazione = tipoLavorazione; }
    /**
     * Sovrascrittura metodo toString di Object
     * @return dati di GestioneSegnalazioni formato stringa
     */
    public Date getDataLavorazione() { return dataLavorazione; }

    public void setDataLavorazione(Date dataLavorazione) { this.dataLavorazione = dataLavorazione; }
    @Override
    public String toString() {
        return "GestioneSegnalazioni{" +
                "impiegato=" + impiegato +
                ", segnalazione=" + segnalazione +
                ", tipoLavorazione='" + tipoLavorazione +
                ", dataLvorazione='" + dataLavorazione +
                '}';
    }
}
