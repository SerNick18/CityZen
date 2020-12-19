package model.gestioneDati.facadeDataAccess;

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

    public Cittadino loginCittadino(String email, String pwd){
        return cittadinoDAO.doLogin(email, pwd);
    }
    public Cittadino getCittadinoByCf(String cf){
        return cittadinoDAO.doRetrieveByCF(cf);
    }
    public Impiegato loginImpiegato(String email, String pwd){
        return impiegatoDAO.doLogin(email, pwd);
    }
    public void registraCittadino(Cittadino c){
        cittadinoDAO.doRegister(c);
    }
    public Cittadino verificaEmail(String email) { return cittadinoDAO.doRetrieveByEmail(email); }
    public Cittadino verificaCodiceFiscale(String cf) { return cittadinoDAO.doRetrieveByCF(cf); }

    public List<SegnalazioneInterface> getSegnalazioniInoltrate(int offset){return segnalazioneDAO.doRetrieveInoltrateProxy(offset);}
    public Segnalazione getSegnalazioneById(int id){return segnalazioneDAO.doRetrieveById(id);}

}
