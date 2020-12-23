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
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@WebServlet("/inoltroSegnalazione")
@MultipartConfig
public class InoltroSegnalazione extends HttpServlet {
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

        if(req.getParameter("fromGui")!=null && req.getParameter("fromGui").equals("true")){
            //si proviene dalla gui cittadino
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("WEB-INF/view/GuiCittadino/inoltro-segnalazione.jsp");
            dispatcher.forward(req,resp);
        }else{
            //recupero campi di input
            String oggetto = req.getParameter("oggetto");
            String descrizione = req.getParameter("descrizione");
            String via = req.getParameter("via");
            String strCivico = req.getParameter("civico");

            //controlli sui campi di input
            if(oggetto==null||descrizione==null||via==null||strCivico==null||
            oggetto.equals("")||descrizione.equals("")||via.equals("")||strCivico.equals("")) {
                req.setAttribute("TryAgain","inoltroSegnalazione");
                throw new MyServletException("Compilare tutti i campi richiesti!");
            }

            if (!Pattern.matches("([A-Za-z0-9]\\s*){4,25}",oggetto)) {
                throw new MyServletException("L'oggetto deve essere lungo minimo 4 e massimo 25 caratteri. " +
                        "Non può contenere caratteri speciali.");
            }
            if (descrizione.length()>500 || descrizione.length()<10)
                throw new MyServletException("La descrizione deve essere lunga minimo 10 caratteri e massimo 500");
            if (!Pattern.matches("([A-Za-z0-9]\\s*){2,200}",via)) {
                throw new MyServletException("La via deve essere lunga minimo 2 e massimo 200 caratteri. " +
                        "Non può contenere caratteri speciali.");
            }
            if (!Pattern.matches("[0-9]{1,5}",strCivico)) {
                throw new MyServletException("Il numero civico deve essere un numero di massimo 5 cifre.");
            }

            //creo la segnalazione
            Segnalazione segnalazione = new Segnalazione();
            segnalazione.setOggetto(oggetto);
            segnalazione.setVia(via);
            segnalazione.setCivico(Integer.parseInt(strCivico));
            segnalazione.setPriorita(0);
            segnalazione.setNumSolleciti(0);
            segnalazione.setStato("inoltrata");
            segnalazione.setDataSegnalazione(new Date());
            segnalazione.setDescrizione(descrizione);
            if ((uploadImage(req).equals("")))
                throw new MyServletException("E' obbligatorio allegare una foto alla segnalazione");
            else
                segnalazione.setFoto(uploadImage(req));
            segnalazione.setCittadino(cittadino);
            //segnalazione.setRiaperta(0);

            FacadeDAO service = new FacadeDAO();
            service.inserisciSegnalazione(segnalazione);

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/GuiCittadino/gui-cittadino.jsp");
            dispatcher.forward(req,resp);

        }
    }

    private String uploadImage(HttpServletRequest request) throws MyServletException {
        String fileName="";
        try {
            Part filePart = request.getPart("foto");
            fileName += Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
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
            throw new MyServletException("Errore nel caricamento della foto!");
        } catch (ServletException e) {
            throw new MyServletException("Errore nel caricamento della foto!");
        }
        return fileName;
    }

}
