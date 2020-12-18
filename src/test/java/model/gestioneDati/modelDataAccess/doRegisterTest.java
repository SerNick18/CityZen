package model.gestioneDati.modelDataAccess;

import model.gestioneDati.modelObjects.Cittadino;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class doRegisterTest extends CittadinoDAO {

    @Test
    void testDoLogin() {
        assertNull(doLogin("pippo@gmail.com",""));
        assertEquals(new Cittadino("CPNLLD99S19A489D", "Pippo", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","leocapuano0@gmail.com",0,0),
                doLogin("leocapuano0@gmail.com", "Password1!"));
    }

    @Test
    void testDoRegister() {
    }
}