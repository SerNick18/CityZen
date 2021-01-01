package controller.gestioneSegnalazioni;

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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class VisualizzaSegnalazioniChiuseTest extends VisualizzaSegnalazioniChiuse{
    VisualizzaSegnalazioniChiuse servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    static Segnalazione segnalazione;
    static FacadeDAO service;
    static Cittadino cittadino;

    @BeforeEach
    void setUpEach () {
        servlet = new VisualizzaSegnalazioniChiuse();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
    }

    @BeforeAll
    static void setUpAll() {
        service = new FacadeDAO();
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        service.registraCittadino(cittadino);
        segnalazione = new Segnalazione();
        segnalazione.setVia("roma");
        segnalazione.setCivico(3);
        segnalazione.setPriorita(0);
        segnalazione.setNumSolleciti(0);
        segnalazione.setStato("inoltrata");
        segnalazione.setDataSegnalazione(new Date());
        segnalazione.setDescrizione("grossa fuoriuscita d'acqua");
        segnalazione.setOggetto("Perdita d'acqua");
        segnalazione.setFoto("immagine.png");
        segnalazione.setRiaperta(0);
        segnalazione.setCittadino(cittadino);
        service.inserisciSegnalazione(segnalazione);
    }

    @Test
    void testCittadinoImpiegatoNull() {
        request.setParameter("id", segnalazione.getId()+"");
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }

    @Test
    void testCittadinoPass() {
        request.getSession().setAttribute("Cittadino", cittadino);
        request.setParameter("id", segnalazione.getId()+"");
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }

    @Test
    void testImpiegatoPass() {
        request.setParameter("id", segnalazione.getId()+"");
        request.getSession().removeAttribute("Cittadino");
        request.getSession().setAttribute("Impiegato", new Impiegato());
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }

    @AfterAll
    public static void clearDB(){
        try {
            service.eliminaCittadino(cittadino.getCF());
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }

}
