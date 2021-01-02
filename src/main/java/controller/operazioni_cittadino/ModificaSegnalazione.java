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
import java.util.regex.Pattern;

/**
 * Servlet per la modifica di una segnalazione.
 */
@WebServlet("/modificaSegnalazione")
@MultipartConfig
public class ModificaSegnalazione extends HttpServlet {
    /**.
     * Metodo doGet che semplicemente chiama il metodo doPost
     * @param req request in cui si passano i campi sottomessi dal cittadino
     * @param resp response
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
    /**
     * Il metodo gestisce la modifica di una segnalazione inoltrata da parte
     * del cittadino. Il cittadino sottomette un form con i parametri
     * di seguito indicati. Il metodo effettua la validazione dei campi,
     * controlla e memorizza il file immagine caricato dal cittadino,
     * modifica la segnalazione e l'aggiorna nel database.
     * @param req request in cui si passano i campi sottomessi dal cittadino
     * @param resp response
     * @throws ServletException per errori di validazione dei campi
     * ed autorizzazioni di sicurezza
     * @throws IOException per errori relativi all'I/O
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        FacadeDAO service = new FacadeDAO();
        int id = Integer.parseInt(req.getParameter("id"));
        Cittadino cittadino;
        if ((cittadino = (Cittadino) req.getSession()
                .getAttribute("Cittadino")) == null) {
            throw new MyServletException("Effettua il login per"
                    + " poter visualizzare questa pagina");
        }
        if (req.getParameter("approva") != null) {
            //si proviene dalla gui cittadino
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("WEB-INF/view/"
                            + "GuiCittadino/modifica-segnalazione.jsp");
            dispatcher.forward(req, resp);
        } else {
            //recupero campi di input
            String oggetto = req.getParameter("oggetto");
            String descrizione = req.getParameter("descrizione");
            String via = req.getParameter("via");
            String strCivico = req.getParameter("civico");
            int civico;

            //controlli sui campi di input
            if (oggetto == null || descrizione == null || via == null
                    || strCivico == null || oggetto.equals("")
                    || descrizione.equals("") || via.equals("")
                    || strCivico.equals("")) {
                throw new MyServletException(
                        "Compilare tutti i campi richiesti!");
            }
            if (!Pattern.matches("([A-Za-z0-9']\\s*){4,25}", oggetto)) {
                throw new MyServletException(
                        "L'oggetto deve essere lungo minimo 4 e massimo 25 "
                                + "caratteri. Non può contenere "
                                + "caratteri speciali.");
            }
            if (descrizione.length() > 500 || descrizione.length() < 10) {
                throw new MyServletException(
                        "La descrizione deve essere lunga minimo 10 caratteri "
                                + "e massimo 500");
            }
            if (!Pattern.matches("([A-Za-z0-9]\\s*){2,200}", via)) {
                throw new MyServletException(
                        "La via deve essere lunga minimo 2 e massimo 200 "
                                + "caratteri. Non può contenere caratteri "
                                + "speciali.");
            }

            //controllo se il civico è un numero
            try {
                civico = Integer.parseInt(strCivico);
            } catch (NumberFormatException e) {
                throw new MyServletException("Il numero civico non è valido.");
            }
            //controllo se il civico è < 5000
            if (civico > 5000 || civico < 0) {
                throw new MyServletException("Il numero civico non è valido.");
            }

            Segnalazione segnalazione = service.getSegnalazioneById(id);
            if (segnalazione != null) {
                if (segnalazione.getStato().equals("inoltrata")) {
                    segnalazione.setOggetto(oggetto);
                    segnalazione.setVia(via);
                    segnalazione.setCivico(civico);
                    segnalazione.setDescrizione(descrizione);
                    if ((uploadImage(req).equals(""))) {
                        segnalazione.setFoto(segnalazione.getFoto());
                    } else {
                        segnalazione.setFoto(uploadImage(req));
                    }
                } else {
                    throw new MyServletException("La segnalazione non è "
                            + "nello stato inoltrata.");
                }
            } else {
                throw new MyServletException("La segnalazione non è "
                        + "presente nel database.");
            }
            service.modificaSegnalazione(segnalazione);

            RequestDispatcher dispatcher = req.getRequestDispatcher(
                    "WEB-INF/view/GuiCittadino/gui-cittadino.jsp");
            dispatcher.forward(req, resp);
        }
    }

    /**
     * Il metodo riceve una request, da cui estrae il file
     * caricato dal cittadino. Si effettua un controllo sul formato accettato
     * (jpg, png, jpeg), si memorizza il file nel server sotto la cartella
     * /resources/images/ e si restituisce il nome del file.
     * @param request da cui estrarre i file caricati dal cliente -
     * Precondizione request containsKey("foto")
     * @return il nome del file caricato
     * @throws MyServletException per gestire errori relativi al formato del
     * file caricato, e per quanto riguarda la memorizzazione del file
     * (se non viene memorizzato)
     * @throws ServletException per gestire errori relativi al ciclo di vita
     * della servlet
     */
    private String uploadImage(HttpServletRequest request)
            throws MyServletException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("foto");
            fileName += Paths.get(filePart.getSubmittedFileName())
                    .getFileName().toString();
            if (fileName.equals("")) {
                return "";
            }
            String[] fileNameSplit = fileName.split("\\.");
            if (!fileNameSplit[fileNameSplit.length - 1].equals("png")
                    && !fileNameSplit[fileNameSplit.length - 1].equals("jpg")
                    && !fileNameSplit[fileNameSplit.length - 1]
                    .equals("jpeg")) {
                throw new MyServletException(
                        "Formato della foto non accettato, I formati accettati"
                                + " sono .png, .jpeg, .jpg");
            }
            InputStream in = filePart.getInputStream();
            BufferedInputStream buf = new BufferedInputStream(in);
            String parentPath = getServletContext().getRealPath("")
                    + "/resources/images/";
            File file = new File(parentPath);
            filePart.write(parentPath + fileName);

            //controlla se il file esiste
            file = new File(parentPath + fileName);
            if (!file.exists()) {
                throw new MyServletException(
                        "Errore nel caricamento della foto!");
            }
        } catch (IOException e) {
            throw new MyServletException(
                    "Errore I/O nel caricamento della foto!");
        }
        return fileName;
    }
}

