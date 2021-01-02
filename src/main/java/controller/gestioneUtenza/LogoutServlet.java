package controller.gestioneUtenza;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    /**.
     * Metodo usato per effettuare il logout
     * Semplicemente questo metodo invalida la sessione
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getSession().getAttribute("Cittadino") != null
                || req.getSession().getAttribute("Impiegato") != null) {
            req.getSession().invalidate();
        } else {
            throw new MyServletException("Non sei autenticato");
        }
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    /**.
     * Metodo doPost che richiama semplicemente il doGet
     * @param req request
     * @param resp response
     * @throws ServletException eccezione
     * @throws IOException ecezione
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
