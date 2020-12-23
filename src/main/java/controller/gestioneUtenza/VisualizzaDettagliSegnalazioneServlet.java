package controller.gestioneUtenza;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Segnalazione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dettagli")
public class VisualizzaDettagliSegnalazioneServlet extends HttpServlet {
    /**
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
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
        req.getSession().setAttribute("Segnalazione", segnalazione);
        if(req.getSession().getAttribute("Cittadino") != null){
            RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/view/GuiCittadino/dettagli-segnalazione.jsp");
            dispatcher.forward(req, resp);
        }else if(req.getSession().getAttribute("Impiegato") != null) {
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("/WEB-INF/view/GuiImpiegato/dettagli-segnalazione.jsp");
            dispatcher.forward(req, resp);
        }else {
            throw new MyServletException("Impossibile vedere i dettagli se non si è autenticato");
        }
    }
}
