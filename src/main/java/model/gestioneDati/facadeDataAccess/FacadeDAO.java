package model.gestioneDati.facadeDataAccess;

import model.gestioneDati.modelDataAccess.*;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;
/**
 *
 * classe per accedere agli oggetti di un sottosistema.
 *
 * */
public class FacadeDAO {
    /**
     * cittadinoDAO, segnalazioneDAO, feedbackDAO, gestioneSegnalazioneDAO, impiegatoDAO
     * effettuano le chiamate al database.
     *
     * */
    private CittadinoDAO cittadinoDAO;
    private SegnalazioneDAO segnalazioneDAO;
    private FeedbackDAO feedbackDAO;
    private GestioneSegnalazioniDAO gestioneSegnalazioneDAO;
    private ImpiegatoDAO impiegatoDAO;
    /**
     *
     * costruttore vuoto.
     *
     * */
    public FacadeDAO() {
        cittadinoDAO = new CittadinoDAO();
        segnalazioneDAO = new SegnalazioneDAO();
        feedbackDAO = new FeedbackDAO();
        gestioneSegnalazioneDAO = new GestioneSegnalazioniDAO();
        impiegatoDAO = new ImpiegatoDAO();
    }
    /**
     * metodo per effettuare il login per il cittadino.
     *
     * @param email
     * @param pwd: password.
     * @return Cittadino.
     *
     * */
    public Cittadino loginCittadino(String email, String pwd){
        return cittadinoDAO.doLogin(email, pwd);
    }
    /**
     * metodo che effettua il login per l'impiegato.
     *
     * @param email
     * @param pwd: password.
     * @return Impiegato.
     *
     * */
    public Impiegato loginImpiegato(String email, String pwd){
        return impiegatoDAO.doLogin(email, pwd);
    }
    /**
     * metodo per la registrazione di un cittadino.
     *
     * @param c: Cittadino.
     *
     * */
    public void registraCittadino(Cittadino c){
        cittadinoDAO.doRegister(c);
    }
    /**
     * metodo per effettura la verifica di un email già esistente.
     *
     * @param email
     * @return Cittadino.
     *
     * */
    public Cittadino verificaEmail(String email) {
        return cittadinoDAO.doRetrieveByEmail(email);
    }
    /**
     * metodo per effetturare la verifica di un codice fiscale già esistente.
     *
     * @param cf: codice fiscale.
     * @return Cittadino.
     *
     * */
    public Cittadino verificaCodiceFiscale(String cf) {
        return cittadinoDAO.doRetrieveByCF(cf);
    }
}
