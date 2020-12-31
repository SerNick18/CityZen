package controller.operazioni_cittadino;

import controller.gestioneUtenza.MyServletException;
import controller.operazioni_impiegato.ApprovaSegnalazione;
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

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EliminaSegnalazioneTest extends EliminaSegnalazione {
    EliminaSegnalazione servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    static Segnalazione segnalazione;
    static FacadeDAO service;
    static Cittadino cittadino;
    static Impiegato impiegato;

    @BeforeEach
    void setUpEach () {
        servlet = new EliminaSegnalazione();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        request.getSession().setAttribute("Cittadino", cittadino);
    }

    @BeforeAll
    static void setUpAll() {
        service = new FacadeDAO();
        impiegato = new Impiegato("abc@scafati.it", "MAT365",
                "Cityzen10!", "MPLGEL80A09H387H", "Pippo", "Pippo", "mercato", 1, "Fisciano", 0, 0);
        service.inserisciImpiegato(impiegato);
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma", 3, "Fisciano", "cattaneo@gmail.com", 0, 0);
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
    void testCittadinoNull(){
        request.getSession().setAttribute("Cittadino", null);
        MyServletException exception = assertThrows(MyServletException.class, () -> {servlet.doPost(request, response);});
        assertEquals("Effettua il login per"
                + " visualizzare questa pagina", exception.getMessage());
        request.getSession().setAttribute("Cittadino", cittadino);
    }

    @Test
    void testCittadinoPass(){
        request.setParameter("id", segnalazione.getId()+"");
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
        service.inserisciSegnalazione(segnalazione);
    }

    @Test
    void TestSegnalazioneNonPresente() {
        request.setParameter("id", 65535+"");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La segnalazione non è"
                        + "presente nel database"
                ,exception.getMessage());
    }

    @Test
    void TestSegnalazioneNonInoltrata(){
        segnalazione.setStato("approvata");
        service.modificaSegnalazione(segnalazione);
        request.setParameter("id", String.valueOf(segnalazione.getId()));
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("La segnalazione non è"
                        + " nello stato inoltrata"
                ,exception.getMessage());
    }

    @Test
    void TestSegnalazioneGiaApprovata(){
        request.setParameter("approva", "approva");
        request.setParameter("id", String.valueOf(segnalazione.getId()));
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doGet(request,response);});
        assertEquals("La segnalazione è"
                        + " già stata approvata"
                ,exception.getMessage());
    }

    @AfterAll
    public static void clearDB(){
        try {
            service.eliminaImpiegato(impiegato.getMatricola());
            service.eliminaCittadino(cittadino.getCF());
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }
}