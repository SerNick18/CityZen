package model.gestioneDati.modelObjects;

import java.util.Date;

/**
 * Classe che mantiene traccia dell'impiegato che lavora
 * una determinata segnalazione.
 */
public class GestioneSegnalazioni {
    /**
     * Impiegato che effettua una lavorazione.
     */
    private Impiegato impiegato;
    /**
     * Segnalazione che subisce la lavorazione.
     */
    private Segnalazione segnalazione;
    /**
     * Tipo di lavorazione effettuata dall'impiegato.
     */
    private String tipoLavorazione;
    /**
     * Data di effettuazione lavorazione.
     */
    private Date dataLavorazione;
    /**
     * Costruttore con parametri.
     * @param impiegato Impiegato che ha lavorato la segnalazione
     * Precondizione: impiegato != null
     * @param segnalazione Segnalazione lavorata
     * Precondizione: segnalazione != null
     * @param tipoLavorazione Tipo di lavoro
     * effettuato dall'impiegato sulla segnalazione
     * Precondizione: tipoLavorazione != null
     * @param data Data della lavorazione
     * Precondizione: data != null
     */
    public GestioneSegnalazioni(Impiegato impiegato, Segnalazione segnalazione,
                                String tipoLavorazione, Date data) {
        this.impiegato = impiegato;
        this.segnalazione = segnalazione;
        this.tipoLavorazione = tipoLavorazione;
        this.dataLavorazione = data;
    }
    /**
     * Questo metodo restituisce l'impiegato che ha lavorato la segnalazione.
     * @return impiegato che ha lavorato la segnalazione
     */
    public Impiegato getImpiegato() {
        return impiegato; }
    /**
     * Questo metodo sostituisce l'attuale
     * impiegato che ha lavorato la segnalazione
     * con l'impiegato comunale passato come parametro esplicito.
     * @param impiegato nuovo impiegato comunale
     * Precondizione: impiegato != null
     */
    public void setImpiegato(Impiegato impiegato) {
        this.impiegato = impiegato; }
    /**
     * Questo metodo restituisce la segnalazione lavorata dall'impiegato.
     * @return segnalazione lavorata dall'impiegato
     */
    public Segnalazione getSegnalazione() {
        return segnalazione; }
    /**
     * Questo metodo sostituisce l'attuale segnalazione lavorata dall'impiegato
     * con la segnalazione passata come parametro esplicito.
     * @param segnalazione nuova segnalazione
     * Precondizione: segnalazione != null
     */
    public void setSegnalazione(Segnalazione segnalazione) {
        this.segnalazione = segnalazione; }
    /**
     * Questo metodo restituisce il tipo di lavoro
     * effettuato dall'impiegato sulla segnalazione.
     * @return tipo di lavoro effettuato dall'impiegato sulla segnalazione
     */
    public String getTipoLavorazione() {
        return tipoLavorazione; }
    /**
     * Questo metodo sostituisce l'attuale tipo
     * di lavoro effettuato dall'impiegato
     * sulla segnalazione con il tipo di lavoro
     * passato come parametro esplicito.
     * @param tipoLavorazione nuovo tipo di lavoro
     * Precondizione: tipoLavorazione != null
     */
    public void setTipoLavorazione(String tipoLavorazione) {
        this.tipoLavorazione = tipoLavorazione; }
    /**
     * Questo metodo restituisce la data di lavorazione.
     * @return dati di GestioneSegnalazioni formato stringa
     */
    public Date getDataLavorazione() {
        return dataLavorazione;
    }
    /**
     * Questo metodo sostituisce l'attuale data di lavorazione
     * con la data passata come parametro esplicito.
     * @param dataLavorazione nuova data di lavorazione
     * Precondizione: dataLavorazione != null
     */
    public void setDataLavorazione(Date dataLavorazione) {
        this.dataLavorazione = dataLavorazione;
    }
    /**
     * Sovrascrittura metodo toString di Object.
     * @return dati di GestioneSegnalazioni formato stringa
     */
    @Override
    public String toString() {
        return "GestioneSegnalazioni{"
                + "impiegato=" + impiegato
                + ", segnalazione=" + segnalazione
                + ", tipoLavorazione='" + tipoLavorazione
                + '}';
    }
}
