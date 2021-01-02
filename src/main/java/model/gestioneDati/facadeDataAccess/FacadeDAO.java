package model.gestioneDati.facadeDataAccess;

import controller.gestioneUtenza.MyServletException;

import model.gestioneDati.modelDataAccess.CittadinoDAO;
import model.gestioneDati.modelDataAccess.FeedbackDAO;
import model.gestioneDati.modelDataAccess.GestioneSegnalazioniDAO;
import model.gestioneDati.modelDataAccess.ImpiegatoDAO;
import model.gestioneDati.modelDataAccess.SegnalazioneDAO;

import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Feedback;
import model.gestioneDati.modelObjects.Impiegato;
import model.gestioneDati.modelObjects.Segnalazione;
import model.gestioneDati.modelObjects.SegnalazioneInterface;

import java.util.List;
/**
 * Classe che si interfaccia con le varie classi DAO
 * usando il pattern Facade.
 */
public class FacadeDAO {
    /**
     * @see model.gestioneDati.modelDataAccess.CittadinoDAO
     */
    private CittadinoDAO cittadinoDAO;
    /**
     * @see model.gestioneDati.modelDataAccess.SegnalazioneDAO
     */
    private SegnalazioneDAO segnalazioneDAO;
    /**
     * @see model.gestioneDati.modelDataAccess.FeedbackDAO
     */
    private FeedbackDAO feedbackDAO;
    /**
     * @see model.gestioneDati.modelDataAccess.GestioneSegnalazioniDAO
     */
    private GestioneSegnalazioniDAO gestioneSegnalazioneDAO;
    /**
     * @see model.gestioneDati.modelDataAccess.ImpiegatoDAO
     */
    private ImpiegatoDAO impiegatoDAO;

    /**
     * Costruisce un oggetto FacaceDAO e istanzia le varie classi DAO.
     */
    public FacadeDAO() {
        cittadinoDAO = new CittadinoDAO();
        segnalazioneDAO = new SegnalazioneDAO();
        feedbackDAO = new FeedbackDAO();
        gestioneSegnalazioneDAO = new GestioneSegnalazioniDAO();
        impiegatoDAO = new ImpiegatoDAO();
    }
    //Facade per model cittadino
    /**
     *  Il metodo riceve in input la email e la password di
     *  un cittadino e controlla se quel cittadino è presente
     *  nel database, in caso affermativo restituisce il bean
     *  del cittadino, null altrimenti.
     * @param email email del cittadino
     * @param pwd password del cittadino
     * @return bean del cittadino
     */
    public Cittadino loginCittadino(String email, String pwd) {
        return cittadinoDAO.doLogin(email, pwd);
    }
    /**
     * Il metodo restituisce il cittadino corrispondente
     * al codice fiscale passato come parametro se è presente
     * nel database, null altrimenti.
     * @param cf codice fiscale del cittadino
     * @return cittadino
     */
    public Cittadino getCittadinoByCf(String cf) {
        return cittadinoDAO.doRetrieveByCF(cf);
    }
    /**
     * Il metodo, dato un bean di un cittadino
     * lo memorizza nel database.
     * @param c bean cittadino
     */
    public void registraCittadino(Cittadino c) {
        cittadinoDAO.doRegister(c);
    }
    /**
     *  Il metodo riceve in input il codice fiscale
     *  di un cittadino e controlla se quel cittadino
     *  è presente nel database restituendo
     *  il bean del cittadino in caso affermativo,
     *  null altrimenti.
     * @param cf codice fiscale del cittadino
     * @return bean del cittadino
     */
    public Cittadino verificaCodiceFiscale(String cf) {
        return cittadinoDAO.doRetrieveByCF(cf);
    }
    /**
     * Aggiorna la password del cittadino nel database.
     * @param email email del cittadino
     * @param password nuova password del cittadino
     */
    public void doUpdatePasswordByEmail(String email, String password) {
        cittadinoDAO.doUpdatePasswordByEmail(email, password);
    }

