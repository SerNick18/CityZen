package model.gestioneDati.facadeDataAccess;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.modelDataAccess.*;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;
import model.gestioneDati.modelObjects.Segnalazione;
import model.gestioneDati.modelObjects.SegnalazioneInterface;

import java.util.List;

public class FacadeDAO {
    private CittadinoDAO cittadinoDAO;
    private SegnalazioneDAO segnalazioneDAO;
    private FeedbackDAO feedbackDAO;
    private GestioneSegnalazioniDAO gestioneSegnalazioneDAO;
    private ImpiegatoDAO impiegatoDAO;


    public FacadeDAO() {
        cittadinoDAO = new CittadinoDAO();
        segnalazioneDAO = new SegnalazioneDAO();
        feedbackDAO = new FeedbackDAO();
        gestioneSegnalazioneDAO = new GestioneSegnalazioniDAO();
        impiegatoDAO = new ImpiegatoDAO();
    }

    //Facade per model cittadino
    public Cittadino loginCittadino(String email, String pwd){
        return cittadinoDAO.doLogin(email, pwd);
    }
    public Cittadino getCittadinoByCf(String cf){
        return cittadinoDAO.doRetrieveByCF(cf);
    }
    public void registraCittadino(Cittadino c){
        cittadinoDAO.doRegister(c);
    }
    public Cittadino verificaCodiceFiscale(String cf) { return cittadinoDAO.doRetrieveByCF(cf); }

    /**
     * Aggiorna la password del
     * cittadino nel database
     * @param email email del cittadino
     * @param password nuova password del cittadino
     */
    public void doUpdatePasswordByEmail(String email, String password){ cittadinoDAO.doUpdatePasswordByEmail(email,password);}
    public Cittadino verificaEmail(String email) { return cittadinoDAO.doRetrieveByEmail(email); }
    /**
     * Il metodo richiama la funzione di
     * eliminazione del cittadino con codice
     * fiscale 'cf' dal database.
     * @param cf del cittadino da eliminare
     * @throws MyServletException se si verifica un errore
     * nell'eliminazione del cittadino
     */
    public void eliminaCittadino(String cf)
            throws MyServletException {cittadinoDAO.doDelete(cf);}

    //Facade per model impiegato
    public Impiegato loginImpiegato(String email, String pwd){
        return impiegatoDAO.doLogin(email, pwd);
    }

    //Facade per model segnalazione
    public List<SegnalazioneInterface> getSegnalazioniInoltrate(int offset){return segnalazioneDAO.doRetrieveInoltrateProxy(offset);}
    public List<SegnalazioneInterface> getSegnalazioniByStato(String stato, int offset){return segnalazioneDAO.doRetrieveByStato(stato, offset);}
    public Segnalazione getSegnalazioneById(int id){return segnalazioneDAO.doRetrieveById(id);}
    public void inserisciSegnalazione(Segnalazione segnalazione){segnalazioneDAO.doInsert(segnalazione);}
    public void setStato(String stato, int id){ segnalazioneDAO.doSetStateById(stato, id);}

    //Facade per model gestioneSegnalazioni
    public void inserisciLavorazione(Impiegato impiegato, String stato, int id){ gestioneSegnalazioneDAO.doInsert(impiegato, stato, id);}
}
