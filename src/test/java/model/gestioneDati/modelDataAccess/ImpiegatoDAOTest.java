package model.gestioneDati.modelDataAccess;


import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Impiegato;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImpiegatoDAOTest extends ImpiegatoDAO{
     ImpiegatoDAO impDAO;
     static Impiegato impiegato;
     static FacadeDAO service;


    @BeforeEach
    void setUp() {
        impDAO = new ImpiegatoDAO();
        impiegato = new Impiegato("abc@scafati.it","MAT365",
                "Cityzen10!","MPLGEL80A09H387H","Pippo","Pippo","mercato",1,"Fisciano",0,0);
        service = new FacadeDAO();
        service.inserisciImpiegato(impiegato);
    }
    @AfterEach
    void tearDown() throws MyServletException {
        service.eliminaImpiegato(impiegato.getMatricola());
    }

    @Test
    void doRegisterTest() throws MyServletException {
        service.eliminaImpiegato(impiegato.getMatricola());
        assertDoesNotThrow(()->{impDAO.doRegister(impiegato);});
    }

    @Test
    void doLoginTest() {
        assertDoesNotThrow(()->{impDAO.doLogin(impiegato.getEmail(), impiegato.getPwd());});
    }

    @Test
    void doUpdatePasswordByEmail() {
        assertDoesNotThrow(()->{impDAO.doUpdatePasswordByEmail(impiegato.getEmail(), impiegato.getPwd());});
    }

    @Test
    void doUpdateTest() {
        assertDoesNotThrow(()->{impDAO.doUpdate(impiegato);});
    }

    @Test
    void doRetrieveByEmail() {
        assertDoesNotThrow(()->{impDAO.doRetrieveByEmail(impiegato.getEmail());});
    }
}
