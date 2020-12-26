package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Segnalazione;
import model.gestioneDati.modelObjects.SegnalazioneInterface;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/visualizza-segnalazioni")
public class VisualizzaProprieSegnalazioni extends HttpServlet {
    /**
     * Si forza il flusso di esecuzione sul metodo doGet.
     * Anche se si riceve una richiesta di tipo POST, si richiama il
     * metodo che gestisce le richieste di tipo GET.
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la servlet
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     *      quando la servlet gestisce la richiesta
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        if (req.getSession().getAttribute("Cittadino") != null) {
            String cf = req.getParameter("cf");
            FacadeDAO service = new FacadeDAO();
            List<SegnalazioneInterface> lista = service.getSegnalazioneByCittadino(cf, 0);

            req.setAttribute("proprie", lista);
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("WEB-INF/view/GuiCittadino"
                            + "/visualizza-proprie.jsp");
            dispatcher.forward(req, resp);
        } else {
            throw new MyServletException("non sei loggato");
        }
    }
}
