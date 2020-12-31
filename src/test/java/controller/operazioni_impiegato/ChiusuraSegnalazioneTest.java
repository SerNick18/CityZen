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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ChiusuraSegnalazioneTest extends ChiusuraSegnalazione{
    ChiusuraSegnalazione servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    static Segnalazione segnalazione;
    static FacadeDAO service;
    static Cittadino cittadino;
    static Impiegato impiegato;

    @BeforeEach
    void setUpEach () {
        servlet = new ChiusuraSegnalazione();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
        impiegato = new Impiegato("abc@scafati.it","MAT365",
                "Cityzen10!","MPLGEL80A09H387H","Pippo","Pippo","mercato",1,"Fisciano",0,0);
        request.getSession().setAttribute("Impiegato", impiegato);
    }

    @BeforeAll
    static void setUpAll() {
        service = new FacadeDAO();
        impiegato = new Impiegato("abc@scafati.it","MAT365",
                "Cityzen10!","MPLGEL80A09H387H","Pippo","Pippo","mercato",1,"Fisciano",0,0);
        service.inserisciImpiegato(impiegato);
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        service.registraCittadino(cittadino);
        segnalazione = new Segnalazione();
        segnalazione.setVia("roma");
        segnalazione.setCivico(3);
        segnalazione.setPriorita(0);
        segnalazione.setNumSolleciti(0);
        segnalazione.setStato("approvata");
        segnalazione.setDataSegnalazione(new Date());
        segnalazione.setDescrizione("grossa fuoriuscita d'acqua");
        segnalazione.setOggetto("Perdita d'acqua");
        segnalazione.setFoto("immagine.png");
        segnalazione.setRiaperta(0);
        segnalazione.setCittadino(cittadino);
        service.inserisciSegnalazione(segnalazione);
    }

    @Test
    void TestImpiegatoNull() {
        impiegato = null;
        request.getSession().setAttribute("Impiegato", impiegato);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Effettua il login per visualizzare la pagina"
                ,exception.getMessage());
    }

    @Test
    public void TestImpiegatoPass(){
        request.addParameter("id", String.valueOf(segnalazione.getId()));
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }

    @Test
    void TestSegnalazioneNonPresente() {
        request.setParameter("id", 65535+"");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Segnalazione non presente nel database"
                ,exception.getMessage());
    }

    @Test
    void TestSegnalazioneNonApprovata(){
        segnalazione.setStato("inoltrata");
        service.modificaSegnalazione(segnalazione);
        request.setParameter("id", String.valueOf(segnalazione.getId()));
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Segnalazione non approvata"
                ,exception.getMessage());
        segnalazione.setStato("approvata");
        service.modificaSegnalazione(segnalazione);
    }

    @Test
    void TestIdParameterNull() {
        request.setParameter("id", (String) null);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doPost(request,response);});
        assertEquals("Indicare una segnalazione"
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
