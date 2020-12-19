package model.gestioneDati.facadeDataAccess;

import model.gestioneDati.modelDataAccess.CittadinoDAO;
import model.gestioneDati.modelObjects.Cittadino;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacadeDAOTest extends FacadeDAO {
    @Test
    public void controlLogin()
    {
        FacadeDAO f = new FacadeDAO();
        Cittadino c = f.loginCittadino("pippo@alice.it", "1111111");
        assertEquals(new Cittadino(),c);
    }


}