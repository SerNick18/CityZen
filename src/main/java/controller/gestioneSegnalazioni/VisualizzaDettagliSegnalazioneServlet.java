package controller.gestioneSegnalazioni;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Feedback;
import model.gestioneDati.modelObjects.Segnalazione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Questa servlet prende una segnalazione e mostra i dettagli,
 * sia al cittadino sia all'impiegato.
 * */
@WebServlet("/dettagli")
public class VisualizzaDettagliSegnalazioneServlet extends HttpServlet {
    /**
     * Si forza il flusso di esecuzione sul metodo doPost.
     * Anche se si riceve una richiesta di tipo GET, si richiama il
     * metodo che gestisce le richieste di tipo POST
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la servlet
     * deve ritornare al client
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     * quando la servlet gestisce la richiesta
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
    /**
     * Metodo per poter visualizzare i dettagli della segnalazione.
     * Riceve l'id della segnalazione.
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la servlet
     * deve ritornare al client
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     * quando la servlet gestisce la richiesta
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        FacadeDAO service = new FacadeDAO();
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new MyServletException("segnalazione non valida");
        }
        Segnalazione segnalazione = service.getSegnalazioneById(id);
        if (segnalazione == null) {
            throw new MyServletException("Nessuna segnalazione trovata");
        }
        req.getSession().setAttribute("Segnalazione", segnalazione);
        if (req.getSession().getAttribute("Cittadino") != null) {
            ArrayList<Feedback> feedbacks = (ArrayList<Feedback>)
                    service.getFeedbacksBySegnalazione(id);
            req.setAttribute("feedbacks", feedbacks);
            RequestDispatcher dispatcher =
                req.getRequestDispatcher(
                        "/WEB-INF/view/GuiCittadino/dettagli-segnalazione.jsp");
            dispatcher.forward(req, resp);
        } else if (req.getSession().getAttribute("Impiegato") != null) {
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("/WEB-INF/view/"
                            + "GuiImpiegato/dettagli-segnalazione.jsp");
            dispatcher.forward(req, resp);
        } else {
            throw new MyServletException("Impossibile "
                   + "vedere i dettagli se non si è autenticato");
        }
    }
}
