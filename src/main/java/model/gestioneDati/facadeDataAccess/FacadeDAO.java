package model.gestioneDati.facadeDataAccess;

import model.gestioneDati.modelDataAccess.*;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;

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
    public Impiegato loginImpiegato(String email, String pwd){
        return impiegatoDAO.doLogin(email, pwd);
    }
    public void registraCittadino(Cittadino c){
        cittadinoDAO.doRegister(c);
    }

}
