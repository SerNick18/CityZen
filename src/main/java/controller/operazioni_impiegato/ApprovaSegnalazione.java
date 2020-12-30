package controller.operazioni_impiegato;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;
import model.gestioneDati.modelObjects.Segnalazione;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**.
 * Servlet per approvare una segnalazione
 */
@WebServlet("/approva")
public class ApprovaSegnalazione extends HttpServlet {
    /**
     * Il metodo svolge la funzione di approvazione
     * di una segnalazione. L'approvazione può essere fatta
     * solo se l'impiegato ha effettuato l'accesso e
     * solo se la segnalazione è nello stato "inoltrata"
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp  oggetto che contiene la risposta che la servlet
     * deve ritornare al cliente
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     * quando la servlet gestisce la richiesta
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Impiegato impiegato = (Impiegato) session.getAttribute("Impiegato");
        if (impiegato == null) {
            throw new MyServletException(
                    "Effettua il login per poter effettuare"
                    + " questa operazione");
        }
        FacadeDAO service = new FacadeDAO();
        if (req.getParameter("id") != null) {
            Segnalazione segnalazione =
                    new FacadeDAO().getSegnalazioneById(
                            Integer.parseInt(req.getParameter("id")));
            if (segnalazione != null
                    && segnalazione.getStato().equals("inoltrata")) {
                segnalazione.setStato("approvata");
                service.modificaSegnalazione(segnalazione);
                impiegato.setNumSegnalazioniApp(
                        impiegato.getNumSegnalazioniApp() + 1);
                service.modificaImpiegato(impiegato);
                service.inserisciLavorazione(impiegato, segnalazione);

                //aggiorna numero di segnalazini approvate del cittadino
                Cittadino cittadino = service.getCittadinoByCf(segnalazione.getCittadino().getCF());
                cittadino.setNumSegnApp(cittadino.getNumSegnApp()+1);
                service.modificaCittadino(cittadino);

                req.getRequestDispatcher(
                        "/WEB-INF/view/GuiImpiegato/gui-impiegato.jsp")
                        .forward(req, resp);
            } else {
                throw new MyServletException(
                        "Indicare una segnalazione inoltrata");
            }
        } else {
            throw new MyServletException("Indicare una segnalazione");
        }
    }
    /**.
     * Metodo doPost che richiama semplicemente il doGet
     * @param req request
     * @param resp response
     * @throws ServletException eccezione
     * @throws IOException eccezione
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
