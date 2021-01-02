package model.gestioneDati.modelObjects;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Properties;

/**.
 * Questa classe rappresenta un cittadino.
 */
public class Cittadino implements Observer {
    /**
     * Codice Fiscale del cittadino.
     */
    private String CF;
    /**
     * Nome del cittadino.
     */
    private String nome;
    /**
     * Cognome del cittadino.
     */
    private String cognome;
    /**
     * Password del cittadino.
     */
    private String pwd;
    /**
     * Via del cittadino.
     */
    private String via;
    /**
     * Civico del cittadino.
     */
    private int civico;
    /**
     * Città del cittadino.
     */
    private String citta;
    /**
     * Email del cittadino.
     */
    private String email;
    /**
     * Numero di segnalazioni effettuate dal cittadino.
     */
    private int numSegnalazioni;
    /**
     * Numero di segnalazione approvate del cittadino.
     */
    private int numSegnApp;

    /**
     * Costruttore vuoto.
     */
    public Cittadino() {
    }

    /**
     * Costruttore con parametri.
     * @param CF codice fiscale del cittadino - precondizione: CF != null
     * @param nome nome del cittadino - precondizione: nome != null
     * @param cognome cognome del cittadino - precondizione: cognome != null
     * @param pwd password del cittadino - precondizione: pwd != null
     * @param via via del cittadino - precondizione: via != null
     * @param civico numero civico del cittadino
     * precondizione: civico > 0 && civico < 5000
     * @param citta citta del cittadino - precondizione: citta != null
     * @param email email del cittadino
     * precondizione: email != null
     * @param numSegnalazioni numero di segnalazioni inoltrate dal cittadino
     * precondizione: numSegnalazioni >= 0
     * @param numSegnApp numero di segnalazioni approvate del cittadino
     * precondizione: numSegnApp >= 0
     */
    public Cittadino(String CF, String nome,
                     String cognome, String pwd, String via, int civico,
                     String citta, String email, int numSegnalazioni, int numSegnApp) {
        this.CF = CF;
        this.nome = nome;
        this.cognome = cognome;
        this.pwd = pwd;
        this.via = via;
        this.civico = civico;
        this.citta = citta;
        this.email = email;
        this.numSegnalazioni = numSegnalazioni;
        this.numSegnApp = numSegnApp;
    }

    /**.
     * Questo metodo restituisce il codice fiscale del cittadino
     * @return codice fiscale
     */
    public String getCF() {

        return CF;
    }
    /**.
     * Questo metodo sostituisce il codice fiscale attuale con quello
     * passato come parametro esplicito
     * @param CF nuovo codice fiscale
     */
    public void setCF(String CF) {

        this.CF = CF;
    }

