package model.gestioneDati.facadeDataAccess;

import model.gestioneDati.modelDataAccess.CittadinoDAO;
import model.gestioneDati.modelObjects.Cittadino;

public class FacadeDAO {
    private CittadinoDAO cittadinoDAO;

    public FacadeDAO() {
        cittadinoDAO = new CittadinoDAO();
    }

    public Cittadino login(String email, String pwd){
        return cittadinoDAO.doLogin(email, pwd);
    }

}
