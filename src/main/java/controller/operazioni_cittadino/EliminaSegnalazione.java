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

/**
 * Servlet per eliminare una segnalazione
 */
@WebServlet("/eliminaSegnalazione")
public class EliminaSegnalazione extends HttpServlet {
    /**
     * Metodo che permette di eliminare una segnalazione. Controlla se il cittadino è loggato, se non lo è lancia
     * un'eccezione. Successivamente controlla se la segnalazione è nello stato inoltrata e che non è ancora
     * stata approvata, in questo caso elimina la segnalazione e aggiorna il numero di segnalazioni inoltrate
     * del cittadino, in caso contrario lancia un'eccezione
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
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
                cittadino.setNumSegnalazioni(cittadino.getNumSegnalazioni()-1);
                service.modificaCittadino(cittadino);
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/GuiCittadino/gui-cittadino.jsp");
                dispatcher.forward(req, resp);
            }else {
                throw new MyServletException("La segnalazione è già stata approvata");
            }
        }else {
            throw new MyServletException("La segnalazione non è nello stato inoltrata");
        }
    }
    /**
     * Metodo doGet che richiama il metodo doPost
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
