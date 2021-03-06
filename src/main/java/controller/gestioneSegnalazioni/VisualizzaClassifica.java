package controller.gestioneSegnalazioni;

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
 * Servlet che visualizza la classifica.
 */
@WebServlet("/visualizzaClassifica")
public class VisualizzaClassifica extends HttpServlet {
    /**
     * Questo metodo instrada l'utente verso una pagina
     * di visualizzazione della classifica dei
     * cittadini più attivi. Si controlla che
     * l'utente sia un cittadino, impiegato
     * oppure ospite e lo si indirizza verso la
     * giusta pagina.
     * @param req oggetto request che contiene l'utente loggato
     * @param resp oggetto che contiene la risposta che la servlet
     * deve ritornare al client
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     * quando la servlet gestisce la richiesta
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Cittadino cittadino = (Cittadino)
                req.getSession().getAttribute("Cittadino");
        Impiegato impiegato = (Impiegato)
                req.getSession().getAttribute("Impiegato");
        String address = "";
        if (cittadino != null && impiegato == null) {
            address = "/WEB-INF/view/GuiCittadino/visualizza-classifica.jsp";
        } else if (cittadino == null && impiegato != null) {
            address = "/WEB-INF/view/GuiImpiegato/visualizza-classifica.jsp";
        } else {
            address = "./index.jsp";
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(address);
        dispatcher.forward(req, resp);
    }
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
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
