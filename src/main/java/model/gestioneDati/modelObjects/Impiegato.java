package model.gestioneDati.modelObjects;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public int getNumSegnalazioniApp() {
        return numSegnalazioniApp;
    }

    public void setNumSegnalazioniApp(int numSegnalazioniApp) {
        this.numSegnalazioniApp = numSegnalazioniApp;
    }

    public int getNumSegnalazioniChiuse() {
        return numSegnalazioniChiuse;
    }

    public void setNumSegnalazioniChiuse(int numSegnalazioniChiuse) {
        this.numSegnalazioniChiuse = numSegnalazioniChiuse;
    }

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

    @Override
    public void update(AbstractSegnalazione s) {
        //mail
        Segnalazione segnalazione = (Segnalazione) s;
        System.out.println("è stata modificata la segnalazione "+segnalazione.getOggetto()
                +" con lo stato "+segnalazione.getStato());
    }
}
