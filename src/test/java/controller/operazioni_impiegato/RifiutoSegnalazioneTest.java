package controller.operazioni_impiegato;


import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;
import model.gestioneDati.modelObjects.Segnalazione;
import model.gestioneDati.modelObjects.SegnalazioneInterface;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RifiutoSegnalazioneTest extends RifiutoSegnalazione {
    RifiutoSegnalazione servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    static FacadeDAO service;
    static Impiegato impiegato;
    static Cittadino cittadino;
    static Segnalazione segnalazione;
    static Segnalazione segnalazione2;
    static int idSegnalazione;
    static int idSegnalazione2;

    @BeforeAll //NOTA BENE: bisogna inserire questo impiegato nel database MANUALMENTE!
    static void setUp() {
        service = new FacadeDAO();
        cittadino = new Cittadino("SCRGNN80A01I483A", "Giovanni", "Scorti", "fa6bdd241d66911a0f121904b968f19ab3a80dd2",
                "Roma",2,"Scafati","scorti@gmail.com",0,0);
        service.registraCittadino(cittadino);

        impiegato = new Impiegato("Massimiliano@scafati.it","mat120","fa6bdd241d66911a0f121904b968f19ab3a80dd2","SCRMSS80A01I483A","Massimiliano","Fresco","Roma",8,"Scafati",0,0);
        service.inserisciImpiegato(impiegato);
        segnalazione = new Segnalazione();
        segnalazione.setOggetto("Buca in strada");
        segnalazione.setVia("roma");
        segnalazione.setCivico(1);
        segnalazione.setPriorita(0);
        segnalazione.setNumSolleciti(0);
        segnalazione.setStato("inoltrata");
        segnalazione.setDataSegnalazione(new Date());
        segnalazione.setFoto("immagineee.jpg");
        segnalazione.setDescrizione("Una buca in via roma");
        segnalazione.setCittadino(cittadino);
        segnalazione.setRiaperta(0);

        segnalazione2 = new Segnalazione();
        segnalazione2.setOggetto("perdita d'acqua");
        segnalazione2.setVia("roma");
        segnalazione2.setCivico(1);
        segnalazione2.setPriorita(0);
        segnalazione2.setNumSolleciti(0);
        segnalazione2.setStato("approvata");
        segnalazione2.setDataSegnalazione(new Date());
        segnalazione2.setFoto("immagineee.jpg");
        segnalazione2.setDescrizione("Perdita in via roma");
        segnalazione2.setCittadino(cittadino);
        segnalazione2.setRiaperta(0);
        service.inserisciSegnalazione(segnalazione);
        service.inserisciSegnalazione(segnalazione2);

        List<SegnalazioneInterface> segnalazioni = service.getSegnalazioneByCittadino(cittadino.getCF(),0);
        idSegnalazione = segnalazioni.get(1).getId();
        idSegnalazione2 = segnalazioni.get(0).getId();
    }

    @BeforeEach
    void setUp_v2(){
        servlet = new RifiutoSegnalazione();
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
        request.getSession().setAttribute("Impiegato",impiegato);
    }
    @Test //test impiegato non loggato
    public void impiegatoNonLoggato(){
        impiegato=null;
        request.getSession().setAttribute("Impiegato",impiegato);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doGet(request,response);});
        assertEquals("Effettua il login per poter visualizzare questa pagina"
                ,exception.getMessage());
    }
   @Test
    public void impiegatoLoggato(){
        request.addParameter("id", String.valueOf(idSegnalazione));
        assertDoesNotThrow(() -> {servlet.doGet(request, response);});
    }
    @Test
    public void segnalazioneNonPresente(){
        request.addParameter("id", "10000");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doGet(request,response);});
        assertEquals("Non esiste nessuna segnalazione con questo id"
                ,exception.getMessage());
    }
    @Test
    public void segnalazionePresenteStatoNonInoltrata(){
        request.addParameter("id", String.valueOf(idSegnalazione2));
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doGet(request,response);});
        assertEquals("La segnalazione non può essere rifiutata perchè il suo stato è: approvata"
                ,exception.getMessage());
    }
    @Test
    public void idSegnalazioneNonValido(){
        request.addParameter("id", "4f");
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doGet(request,response);});
        assertEquals("id della segnalazione non corretto"
                ,exception.getMessage());
    }
    @AfterAll
    public static void clearDB(){
        try {
            service.eliminaCittadino(cittadino.getCF());
            service.eliminaImpiegato("mat120");
            //eliminando il cittadino si cancella anche la segnalazione
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }
}
