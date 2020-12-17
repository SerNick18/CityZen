package Model.GestioneDati.facadeDataAccess;

import Model.GestioneDati.modelDataAccess.CittadinoDAO;
import Model.GestioneDati.modelObjects.Cittadino;

public class FacadeDAO {
    private CittadinoDAO cittadinoDAO;

    public FacadeDAO() {
        cittadinoDAO = new CittadinoDAO();
    }

    public Cittadino login(String email, String pwd){
        return cittadinoDAO.doLogin(email, pwd);
    }

}