    /**
     * Il metodo riceve in input una email e
     * restituisce il cittadino corrispondente a quella mail
     * se presente nel database, null altrimenti.
     * @param email email del cittadino
     * @return cittadino
     */
    public Cittadino verificaEmailCittadino(String email) {
        return cittadinoDAO.doRetrieveByEmail(email);
    }
    /**
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
    /**
     * Il metodo riceve in input il bean di un Cittadino
     * e lo modifica nel database.
     * @param cittadino bean cittadino
     */
    public void modificaCittadino(Cittadino cittadino) {
        cittadinoDAO.doUpdate(cittadino);
    }
    /**
     * Questo metodo richiama la funzionalità
     * di recupero di una lista di cittadini
     * ordiniata in base al numero di
     * segnalazioni approvate.
     * @return la lista ordinata di cittadini
     */
    public List<Cittadino> getCittadiniOrderedBySegnalazioniApp() {
        return cittadinoDAO.doRetrieveByMaxApprovate();
    }
    //Facade per model impiegato
    /**
     *  Il metodo riceve in ingresso una email e una password di
     *  un impiegato e controlla se quel'impiegato è presente
     *  nel database, in caso affermativo restituisce il bean
     *  dell'impiegato, null altrimenti.
     * @param email email dell'impiegato
     * @param pwd password dell'impiegato
     * @return impiegato
     */
    public Impiegato loginImpiegato(String email, String pwd) {
        return impiegatoDAO.doLogin(email, pwd);
    }
    /**
     * Il metodo dato il bean in un impiegato,
     * lo memorizza nel database.
     * @param impiegato bean dell'impiegato
     */
    public void inserisciImpiegato(Impiegato impiegato) {
        impiegatoDAO.doRegister(impiegato);
    }
    /**
     * Il metodo riceve in input il bean di un Impiegato
     * e lo modifica nel database.
     * @param impiegato bean dell'impiegato
     */
    public void modificaImpiegato(Impiegato impiegato) {
        impiegatoDAO.doUpdate(impiegato);
    }

    /**
     * Il metodo riceve in input la matricola
     * di un impiegato e se presente un impiegato
     * corrispondente a quella matricola lo elimina dal database.
     * @param matricola matricola dell'impiegato
     * @throws MyServletException se si verifica un errore
     * nell'eliminazione di un impiegato
     */
    public void eliminaImpiegato(String matricola)
            throws MyServletException {
        impiegatoDAO.doDelete(matricola);
    }
    /**
     * Aggiorna la password dell'impiegato
     * nel database.
     * @param email email dell'impiegato
     * @param pwd nuova password dell'impiegato
     */
    public void doUpdatePasswordByEmailImpiegato(String email, String pwd) {
        impiegatoDAO.doUpdatePasswordByEmail(email, pwd);
    }
    /**
     * Il metodo riceve in input la email di
     * un impiegato e restituisce l'impiegato corrispondente a quella email
     * se presente nel database, null altrimenti.
     * @param email email dell'impiegato
     * @return impiegato
     */
    public Impiegato verificaEmailImpiegato(String email) {
        return impiegatoDAO.doRetrieveByEmail(email);
    }
    //Facade per model segnalazione
    /**
     * Metodo riceve una segnalazione da inserire nel database e
     * richiama la funzionalità di inserimento di una segnalazione.
     * @param segnalazione da inserire nel database
     */
    public void inserisciSegnalazione(Segnalazione segnalazione) {
        segnalazioneDAO.doInsert(segnalazione);
    }
    /**
     * Il metodo riceve in input il codice fiscale di
     * un cittafino e l'offset e restituisce la lista
     * di segnalazioni effettuate dal cittadino.
     * @param cf codice fiscale del cittadino
     * @param offset numero di righe da saltare prima di
     *               iniziare a restituire righe dalla query
     * @return Una lista di SegnalazioniProxy
     */
    public List<SegnalazioneInterface> getSegnalazioneByCittadino(String cf,
                                                                  int offset) {
        return segnalazioneDAO.doRetrieveByCittadino(cf, offset);
    }
    /**
     * Il metodo riceve in input un offset
     * e restituisce la lista delle ultime segnalazioni
     * inoltrate dai cittadini.
     * @param offset numero di righe da saltare prima di
     *               iniziare a restituire righe dalla query
     * @return Una lista di SegnalazioniProxy
     */
    public List<SegnalazioneInterface> getSegnalazioniInoltrate(int offset) {
        return segnalazioneDAO.doRetrieveInoltrateProxy(offset);
    }