    /**.
     * Questo metodo restituisce il nome del cittadino
     * @return nome del cittadino
     */
    public String getNome() {
        return nome;
    }
    /**.
     * Questo metodo sostituisce il nome attuale del cittadino
     * con il nome passato come parametro esplicito
     * @param nome nuovo nome del cittadino
     */
    public void setNome(String nome) {
        this.nome = nome; }
    /**.
     * Questo metodo restituisce il cognome del cittadino
     * @return cognome del cittadino
     */
    public String getCognome() {
        return cognome;
    }
    /**.
     * Questo metodo sostituisce l'attuale cognome del cittadino
     * con il cognome passato come parametro esplicito
     * @param cognome nuovo cognome del cittadino
     */
    public void setCognome(String cognome) {
        this.cognome = cognome; }
    /**.
     * Questo metodo restituisce la password del cittadino
     * @return password
     */
    public String getPwd() {
        return pwd; }
    /**.
     * Questo metodo sostiuisce l'attuale password del cittadino
     * con la password passata come parametro esplicito
     * @param pwd nuova password
     */
    public void setPwd(String pwd) {
        this.pwd = pwd; }
    /**.
     * Questo metodo restituisce la via del cittadino
     * @return via
     */
    public String getVia() {
        return via; }
    /**.
     * Questo metodo sostituisce la via del cittadino
     * con la via passata come parametro esplicito
     * @param via nuova via
     */
    public void setVia(String via) {
        this.via = via; }
    /**.
     * Questo metodo restituisce il numero civico del cittadino
     * @return numero civico
     */
    public int getCivico() {
        return civico; }
    /**.
     * Questo metodo sostituisce il numero civico del cittadino
     * con il numero civico passato come parametro esplicito
     * @param civico nuovo numero civico
     */
    public void setCivico(int civico) {
        this.civico = civico; }
    /**.
     * Questo metodo restituisce la città del cittadino
     * @return città
     */
    public String getCitta() {
        return citta; }
    /**.
     * Questo metodo sostituisce la città del cittadino
     * con la città passata come parametro esplicito
     * @param citta nuova città
     */
    public void setCitta(String citta) {
        this.citta = citta; }
    /**.
     * Questo metodo restituisce l'email del cittadino
     * @return email
     */
    public String getEmail() {
        return email; }
    /**.
     * Questo metodo sostituisce l'email del cittadino
     * con l'email passata come parametro esplicito
     * @param email nuova email
     */
    public void setEmail(String email) {
        this.email = email; }
    /**.
     * Questo metodo restituisce il numero di segnalazioni
     * inoltrate del cittadino
     * @return numero segnalazioni inoltrate
     */
    public int getNumSegnalazioni() {
        return numSegnalazioni;
    }
    /**.
     * Questo metodo sostituisce il numero di segnalazioni inoltrate
     * del cittadino con il numero di segnalazioni inoltrate passato
     * come parametro esplicito
     * @param numSegnalazioni nuovo numero di segnalazioni inoltrate
     */
    public void setNumSegnalazioni(int numSegnalazioni) {
        this.numSegnalazioni = numSegnalazioni;
    }
    /**.
     * Questo metodo restituisce il numero di segnalazioni
     * approvate del cittadino
     * @return numero segnalazioni approvate
     */
    public int getNumSegnApp() {
        return numSegnApp;
    }
    /**.
     * Questo metodo sostituisce il numero di segnalazioni approvate
     * del cittadino con il numero di segnalazioni approvate passato
     * come parametro esplicito
     * @param numSegnApp nuovo numero di segnalazioni approvate
     */
    public void setNumSegnApp(int numSegnApp) {
        this.numSegnApp = numSegnApp;
    }
    /**.
     * Sovrascrittura metodo toString di Object
     * @return dati in formato stringa del cittadino
     */
    @Override
    public String toString() {
        return "Cittadino{"
                + "email='" + email + '\''
                + ", pwd='" + pwd + '\''
                + ", CF='" + CF + '\''
                + ", nome='" + nome + '\''
                + ", cognome='" + cognome + '\''
                + ", via='" + via + '\''
                + ", civico='" + civico + '\''
                + ", città='" + citta + '\''
                + ", numSegnalazioni=" + numSegnalazioni
                + ", numSegnApp=" + numSegnApp
                + '}';
    }
    /**.
     * Sovrascrittura metodo equals di Object
     * @param o oggetto da confrontare
     * @return se l'oggetto passato come parametro
     * è nello stesso stato del parametro implicito
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cittadino cittadino = (Cittadino) o;
        return civico == cittadino.civico && numSegnalazioni
                == cittadino.numSegnalazioni && numSegnApp
                == cittadino.numSegnApp
                && Objects.equals(CF, cittadino.CF)
                && Objects.equals(nome, cittadino.nome)
                && Objects.equals(cognome, cittadino.cognome)
                && Objects.equals(pwd, cittadino.pwd)
                && Objects.equals(via, cittadino.via)
                && Objects.equals(citta, cittadino.citta)
                && Objects.equals(email, cittadino.email);
    }
    /**.
     * Sovrascrittura del metodo hashCode di Object
     * @return valore hash per il cittadino
     */
    @Override
    public int hashCode() {
        return Objects.hash(CF, nome, cognome, pwd, via,
                civico, citta, email, numSegnalazioni, numSegnApp);
    }

    /**
     * Il metodo manda un email al cittadino
     * sull'eventuale cambiamento di stato della segnalazione
     * da lui precedentemente inoltrata.
     * @param s segnalazione del cittadino
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
        Session sessione = Session.getDefaultInstance(p,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                            "progettoC04@gmail.com", "TestProgetto4");
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
