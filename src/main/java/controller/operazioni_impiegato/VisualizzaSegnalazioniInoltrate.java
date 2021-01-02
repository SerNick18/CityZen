package controller.operazioni_impiegato;

import controller.gestioneUtenza.MyServletException;
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
 * Servlet che visualizza le segnalazioni inoltrate dai cittadini
 * per l'impiegato.
 */
@WebServlet("/visualizza-segnalazioni-inoltrate")
public class VisualizzaSegnalazioniInoltrate extends HttpServlet {
    /**
     * Si forza il flusso di esecuzione sul metodo doGet.
     * Anche se si riceve una richiesta di tipo POST, si richiama il
     * metodo che gestisce le richieste di tipo GET.
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la servlet
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
     * Il metodo controlla se l'impiegato è autenticato.
     * In caso affermativo recupera le ultime segnalazioni inoltrate
     * dai cittadini e effettua un indirizzamento alla view per
     * visualizzarle.
     * In caso contrario lancia un eccezione con un messaggio di errore.
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la servlet
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     *      quando la servlet gestisce la richiesta
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        FacadeDAO service = new FacadeDAO();
        if (req.getSession().getAttribute("Impiegato") != null) {
            List<SegnalazioneInterface> inoltrate =
                    service.getSegnalazioniByStato("inoltrata", 0);

            req.setAttribute("inoltrate", inoltrate);

            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("WEB-INF/view/GuiImpiegato"
                            + "/visualizza-inoltrate.jsp");
            dispatcher.forward(req, resp);
        } else {
            throw new MyServletException("Per visualizzare "
                    + "le segnalazioni inoltrate "
                    + "bisogna essere autenticati come impiegato");
        }
    }

}
