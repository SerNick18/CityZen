package controller.gestioneUtenza;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Servlet per il logout
 */
public class LogoutServlet extends HttpServlet {
    /**
     * Metodo per invalidare la sessione
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("Cittadino") != null || req.getSession().getAttribute("Impiegato") != null)
            req.getSession().invalidate();
        else
            throw new MyServletException("Non sei autenticato");
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
    /**
     * Metodo che richiama semplicemente il doGet
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
