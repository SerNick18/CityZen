package model.gestioneDati.modelObjects;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Questa classe rappresenta un impiegato comunale.
 */
public class Impiegato implements Observer {
    /**
     * Email dell'impiegato.
     */
    private String email;
    /**
     * Numero di matricola dell'impiegato.
     */
    private String matricola;
    /**
     * Password dell'impiegato.
     */
    private String pwd;
    /**
     * Codice Fiscale dell'impiegato.
     */
    private String CF;
    /**
     * Nome dell'impiegato.
     */
    private String nome;
    /**
     * Cognome dell'impiegato.
     */
    private String cognome;
    /**
     * Via dove vive l'impiegato.
     */
    private String via;
    /**
     * Numero civico dell'abitazione dell'impiegato.
     */
    private int civico;
    /**
     * Città in cui vive l'impiegato.
     */
    private String citta;
    /**
     * Numero segnalazioni approvate dell'impiegato.
     */
    private int numSegnalazioniApp;
    /**
     * Numero di segnalazioni chiuse dell'impiegato.
     */
    private int numSegnalazioniChiuse;
    /**
     * Costruttore vuoto.
     */
    public Impiegato() { }
    /**
     * Costruttore con parametri.
     * @param email email dell'impiegato comunale - Precondizione: email != null
     * @param matricola numero di matricola dell'impiegato comunale
     * - Precondizione: matricola != null
     * @param pwd password dell'impiegato comunale - Precondizione: pwd != null
     * @param CF codice fiscale dell'impiegato comunale
     * - Precondizione: CF != null
     * @param nome nome dell'impiegato comunale - Precondizione: nome != null
     * @param cognome cognome dell'impiegato comunale
     * - Precondizione: cognome != null
     * @param via via - Precondizione: via != null
     * @param civico numero civico - Precondizione civico > 0 && civico < 5000
     * @param citta città - Precondizione città != null
     * @param numSegnalazioniApp numero di segnalazioni approvate
     * - Precondizione: numSegnalazioniApp >= 0
     * @param numSegnalazioniChiuse numero di segnalazioni chiuse
     * - Precondizione: numSegnalazioniChiuse >= 0
     */
    public Impiegato(String email, String matricola, String pwd, String CF,
                     String nome, String cognome, String via,
                     int civico, String citta,
                     int numSegnalazioniApp, int numSegnalazioniChiuse) {
        this.email = email;
        this.matricola = matricola;
        this.pwd = pwd;
        this.CF = CF;
        this.nome = nome;
        this.cognome = cognome;
        this.via = via;
        this.civico = civico;
        this.citta = citta;
        this.numSegnalazioniApp = numSegnalazioniApp;
        this.numSegnalazioniChiuse = numSegnalazioniChiuse;
    }
    /**
     * Questo metodo restituisce l'email dell'impiegato comunale.
     * @return email dell'impiegato comunale
     */
    public String getEmail() {
        return email;
    }
    /**
     * Questo metodo sostituisce l'attuale email dell'impiegato comunale
     * con l'email passata come parametro esplicito.
     * @param email - Postcondizione: nuova email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Questo metodo restituisce il numero di matricola dell'impiegato comunale.
     * @return numero di matricola dell'impiegato comunale
     */
    public String getMatricola() {
        return matricola; }
    /**
     * Questo metodo sostituisce l'attuale numero di matricola
     * dell'impiegato comunale con il numero di matricola
     * passato come parametro esplicito.
     * @param matricola - Postcondizione: nuovo numero di matricola
     */
    public void setMatricola(String matricola) {
        this.matricola = matricola; }
    /**
     * Questo metodo restituisce la password dell'impiegato comunale.
     * @return password dell'impiegato comunale
     */
    public String getPwd() {
        return pwd; }
    /**
     * Questo metodo sostituisce l'attuale password dell'impiegato comunale
     * con la password passata come parametro esplicito.
     * @param pwd - Postcondizione: nuova password
     */
    public void setPwd(String pwd) {
        this.pwd = pwd; }
    /**
     * Questo metodo restituisce il codice fiscale dell'impiegato comunale.
     * @return codice fiscale dell'impiegato comunale
     */
    public String getCF() {
        return CF; }
    /**
     * Questo metodo sostituisce l'attuale codice fiscale
     * dell'impiegato comunale con il codice fiscale
     * passato come parametro esplicito.
     * @param CF - Postcondizione: nuovo codice fiscale
     */
    public void setCF(String CF) {
        this.CF = CF; }
    /**
     * Questo metodo restituisce il nome dell'impiegato comunale.
     * @return nome dell'impiegato comunale
     */
    public String getNome() {
        return nome; }
    /**
     * Questo metodo sostituisce l'attuale nome dell'impiegato comunale
     * con il nome passato come parametro esplicito.
     * @param nome - Postcondizione: nuovo nome
     */
    public void setNome(String nome) {
        this.nome = nome; }
    /**
     * Questo metodo restituisce il cognome dell'impiegato comunale.
     * @return cognome dell'impiegato comunale
     */
    public String getCognome() {
        return cognome; }
    /**
     * Questo metodo sostituisce l'attuale cognome dell'impiegato comunale
     * con il cognome passato come parametro esplicito.
     * @param cognome - Postcondizione: nuovo cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome; }
    /**
     * Questo metodo restituisce la via.
     * @return via
     */
    public String getVia() {
        return via; }
    /**
     * Questo metodo sostituisce l'attuale via con la via passata
     * come parametro esplicito.
     * @param via - Postcondizione: nuova via
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
     * Questo metodo sostituisce l'attuale numero civico con il numero
     * civico passato come parametro esplicito.
     * @param civico - Postcondizione: nuovo numero civico
     */
    public void setCivico(int civico) {
        this.civico = civico; }
    /**
     * Questo metodo restituisce la città.
     * @return città
     */
    public String getCitta() {
        return citta; }
    /**
     * Questo metodo sostituisce l'attuale città con la città passata
     * come parametro esplicito.
     * @param citta - Postcondizione: nuova città
     */
    public void setCitta(String citta) {
        this.citta = citta; }
    /**
     * Questo metodo restituisce il numero di segnalazioni approvate.
     * @return numero di segnalazioni approvate
     */
    public int getNumSegnalazioniApp() {
        return numSegnalazioniApp; }
    /**
     * Questo metodo sostituisce l'attuale numero di segnalazioni approvate
     * con il numero passato come parametro esplicito.
     * @param numSegnalazioniApp
     * - Postcondizione: nuovo numero segnalazioni approvate
     */
    public void setNumSegnalazioniApp(int numSegnalazioniApp) {
        this.numSegnalazioniApp = numSegnalazioniApp; }
    /**
     * Questo metodo restituisce il numero di segnalazioni chiuse.
     * @return numero di segnalazioni chiuse
     */
    public int getNumSegnalazioniChiuse() {
        return numSegnalazioniChiuse; }
    /**
     * Questo metodo sostituisce l'attuale numero di segnalazioni chiuse
     * con il numero passato come parametro esplicito.
     * @param numSegnalazioniChiuse
     * - Postcondizione: nuovo numero segnalazioni chiuse
     */
    public void setNumSegnalazioniChiuse(int numSegnalazioniChiuse) {
        this.numSegnalazioniChiuse = numSegnalazioniChiuse; }
    /**
     * Sovrascrittura metodo toString di Object.
     * @return dati dell'impiegato formato stringa
     */
    @Override
    public String toString() {
        return "Impiegato{"
                + "email='" + email + '\''
                + ", matricola='" + matricola + '\''
                + ", pwd='" + pwd + '\''
                + ", CF='" + CF + '\''
                + ", nome='" + nome + '\''
                + ", cognome='" + cognome + '\''
                + ", via='" + via + '\''
                + ", civico='" + civico + '\''
                + ", città='" + citta + '\''
                + ", numSegnalazioniApp=" + numSegnalazioniApp
                + ", numSegnalazioniChiuse=" + numSegnalazioniChiuse
                + '}';
    }
    /**
     * Il metodo manda un'email all'impiegato sul
     * cambiamento di stato di una segnalazione
     * a tutti coloro che ci hanno lavorato.
     * @param s segnalazione
     */
    @Override
    public void update(AbstractSegnalazione s) {
        //mail
        final int port = 587;
        Segnalazione segnalazione = (Segnalazione) s;
        String host = "smtp.gmail.com";
        String oggetto = "Notifica Segnalazione #" + segnalazione.getId();
        String testo = "La segnalazione è stata " + segnalazione.getStato();
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true"); //enable authentication
        p.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        p.put("mail.smtp.host", host);
        p.put("mail.smtp.port", port);
        Session sessione = Session.getDefaultInstance(p, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                "progettoC04@gmail.com",
                                "TestProgetto4");
                    }
                });
        MimeMessage mail = new MimeMessage(sessione);
        try {
            mail.setFrom(new InternetAddress("no-reply@scafati.it"));
            mail.addRecipients(Message.RecipientType.TO, this.getEmail());
            mail.setSubject(oggetto);
            mail.setContent(testo, "text/html");
            Transport.send(mail);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
