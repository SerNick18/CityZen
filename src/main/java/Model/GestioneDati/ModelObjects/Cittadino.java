package Model.GestioneDati.ModelObjects;

/**
 *
 */
public class Cittadino {
    private String CF;
    private String nome;
    private String cognome;
    private String pwd;
    private String via;
    private int civico;
    private String citta;
    private String email;
    private int numSegnalazioni;
    private int numSegnApp;

    public Cittadino(){}

    /**
     * descrizione
     * @param CF webofcbew
     * @param nome cwvcdsvc
     * @param cognome cwvcwv
     * @param pwd cwcwe
     * @param via cww
     * @param civico cwvcw
     * @param citta wcvwcv
     * @param email wvcwrvc
     * @param numSegnalazioni vcwvcwr
     * @param numSegnApp cwvcwvc
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

    /**
     *
     * @return vwvcw
     */
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumSegnalazioni() {
        return numSegnalazioni;
    }

    public void setNumSegnalazioni(int numSegnalazioni) {
        this.numSegnalazioni = numSegnalazioni;
    }

    public int getNumSegnApp() {
        return numSegnApp;
    }

    public void setNumSegnApp(int numSegnApp) {
        this.numSegnApp = numSegnApp;
    }

    @Override
    public String toString() {
        return "Cittadino{" +
                "email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", CF='" + CF + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", via='" + via + '\'' +
                ", civico='" + civico + '\'' +
                ", città='" + citta + '\'' +
                ", numSegnalazioni=" + numSegnalazioni +
                ", numSegnApp=" + numSegnApp +
                '}';
    }


}
