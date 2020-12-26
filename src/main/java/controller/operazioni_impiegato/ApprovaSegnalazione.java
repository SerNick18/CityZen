package controller.operazioni_impiegato;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
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
    /**.
     * Metodo per approvare una segnalazione
     * @param req request
     * @param resp response
     * @throws ServletException Eccezione
     * @throws IOException Eccezione
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
                service.inserisciLavorazione(impiegato, segnalazione);
                req.getRequestDispatcher(
                        "/WEB-INF/view/GuiImpiegato/gui-impiegato.jsp")
                        .forward(req, resp);
            } else {
                throw new MyServletException(
                        "Indicare una segnalazione correttamente");
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