    /**
     * Il metodo prende in input lo stato e l'offset
     * e restituisce una lista di segnalazioni con quello stato.
     * @param stato stato delle segnalazioni da restituire
     * @param offset numero di righe da saltare prima di
     *               iniziare a restituire righe dalla query
     * @return lista di segnalazioni interface
     */
    public List<SegnalazioneInterface> getSegnalazioniByStato(String stato,
                                                              int offset) {
        return segnalazioneDAO.doRetrieveByStato(stato, offset);
    }

    /**
     * Il metodo prende in input un id e restituisce
     * la segnalazione corrispondente a quell'id se presente nel database,
     * null altrimenti.
     * @param id id della segnalazione
     * @return segnalazione
     */
    public Segnalazione getSegnalazioneById(int id) {
        return segnalazioneDAO.doRetrieveById(id);
    }
    /**
     * Il metodo riceve in input una segnalazione e la modifica
     * nel database.
     * @param segnalazione bean della segnalazione da modificare
     */
    public void modificaSegnalazione(Segnalazione segnalazione) {
        segnalazioneDAO.doUpdate(segnalazione);
    }

    /**
     * Il metodo riceve in input l'id di una segnalazione,
     * e elimina la segnalazione corrispondente a quell'id
     * se presente nel database, altrimenti lancia un eccezione.
     * @param id id della segnalazione
     * @throws MyServletException si verifica un errore nell'eliminazione
     * di una segnalazione
     */
    public void eliminaSegnalazione(int id) throws MyServletException {
        segnalazioneDAO.doDelete(id);
    }
    //Facade per model gestioneSegnalazioni
    /**
     * Il metodo riceve in input un bean di un impiegato
     * e un bean di una segnalazione e crea una riga
     * nel database nella tabella GestioneSegnalazioni.
     * @param impiegato bean dell'impiegato
     * @param segnalazione bean della segnalazione
     */
    public void inserisciLavorazione(Impiegato impiegato,
                                     Segnalazione segnalazione) {
        gestioneSegnalazioneDAO.doInsert(impiegato, segnalazione);
    }

    /**
     * Il metodo riceve in input l'id di una segnalazione
     * e restituisce la lista di tutti gli impiegati
     * che ci hanno lavorato.
     * @param idSegnalazione id della segnalazione
     * @return lista degli impiegati
     */
    public List<Impiegato> getImpiegatiOsservatori(int idSegnalazione) {
        return gestioneSegnalazioneDAO
                .doRetrieveImpiegatiOsservatori(idSegnalazione);
    }
    //Facade per model feedback
    /**
     * Il metodo riceve l'id di una segnalazione e restituisce tutti
     * i feedback ad essa collegati.
     * @param id id sella segnalazione.
     * @return restituisce una lista di feedback.
     */
    public List<Feedback> getFeedbacksBySegnalazione(int id) {
        return feedbackDAO.doRetrieveFeedBackBySegnalazione(id);
    }
    /**
     * Il metodo riceve un feedback e lo memorizza nel
     * database.
     * @param feedback feedback da memorizzare
     */
    public void doInsertFeedback(Feedback feedback) {
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
    public boolean isCittadinoFeedbackSegnalazione(String cfCittadino,
                                                   int idSegnalazione) {
       return feedbackDAO.isCittadinoFeedbackSegnalazione(cfCittadino,
               idSegnalazione);
    }

}
