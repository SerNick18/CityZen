package controller.operazioni_cittadino;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/eliminaSegnalazione")
public class EliminaSegnalazione extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cittadino cittadino;
        if((cittadino = (Cittadino) req.getSession().getAttribute("Cittadino"))==null){
            throw new MyServletException("Effettua il login per visualizzare questa pagina");
        }
        FacadeDAO service = new FacadeDAO();
        int id = Integer.parseInt(req.getParameter("id"));
        Segnalazione segnalazione = service.getSegnalazioneById(id);
        if(segnalazione.getStato().equals("inoltrata")){
            if(req.getParameter("approva")==null){
                service.eliminaSegnalazione(segnalazione.getId());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/GuiCittadino/gui-cittadino.jsp");
                dispatcher.forward(req, resp);
            }else {
                throw new MyServletException("La segnalazione è già stata approvata");
            }
        }else {
            throw new MyServletException("La segnalazione non è nello stato inoltrata");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
