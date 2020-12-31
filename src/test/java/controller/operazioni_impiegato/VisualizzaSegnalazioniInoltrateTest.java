package controller.operazioni_impiegato;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;
import model.gestioneDati.modelObjects.Segnalazione;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

public class VisualizzaSegnalazioniInoltrateTest extends VisualizzaSegnalazioniInoltrate{
    VisualizzaSegnalazioniInoltrate servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    static FacadeDAO service;
    static Impiegato impiegato;

    @BeforeEach
    void setUpEach() {
        servlet = new VisualizzaSegnalazioniInoltrate();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
        impiegato = new Impiegato("abc@scafati.it","MAT365",
                "Cityzen10!","MPLGEL80A09H387H","Pippo","Pippo","mercato",1,"Fisciano",0,0);
        request.getSession().setAttribute("Impiegato", impiegato);
    }

    @BeforeAll
    static void setUpAll() {
        service = new FacadeDAO();
        impiegato = new Impiegato("abc@scafati.it", "MAT365",
                "Cityzen10!", "MPLGEL80A09H387H", "Pippo", "Pippo", "mercato", 1, "Fisciano", 0, 0);
        service.inserisciImpiegato(impiegato);
    }

    @Test
    void TestImpiegatoNull() {
        request.getSession().removeAttribute("Impiegato");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Per visualizzare "
                        + "le segnalazioni inoltrate "
                        + "bisogna essere autenticati come impiegato"
                ,exception.getMessage());
    }

    @Test
    void TestPass() {
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }

    @AfterAll
    public static void clearDB(){
        try {
            service.eliminaImpiegato(impiegato.getMatricola());
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }
}
