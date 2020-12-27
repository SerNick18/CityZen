package model.gestioneDati.facadeDataAccess;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.modelDataAccess.*;
import model.gestioneDati.modelObjects.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FacadeDAO {
    /**
     *
     */
    private CittadinoDAO cittadinoDAO;
    /**
     *
     */
    private SegnalazioneDAO segnalazioneDAO;
    /**
     *
     */
    private FeedbackDAO feedbackDAO;
    /**
     *
     */
    private GestioneSegnalazioniDAO gestioneSegnalazioneDAO;
    /**
     *
     */
    private ImpiegatoDAO impiegatoDAO;

    /**
     *
     */
    public FacadeDAO() {
        cittadinoDAO = new CittadinoDAO();
        segnalazioneDAO = new SegnalazioneDAO();
        feedbackDAO = new FeedbackDAO();
        gestioneSegnalazioneDAO = new GestioneSegnalazioniDAO();
        impiegatoDAO = new ImpiegatoDAO();
    }

    /**.
     *  Facade per model cittadino
     * @param email
     * @param pwd
     * @return cittadino
     */
    public Cittadino loginCittadino(String email, String pwd) {
        return cittadinoDAO.doLogin(email, pwd);
    }

    /**
     *
     * @param cf
     * @return cittadino
     */
    public Cittadino getCittadinoByCf(String cf) {
        return cittadinoDAO.doRetrieveByCF(cf);
    }

    /**
     *
     * @param c
     */
    public void registraCittadino(Cittadino c) {
        cittadinoDAO.doRegister(c);
    }

    /**
     *
     * @param cf
     * @return cittadino
     */
    public Cittadino verificaCodiceFiscale(String cf) {
        return cittadinoDAO.doRetrieveByCF(cf);
    }

    /**.
     * Aggiorna la password del
     * cittadino nel database
     * @param email email del cittadino
     * @param password nuova password del cittadino
     */
    public void doUpdatePasswordByEmail(String email, String password) {
        cittadinoDAO.doUpdatePasswordByEmail(email, password);
    }

    /**
     *
     * @param email
     * @return cittadino
     */
    public Cittadino verificaEmail(String email) {
        return cittadinoDAO.doRetrieveByEmail(email);
    }
    /**.
     * Il metodo richiama la funzione di
     * eliminazione del cittadino con codice
     * fiscale 'cf' dal database.
     * @param cf del cittadino da eliminare
     * @throws MyServletException se si verifica un errore
     * nell'eliminazione del cittadino
     */
    public void eliminaCittadino(String cf)
            throws MyServletException {
        cittadinoDAO.doDelete(cf);
    }
    public void modificaCittadino(Cittadino cittadino) {
        cittadinoDAO.doUpdate(cittadino);
    }


    /**.
     *Facade per model impiegato
     * @param email
     * @param pwd
     * @return impiegato
     */
    public Impiegato loginImpiegato(String email, String pwd) {
        return impiegatoDAO.doLogin(email, pwd);
    }
    /**
     * Metodo riceve una segnalazione da inserire nel database e
     * richiama la funzionalità di inserimento di una segnalazione.
     * @param segnalazione da inserire nel database
     */
    public void inserisciSegnalazione(Segnalazione segnalazione){
        segnalazioneDAO.doInsert(segnalazione);
    }
    /**
     *
     * @param cf
     * @param offset
     * @return
     */
    public List<SegnalazioneInterface> getSegnalazioneByCittadino(String cf, int offset){
        return segnalazioneDAO.doRetrieveByCittadino(cf, offset);
    }
    /**.
     *Facade per model segnalazione
     * @param offset
     * @return lista di segnalazioni interface
     */
    public List<SegnalazioneInterface> getSegnalazioniInoltrate(int offset) {
        return segnalazioneDAO.doRetrieveInoltrateProxy(offset);
    }

    /**
     *
     * @param stato
     * @param offset
     * @return lista di segnalazioni interface
     */
    public List<SegnalazioneInterface> getSegnalazioniByStato(String stato,
                                                              int offset) {
        return segnalazioneDAO.doRetrieveByStato(stato, offset);
    }

    /**
     *
     * @param id
     * @return segnalazione
     */
    public Segnalazione getSegnalazioneById(int id) {
        return segnalazioneDAO.doRetrieveById(id);
    }
    /**
     *
     * @param segnalazione
     */
    public void modificaSegnalazione(Segnalazione segnalazione) {
        segnalazioneDAO.doUpdate(segnalazione);
    }
    public void eliminaSegnalazione(int ID) throws MyServletException { segnalazioneDAO.doDelete(ID);}

    //Facade per model gestioneSegnalazioni

    /**
     *
     * @param impiegato
     * @param segnalazione
     */
    public void inserisciLavorazione(Impiegato impiegato,
                                     Segnalazione segnalazione) {
        gestioneSegnalazioneDAO.doInsert(impiegato, segnalazione);
    }

    /**
     *
     * @param idSegnalazione
     * @return lista degli impiegati
     */
    public List<Impiegato> getImpiegatiOsservatori(int idSegnalazione) {
        return gestioneSegnalazioneDAO.doRetrieveImpiegatiOsservatori(idSegnalazione);
    }

    //Facade per model feedback

    /**
     * Il metodo riceve l'id di una segnalazione e restituisce tutti
     * i feedback ad essa collegati.
     * @param id id sella segnalazione.
     * @return restituisce una lista di feedback.
     */
    public List<Feedback> getFeedbacksBySegnalazione(int id){
        return feedbackDAO.doRetrieveFeedBackBySegnalazione(id);
    }
    /**
     * Il metodo riceve un feedback e lo memorizza nel
     * database.
     * @param feedback feedback da memorizzare
     */
    public void doInsertFeedback(Feedback feedback){
        feedbackDAO.doInsertFeedback(feedback);
    }

    /**
     * Il metodo verifica se un cittadino ha già inserito
     * un feedback per una determinata segnalazione.
     * @param cfCittadino codice fiscale cittadino
     * @param idSegnalazione id segnalazione
     * @return true - se il cittadino ha già inserito un feedback
     * false - se il cittadino non ha inserito nessun feedback
     */
    public boolean isCittadinoFeedbackSegnalazione(String cfCittadino, int idSegnalazione){
       return feedbackDAO.isCittadinoFeedbackSegnalazione(cfCittadino,idSegnalazione);
    }
}
