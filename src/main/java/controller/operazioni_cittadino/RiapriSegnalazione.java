package controller.operazioni_cittadino;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Date;

@WebServlet("/riapriSegnalazione")
@MultipartConfig
public class RiapriSegnalazione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cittadino cittadino;
        if ((cittadino=(Cittadino)req.getSession().getAttribute("Cittadino"))==null)
            throw new MyServletException("Effettua il login per"
                    +" poter visualizzare questa pagina");

        String idSegnalazioneDaAprire = req.getParameter("idSegnalazioneDaAprire");
        if (idSegnalazioneDaAprire!=null){
            //provengo da dettagli, faccio il redirect verso il form di riapertura
            req.setAttribute("idSegnalazioneDaAprire",idSegnalazioneDaAprire);
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/GuiCittadino/riapri-segnalazione.jsp");
            dispatcher.forward(req,resp);
        }else {
            //id segnalazione da riaprire
            int idSegnalazione = Integer.parseInt(req.getParameter("idSegnalazione"));
            String descrizione = req.getParameter("descrizione");
            //provengo dal form di riapertura, riapro la segnalazione
            FacadeDAO service = new FacadeDAO();
            //recupero la segnalazione chiusa
            Segnalazione segnalazioneChiusa = service.getSegnalazioneById(idSegnalazione);

            //controlli sugli input inviati
            if (descrizione.length()>500 || descrizione.length()<10)
                throw new MyServletException("La descrizione deve essere lunga minimo 10 caratteri e massimo 500");

            //creo una nuova segnalazione con alcuni campi di quella chiusa ereditati
            Segnalazione nuovaSegnalazione = new Segnalazione();
            nuovaSegnalazione.setOggetto(segnalazioneChiusa.getOggetto());
            nuovaSegnalazione.setVia(segnalazioneChiusa.getVia());
            nuovaSegnalazione.setCivico(segnalazioneChiusa.getCivico());
            nuovaSegnalazione.setPriorita(0);
            nuovaSegnalazione.setNumSolleciti(0);
            nuovaSegnalazione.setStato("inoltrata");
            nuovaSegnalazione.setDataSegnalazione(new Date());
            nuovaSegnalazione.setDescrizione(descrizione);
            if ((uploadImage(req).equals("")))
                throw new MyServletException("E' obbligatorio allegare una foto alla segnalazione. " +
                        "I formati accettati sono .jpg, .jpeg, .jpg");
            else
                nuovaSegnalazione.setFoto(uploadImage(req));
            nuovaSegnalazione.setCittadino(cittadino);
            nuovaSegnalazione.setRiaperta(segnalazioneChiusa.getId());

            //inserisco la nuova segnalazione
            service.inserisciSegnalazione(nuovaSegnalazione);
            //setto questa segnalaizone come riaperta

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/GuiCittadino/gui-cittadino.jsp");
            dispatcher.forward(req,resp);
        }
    }
    /**
     * Il metodo riceve una request, da cui estrae il file
     * caricato dal cittadino. Si effettua un controllo sul formato accettato (jpg, png, jpeg),
     * si memorizza il file nel server sotto la cartella /resources/images/ e si
     * restituisce il nome del file.
     * @param request da cui estrarre i file caricati dal cliente
     * @return il nome del file caricato
     * @throws MyServletException per gestire errori relativi al formato del file caricato,
     * e per quanto riguarda la memorizzazione del file (se non viene memorizzato)
     * @throws ServletException per gestire errori relativi al ciclo di vita della servlet
     */
    private String uploadImage(HttpServletRequest request) throws MyServletException, ServletException {
        String fileName="";
        try {
            Part filePart = request.getPart("foto");
            fileName += Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String[] fileNameSplit = fileName.split("\\.");
            if (!fileNameSplit[fileNameSplit.length-1].equals("png") && !fileNameSplit[fileNameSplit.length-1].equals("jpg")
                    && !fileNameSplit[fileNameSplit.length-1].equals("jpeg"))
                throw new MyServletException("Formato della foto non accettato, I formati accettati sono .jpg, .jpeg, .jpg");

            InputStream in = filePart.getInputStream();
            BufferedInputStream buf = new BufferedInputStream(in);
            String parentPath = getServletContext().getRealPath("")+"/resources/images/";
            File file = new File(parentPath);
            filePart.write(parentPath+fileName);

            //controlla se il file esiste
            file = new File(parentPath+fileName);
            if (!file.exists())
                throw new MyServletException("Errore nel caricamento della foto!");
        } catch (IOException e) {
            throw new MyServletException("Errore I/O nel caricamento della foto!");
        }
        return fileName;
    }
}