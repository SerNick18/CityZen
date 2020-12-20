package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/elimina-profilo-servlet")
public class EliminaProfilo extends HttpServlet {
    /**
     * Si forza il flusso di esecuzione sul metodo doPost.
     * Anche se si riceve una richiesta di tipo GET, si richiama il
     * metodo che gestisce le richieste di tipo POST
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la servlet
     * deve ritornare al cliente
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
     * Il metodo richiama la funzione di eliminazione
     * del cittadino attualmente loggato(in sessione). Se non
     * è presente alcun cittadino nella sessione allora
     * l'utente che ha richiesto la funzione non è autorizzato ad
     * usufruire di questo servizio.
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la servlet
     * deve ritornare al cliente
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     * quando la servlet gestisce la richiesta
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Cittadino cittadino =
                (Cittadino) req.getSession().getAttribute("Cittadino");
        if (cittadino != null) {
            FacadeDAO facadeDAO = new FacadeDAO();
            facadeDAO.eliminaCittadino(cittadino.getCF());
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            throw new MyServletException("Effettua il Login per "
                    + "visualizzare questa pagina!");
        }
    }
}
