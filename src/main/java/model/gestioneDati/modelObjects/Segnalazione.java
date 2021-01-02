package model.gestioneDati.modelObjects;

import java.util.Date;

/**
 * Questa classe rappresenta una segnalazione.
 */
public class Segnalazione extends AbstractSegnalazione
        implements SegnalazioneInterface {
    /**
     * id della segnalazione.
     */
    private int id;
    /**
     * via dove è stato segnalato il problema.
     */
    private String via;
    /**
     * civico dove è stato segnalato il problema.
     */
    private int civico;
    /**
     * priorità della segnalazione.
     */
    private int priorita;
    /**
     * numero di solleciti sulla segnalazione.
     */
    private int numSolleciti;
    /**
     * stato della segnalazione.
     */
    private String stato;
    /**
     * data della segnalazione.
     */
    private Date dataSegnalazione;
    /**
     * oggetto della segnalazione.
     */
    private String oggetto;
    /**
     * descrizione della segnalazione.
     */
    private String descrizione;
    /**
     * foto della segnalazione.
     */
    private String foto;
    /**
     * cittadino che ha effettuato la segnalazione.
     */
    private Cittadino cittadino;
    /**
     * id della segnalazione chiusa a cui
     * si fa riferimento (può essere nullo).
     */
    private int riaperta;
    /**
     * Costruttore con parametri.
     * @param id identificativo della segnalazione
     *           - precondizione: id>0
     * @param via via di dove si è presentato il problema
     *            - precondizione: via!=null
     * @param civico numero civico
     *               - precondizione: civico>0 && civico<5000
     * @param priorita priorità del problema segnalato
     *                 - precondizione: priorità>0 && priorità<5
     * @param numSolleciti numero di solleciti effettuato dai cittadini
     *                     -precondizione: numSolleciti >= 0
     * @param stato stato della segnalazione
     *              - precondizione: stato=="inoltrata"
     *              || stato=="approvata" || stato=="chiusa"
     *              || stato=="rifiutata"
     * @param dataSegnalazione data inoltro della segnalazione
     *                         - precondizione: data!=null
     * @param oggetto oggetto della segnalazione
     *                - precondizione: oggetto!=null
     * @param descrizione descrizione del problema segnalato
     *                    - precondizione: descrizione!=null
     * @param foto foto del problema segnalato
     *             - precondizione: foto!=null
     * @param cittadino cittadino che ha inoltrato la segnalazione
     *                  - precondizione: cittadino!=null
     * @param riaperta identificativo della segnalazione chiusa
     *                 - precondizione: riaperta>0
     */
    public Segnalazione(int id, String via, int civico,
                        int priorita, int numSolleciti,
                        String stato, Date dataSegnalazione, String oggetto,
                        String descrizione, String foto,
                        Cittadino cittadino, int riaperta) {
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
    /**
     * Costruttore vuoto.
     */
    public Segnalazione() {
    }
    /**
     * Questo metodo restituisce l'identificativo della segnalazione.
     * @return id della segnalazione
     */
    public int getId() {
        return id; }
    /**
     * Questo metodo sostituisce l'attuale identificativo della segnalazione
     * con l'identificativo passato come parametro esplicito.
     * @param id nuovo identificativo - precondizione: id>0
     */
    public void setId(int id) {
        this.id = id; }
    /**
     * Questo metodo restituisce la via di dove si è presentato il problema.
     * @return via segnalata
     */
    public String getVia() {
        return via; }
    /**
     * Questo metodo sostituisce l'attuale via di
     * dove si è presentato il problema
     * con la via passata come parametro esplicito.
     * @param via nuova via - precondizione: via!=null
     */
    public void setVia(String via) {
        this.via = via; }
    /**
     * Questo metodo restituisce il numero civico.
     * @return numero civico
     */
    public int getCivico() {
        return civico; }
    /**
     * Questo metodo sostituisce l'attuale numero civico
     * con il numero passato come parametro esplicito.
     * @param civico nuovo numero civico
     *               - precondizione: civico>0 && civico<5000
     */
    public void setCivico(int civico) {
        this.civico = civico; }
    /**
     * Questo metodo restituisce la priorità del problema segnalato.
     * @return priorità del problema segnalato
     */
    public int getPriorita() {
        return priorita; }
    /**
     * Questo metodo sostituisce l'attuale priorità del problema segnalato
     * con la priorità passata come parametro esplicito.
     * @param priorita nuova priorità - precondizione: priorità>0 && priorità<5
     */
    public void setPriorita(int priorita) {
        this.priorita = priorita; }
    /**
     * Questo metodo restituisce il nome del cittadino
     * che ha inoltrato la segnalazione.
     * @return nome del cittadino che ha inoltrato la segnalazione
     */
    public String getNomeCittadino() {
        return cittadino.getNome(); }
    /**
     * Questo metodo restituisce il numero di solleciti
     * effettuato dai cittadini.
     * @return numero di solleciti effettuato dai cittadini.
     */
    public int getNumSolleciti() {
        return numSolleciti; }
    /**
     * Questo metodo sostituisce l'attuale numero di
     * solleciti effettuato dai cittadini
     * con il numero passato come parametro esplicito.
     * @param numSolleciti nuovo numero di solleciti
     */
    public void setNumSolleciti(int numSolleciti) {
        this.numSolleciti = numSolleciti; }
    /**
     * Questo metodo restituisce lo stato della segnalazione.
     * @return stato della segnalazione
     */
    public String getStato() {
        return stato; }
    /**
     * Questo metodo sostituisce l'attuale stato della segnalazione
     * con lo stato passato come parametro esplicito.
     * @param stato nuovo stato - precondizione: stato=="inoltrata"
     * || stato=="approvata" || stato=="chiusa" || stato=="rifiutata".
     */
    public void setStato(String stato) {
        this.stato = stato; }
    /**
     * Questo metodo restituisce la data inoltro della segnalazione.
     * @return data inoltro della segnalazione
     */
    public Date getDataSegnalazione() {
        return dataSegnalazione; }
    /**
     * Questo metodo sostituisce l'attuale data inoltro della segnalazione
     * con la data passata come parametro esplicito.
     * @param dataSegnalazione nuova data - precondizione: data!=null
     */
    public void setDataSegnalazione(Date dataSegnalazione) {
        this.dataSegnalazione = dataSegnalazione; }
    /**
     * Questo metodo restituisce l'oggetto della segnalazione.
     * @return oggetto della segnalazione.
     */
    public String getOggetto() {
        return oggetto; }
    /**
     * Questo metodo sostituisce l'attuale oggetto della segnalazione.
     * con l'oggetto passato come parametro esplicito.
     * @param oggetto nuovo oggetto - precondizione: oggetto!=null.
     */
    public void setOggetto(String oggetto) {
        this.oggetto = oggetto; }
    /**
     * Questo metodo restituisce la descrizione del problema segnalato.
     * @return descrizione del problema segnalato.
     */
    public String getDescrizione() {
        return descrizione; }
    /**
     * Questo metodo sostituisce l'attuale descrizione del problema segnalato
     * con la descrizione passata come parametro esplicito.
     * @param descrizione nuova descrizione - precondizione: descrizione!=null.
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione; }
    /**
     * Questo metodo restituisce la foto del problema segnalato.
     * @return foto del problema segnalato
     */
    public String getFoto() {
        return foto; }
    /**
     * Questo metodo sostituisce l'attuale foto del problema segnalato
     * con la foto passata come parametro esplicito.
     * @param foto nuova foto - precondizione: foto!=null.
     */
    public void setFoto(String foto) {
        this.foto = foto; }
    /**
     * Questo metodo restituisce il cittadino che ha inoltrato la segnalazione.
     * @return cittadino che ha inoltrato la segnalazione
     */
    public Cittadino getCittadino() {
        return cittadino; }
    /**
     * Questo metodo sostituisce l'attuale cittadino
     * che ha inoltrato la segnalazione
     * con il cittadino passato come parametro esplicito.
     * @param cittadino nuovo cittadino - precondizione: cittadino!=null.
     */
    public void setCittadino(Cittadino cittadino) {
        this.cittadino = cittadino; }
    /**
     * Questo metodo restituisce l'identificativo della segnalazione chiusa.
     * @return identificativo della segnalazione chiusa
     */
    public int getRiaperta() {
        return riaperta; }
    /**
     * Questo metodo sostituisce l'attuale identificativo
     * della segnalazione chiusa
     * con l'identificativo passato come parametro esplicito.
     * @param riaperta nuovo identificativo - precondizione: riaperta>0
     */
    public void setRiaperta(int riaperta) {
        this.riaperta = riaperta; }
    /**
     * Sovrascrittura metodo toString di Object.
     * @return dati in formato stringa della segnalazione
     */
    @Override
    public String toString() {
        return "Segnalazione{"
                + "id=" + id
                + ", via='" + via + '\''
                + ", civico=" + civico
                + ", priorità=" + priorita
                + ", numSolleciti=" + numSolleciti
                + ", stato='" + stato + '\''
                + ", dataSegnalazione=" + dataSegnalazione
                + ", oggetto='" + oggetto + '\''
                + ", descrizione='" + descrizione + '\''
                + ", foto='" + foto + '\''
                + ", cittadino=" + cittadino
                + ", riaperta=" + riaperta
                + '}';
    }
}
