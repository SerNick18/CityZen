package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.modelObjects.Cittadino;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/elimina-profilo")
public class VisualizzaEliminaProfilo extends HttpServlet {
    /**
     * Il metodo riceve le richieste dei cittadini che
     * vogliono eliminare il proprio profilo. Si recupera
     * il cittadino loggato dalla sessione e lo si
     * ridireziona verso la giusta pagina. Se non
     * è presente alcun cittadino nella sessione allora
     * l'utente che ha richiesto la funzione non è autorizzato ad
     * usufruire di questo servizio.
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la
     * servlet deve ritornare al cliente
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     * quando la servlet gestisce la richiesta
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Cittadino cittadino = (Cittadino)
                req.getSession().getAttribute("Cittadino");
        if (cittadino != null) {
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("WEB-INF/view"
                            + "/GuiCittadino/elimina-profilo.jsp");
            dispatcher.forward(req, resp);
        } else {
            throw new MyServletException("Effettua il "
                    + "Login per visualizzare questa pagina!");
        }
    }
    /**
     * Si forza il flusso di esecuzione sul metodo doGet.
     * Anche se si riceve una richiesta di tipo POST, si richiama il
     * metodo che gestisce le richieste di tipo GET.
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
