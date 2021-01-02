package model.gestioneDati.modelObjects;

import java.util.Date;

/**
 * Questa classe rappresenta un feedback.
 */
public class Feedback {
    /**
     * Cittadino che deve scrivere il feedback.
     */
    private Cittadino cittadino;
    /**
     * Segnalazione a cui deve riferirsi il feedback.
     */
    private Segnalazione segnalazione;
    /**
     * Descrizione del feedback.
     */
    private String descrizione;
    /**
     * Valutazione alla risoluzione del feedback.
     */
    private int valutazione;
    /**
     * Data in cui è stato sottomesso il feedback.
     */
    private Date dataFeedback;

    /**
     * Costruttore vuoto.
     */
    public Feedback() { }

    /**
     * Costruttori con parametri.
     * @param cittadino Cittadino che ha scritto il feedback
     * - Precondizione: cittadino != null
     * @param segnalazione Segnalazione a cui si riferisce il feedback
     * - Precondizione: segnalazione != null
     * @param descrizione Descrizione del feedback
     * - Precondizione: descrizione != null
     * @param valutazione Valutazione della risoluzione della segnalazione
     * - Precondizione: valutazione != null
     * @param dataFeedback Data in cui è stato sottomesso il feedback
     * - Precondizione: dataFeedback != null
     */
    public Feedback(Cittadino cittadino, Segnalazione segnalazione,
                    String descrizione, int valutazione, Date dataFeedback) {
        this.cittadino = cittadino;
        this.segnalazione = segnalazione;
        this.descrizione = descrizione;
        this.valutazione = valutazione;
        this.dataFeedback = dataFeedback;
    }
    /**
     * Questo metodo restiuisce il cittadino che ha scritto il feedback.
     * @return cittadino che ha scritto il feedback
     */
    public Cittadino getCittadino() {
        return cittadino;
    }
    /**
     * Questo metodo sostituisce l'attuale cittadino che ha scritto il feedback
     * con il cittadino passato come parametro esplicito.
     * @param cittadino - Postcondizione: nuovo cittadino
     */
    public void setCittadino(Cittadino cittadino) {
        this.cittadino = cittadino;
    }
    /**
     * Questo metodo restituisce la segnalazione a cui è riferito il feedback.
     * @return segnalazione a cui è riferito il feedback
     */
    public Segnalazione getSegnalazione() {
        return segnalazione;
    }

    /**
     * Questo metodo sostituisce l'attuale
     * segnalazione a cui è riferito il feedback
     * con la segnalazione passata come parametro esplicito.
     * @param segnalazione - Postcondizione: nuova segnalazione
     */
    public void setSegnalazione(Segnalazione segnalazione) {
        this.segnalazione = segnalazione; }
    /**
     * Questo metodo restituisce la descrizione del feedback.
     * @return descrizione del feedback
     */
    public String getDescrizione() {
        return descrizione; }
    /**
     * Questo metodo sostituisce l'attuale descrizione del feedback
     * con la descrizione passata come parametro esplicito.
     * @param descrizione - Postcondizione: nuova descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione; }
    /**
     * Questo metodo restituisce la valutazione
     * sulla risoluzione della segnalazione.
     * @return un numero che rappresenta la valutazione
     */
    public int getValutazione() {
        return valutazione; }
    /**
     * Questo metodo sostituisce l'attuale valutazione
     * sulla risoluzione della segnalazione.
     * con la valutazione passata come parametro esplicito
     * @param valutazione - PostCondizione: nuova valutazione
     */
    public void setValutazione(int valutazione) {
        this.valutazione = valutazione; }
    /**
     * Questo metodo restituisce la data in cui è stato sottomesso il feedback.
     * @return data in cui è stato sottomesso il feedback
     */
    public Date getDataFeedback() {
        return dataFeedback; }
    /**
     * Questo metodo sostituisce l'attuale data
     * in cui è stato sottomesso il feedback
     * con la data passata come parametro esplicito.
     * @param dataFeedback - Postcondizione: nuova data
     */
    public void setDataFeedback(Date dataFeedback) {
        this.dataFeedback = dataFeedback;
    }

    /**
     *
     * @return dati in formato stringa di un feedback
     */
    @Override
    public String toString() {
        return "Feedback{"
                + "cittadino=" + cittadino
                + ", segnalazione=" + segnalazione
                + ", descrizione='" + descrizione + '\''
                + ", valutazione=" + valutazione
                + ", dataFeedback=" + dataFeedback
                + '}';
    }
}
