package controller.gestioneUtenza;

import model.gestioneDati.facadeDataAccess.FacadeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Questa servlet viene chiamata in fase di registrazione
 * tramite una chiamata AJAX che controlla se esiste nel databse
 * un Cittadino con lo stesso codice fiscale.
 *
 */
@WebServlet("/VerificaCodiceFiscale")
public class VerificaCodiceFiscaleServlet extends HttpServlet {
    /**
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    /**
     * In questo metodo avviene il controllo nel database.
     *
     * @param req request
     * @param resp response
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FacadeDAO service = new FacadeDAO();
        String cf = req.getParameter("cf");
        resp.setContentType("text/xml");
        if (cf != null && cf.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$")
                && service.verificaCodiceFiscale(cf) == null) {
            resp.getWriter().append("<ok/>");
        } else {
            resp.getWriter().append("<no/>");
        }
    }
}
