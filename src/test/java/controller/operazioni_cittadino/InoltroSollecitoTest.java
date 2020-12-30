package controller.operazioni_cittadino;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;
import model.gestioneDati.modelObjects.SegnalazioneInterface;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.ServletException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InoltroSollecitoTest extends InoltroSollecito {
    MockServletConfig config;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    InoltroSollecito servlet;
    static FacadeDAO service;
    static Cittadino cittadino;
    static Segnalazione segnalazione;
    static int idSegnalazione;
    static File solleciti;

    @BeforeAll
    static void setUpAll() {

        service = new FacadeDAO();
        cittadino = new Cittadino("SCRGNN80A01I483A", "Giovanni", "Scorti", "fa6bdd241d66911a0f121904b968f19ab3a80dd2",
                "Roma",2,"Scafati","scorti@gmail.com",0,0);
        service.registraCittadino(cittadino);
        segnalazione = new Segnalazione();
        segnalazione.setOggetto("Buca in strada");
        segnalazione.setVia("roma");
        segnalazione.setCivico(1);
        segnalazione.setPriorita(0);
        segnalazione.setNumSolleciti(0);
        segnalazione.setStato("approvata");
        segnalazione.setDataSegnalazione(new Date());
        segnalazione.setFoto("immagineee.jpg");
        segnalazione.setDescrizione("Una buca in via roma");
        segnalazione.setCittadino(cittadino);
        segnalazione.setRiaperta(0);
        service.inserisciSegnalazione(segnalazione);

        List<SegnalazioneInterface> segnalazioni = service.getSegnalazioneByCittadino(cittadino.getCF(),0);
        idSegnalazione = segnalazioni.get(0).getId();
    }

    @BeforeEach
    void setUpEach() throws ServletException, IOException {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new InoltroSollecito();
        config=new MockServletConfig();
        this.servlet.init(this.config);
        Path path = Paths.get(this.config.getServletContext().getRealPath("")+"/resources");

        Files.createDirectories(path);
        solleciti=new File(this.config.getServletContext().getRealPath("")+"/resources/solleciti.txt");
        solleciti.createNewFile();
    }

    @Test
    void testCittadinoNonLoggato(){
        request.setParameter("id", String.valueOf(idSegnalazione));
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doGet(request,response);});
        assertEquals("Effettua il login per poter visualizzare questa pagina" ,exception.getMessage());
    }

    @Test
    void testIdSegnalazioneNullo(){
        request.getSession().setAttribute("Cittadino", cittadino);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doGet(request,response);});
        assertEquals("Id della segnalazione non definito", exception.getMessage());
    }

    @Test
    void testSegnalazioneNonSollecitata() throws IOException {
        request.setParameter("id", String.valueOf(idSegnalazione));
        FileWriter f=new FileWriter(solleciti);
        f.write("SCRGNN80A01I443A,"+idSegnalazione);
        f.flush();
        f.close();
        request.getSession().setAttribute("Cittadino", cittadino);
        assertDoesNotThrow(()->servlet.doGet(request, response));
    }

    @Test //test con cittadino che ha effettuato solleciti ad altre segnalazioni (quindi con più occorenze nel file)
    void testCittadinoConPiùSolleciti() throws IOException {
        request.setParameter("id", String.valueOf(idSegnalazione));
        FileWriter f=new FileWriter(solleciti);
        f.write("SCRGNN80A01I483A,"+111000+"\n");
        f.flush();
        f.close();
        request.getSession().setAttribute("Cittadino", cittadino);
        assertDoesNotThrow(()->servlet.doGet(request, response));
    }

    @Test
    void testSeganalazioneGiaSollecitata() throws IOException {
        FileWriter f=new FileWriter(solleciti);
        f.write("SCRGNN80A01I483A,"+idSegnalazione+"\n");
        f.flush();
        f.close();
        request.setParameter("id", String.valueOf(idSegnalazione));
        request.getSession().setAttribute("Cittadino", cittadino);
        MyServletException exception =
                assertThrows(MyServletException.class, () -> {servlet.doGet(request,response);});
        assertEquals("hai già effettuato un sollecito a questa segnalazione" ,exception.getMessage());

    }

    @AfterAll
    public static void clearDB(){
        try {
            service.eliminaCittadino(cittadino.getCF());
            //eliminando il cittadino si cancella anche la segnalazione
        } catch (MyServletException myServletException) {
            myServletException.printStackTrace();
        }
    }

    @AfterAll
    public static void deleteSollecitiFile(){
        solleciti.delete();
    }

}