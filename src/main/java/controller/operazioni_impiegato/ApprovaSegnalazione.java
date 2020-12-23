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
import java.sql.SQLException;
@WebServlet("/approva")
public class ApprovaSegnalazione extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Impiegato impiegato = (Impiegato) session.getAttribute("Impiegato");
        if(impiegato == null)
            throw new MyServletException("Effettua il login per poter effettuare" +
                    " questa operazione");
        FacadeDAO service = new FacadeDAO();
        if(req.getParameter("id") != null) {
            Segnalazione segnalazione = new FacadeDAO().getSegnalazioneById(Integer.parseInt(req.getParameter("id")));
            if(segnalazione != null && segnalazione.getStato().equals("inoltrata")) {
                int id = Integer.parseInt(req.getParameter("id"));
                service.setStato("approva", id);
                service.inserisciLavorazione(impiegato, "approvata", id);
                req.getRequestDispatcher("").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Indicare una segnalazione correttamente");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Indicare una segnalazione");
        }
    }
}
