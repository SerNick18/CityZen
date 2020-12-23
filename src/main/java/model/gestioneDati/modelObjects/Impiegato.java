package model.gestioneDati.modelObjects;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Questa classe rappresenta un impiegato comunale
 */
public class Impiegato implements Observer {
    private String email;
    private String matricola;
    private String pwd;
    private String CF;
    private String nome;
    private String cognome;
    private String via;
    private int civico;
    private String citta;
    private int numSegnalazioniApp;
    private int numSegnalazioniChiuse;
    /**
     * Costruttore vuoto
     */
    public Impiegato() { }
    /**
     * Costruttore con parametri
     * @param email email dell'impiegato comunale
     * @param matricola numero di matricola dell'impiegato comunale
     * @param pwd password dell'impiegato comunale
     * @param CF codice fiscale dell'impiegato comunale
     * @param nome nome dell'impiegato comunale
     * @param cognome cognome dell'impiegato comunale
     * @param via via
     * @param civico numero civico
     * @param citta città
     * @param numSegnalazioniApp numero di segnalazioni approvate
     * @param numSegnalazioniChiuse numero di segnalazioni chiuse
     */
    public Impiegato(String email, String matricola, String pwd, String CF,
                     String nome, String cognome, String via,
                     int civico, String citta, int numSegnalazioniApp, int numSegnalazioniChiuse) {
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
     * Questo metodo restituisce l'email dell'impiegato comunale
     * @return email dell'impiegato comunale
     */
    public String getEmail() {
        return email;
    }
    /**
     * Questo metodo sostituisce l'attuale email dell'impiegato comunale
     * con l'email passata come parametro esplicito
     * @param email nuova email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Questo metodo restituisce il numero di matricola dell'impiegato comunale
     * @return numero di matricola dell'impiegato comunale
     */
    public String getMatricola() { return matricola; }
    /**
     * Questo metodo sostituisce l'attuale numero di matricola dell'impiegato comunale
     * con il numero di matricola passato come parametro esplicito
     * @param matricola nuovo numero di matricola
     */
    public void setMatricola(String matricola) { this.matricola = matricola; }
    /**
     * Questo metodo restituisce la password dell'impiegato comunale
     * @return password dell'impiegato comunale
     */
    public String getPwd() { return pwd; }
    /**
     * Questo metodo sostituisce l'attuale password dell'impiegato comunale
     * con la password passata come parametro esplicito
     * @param pwd nuova password
     */
    public void setPwd(String pwd) { this.pwd = pwd; }
    /**
     * Questo metodo restituisce il codice fiscale dell'impiegato comunale
     * @return codice fiscale dell'impiegato comunale
     */
    public String getCF() { return CF; }
    /**
     * Questo metodo sostituisce l'attuale codice fiscale dell'impiegato comunale
     * con il codice fiscale passato come parametro esplicito
     * @param CF nuovo codice fiscale
     */
    public void setCF(String CF) { this.CF = CF; }
    /**
     * Questo metodo restituisce il nome dell'impiegato comunale
     * @return nome dell'impiegato comunale
     */
    public String getNome() { return nome; }
    /**
     * Questo metodo sostituisce l'attuale nome dell'impiegato comunale
     * con il nome passato come parametro esplicito
     * @param nome nuovo nome
     */
    public void setNome(String nome) { this.nome = nome; }
    /**
     * Questo metodo restituisce il cognome dell'impiegato comunale
     * @return cognome dell'impiegato comunale
     */
    public String getCognome() { return cognome; }
    /**
     * Questo metodo sostituisce l'attuale cognome dell'impiegato comunale
     * con il cognome passato come parametro esplicito
     * @param cognome nuovo cognome
     */
    public void setCognome(String cognome) { this.cognome = cognome; }
    /**
     * Questo metodo restituisce la via
     * @return via
     */
    public String getVia() { return via; }
    /**
     * Questo metodo sostituisce l'attuale via con la via passata
     * come parametro esplicito
     * @param via nuova via
     */
    public void setVia(String via) { this.via = via; }
    /**
     * Questo metodo restituisce il numero civico
     * @return numero civico
     */
    public int getCivico() { return civico; }
    /**
     * Questo metodo sostituisce l'attuale numero civico con il numero
     * civico passato come parametro esplicito
     * @param civico nuovo numero civico
     */
    public void setCivico(int civico) { this.civico = civico; }
    /**
     * Questo metodo restituisce la città
     * @return città
     */
    public String getCitta() { return citta; }
    /**
     * Questo metodo sostituisce l'attuale città con la città passata
     * come parametro esplicito
     * @param citta nuova città
     */
    public void setCitta(String citta) { this.citta = citta; }
    /**
     * Questo metodo restituisce il numero di segnalazioni approvate
     * @return numero di segnalazioni approvate
     */
    public int getNumSegnalazioniApp() { return numSegnalazioniApp; }
    /**
     * Questo metodo sostituisce l'attuale numero di segnalazioni approvate
     * con il numero passato come parametro esplicito
     * @param numSegnalazioniApp nuovo numero segnalazioni approvate
     */
    public void setNumSegnalazioniApp(int numSegnalazioniApp) { this.numSegnalazioniApp = numSegnalazioniApp; }
    /**
     * Questo metodo restituisce il numero di segnalazioni chiuse
     * @return numero di segnalazioni chiuse
     */
    public int getNumSegnalazioniChiuse() { return numSegnalazioniChiuse; }
    /**
     * Questo metodo sostituisce l'attuale numero di segnalazioni chiuse
     * con il numero passato come parametro esplicito
     * @param numSegnalazioniChiuse nuovo numero segnalazioni chiuse
     */
    public void setNumSegnalazioniChiuse(int numSegnalazioniChiuse) { this.numSegnalazioniChiuse = numSegnalazioniChiuse; }
    /**
     * Sovrascrittura metodo toString di Object
     * @return dati dell'impiegato formato stringa
     */
    @Override
    public String toString() {
        return "Impiegato{" +
                "email='" + email + '\'' +
                ", matricola='" + matricola + '\'' +
                ", pwd='" + pwd + '\'' +
                ", CF='" + CF + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", via='" + via + '\'' +
                ", civico='" + civico + '\'' +
                ", città='" + citta + '\'' +
                ", numSegnalazioniApp=" + numSegnalazioniApp +
                ", numSegnalazioniChiuse=" + numSegnalazioniChiuse +
                '}';
    }
    /**
     *
     * @param s
     */
    @Override
    public void update(AbstractSegnalazione s) {
        //mail
        final int port = 587;
        Segnalazione segnalazione = (Segnalazione) s;
        System.out.println("è stata modificata la segnalazione "+segnalazione.getOggetto()
                +" con lo stato "+segnalazione.getStato());
        String host = "smtp.gmail.com";
        String oggetto = "Reimposta la password";
        String testo = "Gentile Utente, "
                + "Puoi reimpostare la tua password premendo "
                + "il pulsante di seguito: \n"
                + "<form action=\"http://localhost:8080"
                + "/CityZen_war_exploded/reimposta-password\" "
                + "method=\"post\">\n"
                + "    <input type=\"hidden\" name=\"email\""
                + " value=\"" + email + "\" />\n"
                + "    <input type=\"hidden\" name=\"provenienza\""
                + " value=\"email\" />\n"
                + "    <button>Reimposta Password</button>\n"
                + "</form>";
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true"); //enable authentication
        p.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        p.put("mail.smtp.host", host);
        p.put("mail.smtp.port", port);
        Session sessione = Session.getDefaultInstance(p,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("progettoC04@gmail.com",
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
