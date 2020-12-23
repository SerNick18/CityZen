package controller.operazioni_impiegato;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Impiegato;
import model.gestioneDati.modelObjects.Segnalazione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet per chiudere una segnalazione
 */
@WebServlet("/chiusuraSegnalazione")
public class ChiusuraSegnalazione extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Impiegato impiegato;
        if((impiegato = (Impiegato) session.getAttribute("Impiegato"))==null){
            throw new MyServletException("Effettua il login per visualizzare la pagina");
        }
        FacadeDAO service = new FacadeDAO();
        if(req.getParameter("id")!=null){
            Segnalazione segnalazione = new FacadeDAO().getSegnalazioneById(Integer.parseInt(req.getParameter("id")));
            if(segnalazione != null && (segnalazione.getStato().equals("approvata"))){
                segnalazione.setStato("chiusa");
                service.modificaSegnalazione(segnalazione);
                segnalazione.notifyObservers();
                service.inserisciLavorazione(impiegato, segnalazione);
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/GuiImpiegato/visualizza-chiuse.jsp");
                dispatcher.forward(req,resp);
            } else {
                throw new MyServletException("Segnalazione non approvata");
            }
        }else{
            throw new MyServletException(("Indicare una segnalazione"));
        }
    }
}
