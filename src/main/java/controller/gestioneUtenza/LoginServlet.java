package controller.gestioneUtenza;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
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
import java.util.regex.Pattern;

/**
 * Servlet per l'autenticazione.
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
    /**.
     * Metodo usato per effettuare il login
     * Controlla se l'utente ha già effettuato il login e se è loggato lancia
     * un'eccezione. Riceve in input una email e una password ed effettua
     * controlli sulla correttezza dei campi. L'email può contenere lettere e
     * numeri e una @ seguita dal dominio, la password deve contenere almeno
     * 8 caratteri, una lettera maiuscola, una lettera minuscola e un numero.
     * Inoltre se la mail contiene come dominio @scafati.it vuol dire che a
     * loggarsi è stato un Impiegato che viene reindirizzato alla sua pagina
     * principale, altrimenti è un Cittadino che verrà reindirizzato alla
     * propria home page.
     * @param req request
     * @param resp response
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        if (email == null || pwd == null) {
            throw new MyServletException("Si è verificato un errore");
        }
        FacadeDAO service = new FacadeDAO();
        HttpSession sn = req.getSession();
        if (req.getSession().getAttribute("Cittadino") == null) {
            if (email.compareTo("") == 0
                    || !Pattern.matches("[A-Za-z.]+[0-9]*@[A-Za-z.]+", email)
                    || pwd.compareTo("") == 0
                    || !Pattern.matches("^(?=.*\\d)(?=.*[a-z])"
                   + "(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", pwd)) {
                throw new MyServletException("Controlla correttezza campi");
            } else if (email.contains("@scafati.it")) { //login impiegato
                Impiegato impiegato = service.loginImpiegato(email, pwd);
                if (impiegato != null) {
                    sn.setAttribute("Impiegato", impiegato);
                    RequestDispatcher dispatcher =
                            req.getRequestDispatcher("WEB-INF/view/"
                                    + "GuiImpiegato/gui-impiegato.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    throw new MyServletException("Email o password errati");
                }
            } else { //login cittadino
                Cittadino cittadino = service.loginCittadino(email, pwd);
                if (cittadino != null) {
                    sn.setAttribute("Cittadino", cittadino);
                    RequestDispatcher dispatcher =
                            req.getRequestDispatcher(
                                    "/WEB-INF/view/GuiCittadino/gui-cittadino.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    throw new MyServletException("Email o password errati");
                }
            }
        }
        else {
            throw new MyServletException("Utente già loggato");
        }
    }
}
