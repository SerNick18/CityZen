package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe che reindirizza l'utente cittadino o
 * impiegato verso la sua homepage.
 */
@WebServlet("/guiCittadino")
public class VisualizzaHome extends HttpServlet {
    /**
     * Questo metodo indirizza l'utente verso la propria HomePage,
     * controllando che l'utente loggato sia un cittadino oppure
     * un impiegato.
     * @param req request contenente il Cittadino o Impiegato loggato
     * @param resp oggetto che contiene la risposta che la servlet
     * deve ritornare al cliente
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     * quando la servlet gestisce la richiesta
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Cittadino cittadino =
                (Cittadino) req.getSession().getAttribute("Cittadino");
        Impiegato impiegato =
                (Impiegato) req.getSession().getAttribute("Impiegato");
        if (cittadino != null && impiegato == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(
                    "WEB-INF/view/GuiCittadino/gui-cittadino.jsp");
            dispatcher.forward(req, resp);
        } else if (impiegato != null && cittadino == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(
                    "WEB-INF/view/GuiImpiegato/gui-impiegato.jsp");
            dispatcher.forward(req, resp);
        } else {
            throw new MyServletException(
                    "Non sei autorizzato a visualizzare questa pagina, "
                            + "effettua prima il login.");
        }
    }
    /**
     * Si forza il flusso di esecuzione sul metodo doPost.
     * Anche se si riceve una richiesta di tipo GET, si richiama il
     * metodo che gestisce le richieste di tipo POST
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la servlet
     * deve ritornare al cliente
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     * quando la servlet gestisce la richiesta
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
