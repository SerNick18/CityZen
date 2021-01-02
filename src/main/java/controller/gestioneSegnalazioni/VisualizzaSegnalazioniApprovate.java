package controller.gestioneSegnalazioni;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.SegnalazioneInterface;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet che prende le segnalazioni
 * con lo stato approvata e le mostra all'utente.
 */
@WebServlet("/ListApprovate")
public class VisualizzaSegnalazioniApprovate extends HttpServlet {
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
     * Metodo che recupera le segnalazioni
     * nello stato approvata e le mostra all'utente.
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
        List<SegnalazioneInterface> approvate =
                service.getSegnalazioniByStato("approvata", 0);

        req.setAttribute("approvata", approvate);
        if (req.getSession().getAttribute("Impiegato") != null) {
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("WEB-INF/view/GuiImpiegato"
                            + "/visualizza-approvate.jsp");
            dispatcher.forward(req, resp);
        } else if (req.getSession().getAttribute("Cittadino") != null) {
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("WEB-INF/view/GuiCittadino"
                            + "/visualizza-approvate.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("WEB-INF/view/GuiOspite"
                            + "/gui-ospite.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
