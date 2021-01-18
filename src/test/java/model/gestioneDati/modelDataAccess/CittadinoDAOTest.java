package model.gestioneDati.modelDataAccess;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.modelObjects.Cittadino;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class CittadinoDAOTest extends CittadinoDAO {

    static CittadinoDAO cittadinoDAO;
    static Cittadino cittadino;

    @BeforeEach
    void setUp() {
        cittadinoDAO = new CittadinoDAO();
        cittadino = new Cittadino("LPNLLD11S19B333D", "Geppe", "Fatta", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cgesu@gmail.com",0,0);
        cittadinoDAO.doRegister(cittadino);
    }

    @Test
   void doLoginTest(){
        assertDoesNotThrow(()->{cittadinoDAO.doLogin(cittadino.getEmail(), cittadino.getPwd());});
    }

    @Test
    void doRetrieveByEmailTest(){
        assertDoesNotThrow(()->{cittadinoDAO.doRetrieveByEmail(cittadino.getEmail());});
    }

    @Test
    void doRetrieveByCFTest(){
        assertDoesNotThrow(()->{cittadinoDAO.doRetrieveByCF(cittadino.getCF());});
    }

    @Test
    void doRegisterTest() throws MyServletException{
        cittadinoDAO.doDelete(cittadino.getCF());
        assertDoesNotThrow(()->{cittadinoDAO.doRegister(cittadino);});
    }

    @Test
    void doRetrieveMaxApprovateTest(){
        assertDoesNotThrow(()->{cittadinoDAO.doRetrieveByMaxApprovate();});
    }

    @Test
    void testDoDelete() {
        assertThrows(MyServletException.class, ()->{cittadinoDAO.doDelete("ASDFGHJKLOIUYTRE");});
    }

    @Test
    void doRegister() throws MyServletException {
        cittadinoDAO.doDelete(cittadino.getCF());
        assertDoesNotThrow(()->{cittadinoDAO.doRegister(cittadino);});
    }

    @Test
    void doUpdate(){
        cittadino.setNome("Alessia");
        assertDoesNotThrow(()->{cittadinoDAO.doUpdate(cittadino);});
    }

    @Test
    void doUpdatePassword(){
        cittadino.setPwd("33ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573");
        assertDoesNotThrow(()->{cittadinoDAO.doUpdatePasswordByEmail(cittadino.getEmail(), cittadino.getPwd());});
    }

    @AfterEach
    void tearDown() throws MyServletException {
        cittadinoDAO.doDelete(cittadino.getCF());
    }

}