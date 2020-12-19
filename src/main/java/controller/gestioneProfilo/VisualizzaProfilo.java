package controller.gestioneProfilo;

import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet per la visualizzazione del profilo utente.
 * Controlla se nella sessione è presente un cittadino oppure un impiegato.
 * A seconda dei casi richiama la jsp giusta.
 */
@WebServlet("/profilo")
public class VisualizzaProfilo extends HttpServlet {
    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     *
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String address = "";
        HttpSession session = req.getSession();
        Impiegato impiegato = (Impiegato) session.getAttribute("Impiegato");
        Cittadino cittadino = (Cittadino) session.getAttribute("Cittadino");
        if (cittadino != null) {
            address = "WEB-INF/view/GuiCittadino/profilo.jsp";
        } else
            if (impiegato != null) {
                address = "WEB-INF/view/GuiImpiegato/profilo.jsp";
            }
        RequestDispatcher dispatcher = req.getRequestDispatcher(address);
        dispatcher.forward(req, resp);
    }
}
