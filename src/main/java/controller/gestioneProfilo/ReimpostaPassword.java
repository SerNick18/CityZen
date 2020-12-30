package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Servlet per reimpostare la password del cittadino.
 */
@WebServlet("/reimposta-password")
public class ReimpostaPassword extends HttpServlet {
    /**.
     * Si forza il flusso di esecuzione sul metodo doPost:
     * anche se si riceve una richiesta di tipo GET, si richiama il
     * metodo che gestisce le richieste di tipo POST
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la servlet
     * deve ritornare al cliente
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     * quando la servlet gestisce la richiesta
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
    /**.
     * Il metodo svolge una duplice funzione.
     *  Nel caso in cui nella request il parametro "provienienza"
     *  sia impostato ad email, significa che la servlet è stata richiamata
     *  dal link presente nella mail di recupera password
     *  recapitata al cittadino, quindi di conseguenza
     *  si effettuerà un indirizzamento verso la pagina di reimposta-password,
     *  che permetterà al cittadino di scegliere una nuova password.
     *  Altrimenti se il parametro è impostato a reimposta-password, significa
     *  che è stata effettuata la richiesta dalla pagina di reimposta-password,
     *  di conseguenza il metodo provvederà
     *  a cambiare la password precedente con la nuova.
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la servlet
     * deve ritornare al cliente
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     * quando la servlet gestisce la richiesta
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String provenienza = req.getParameter("provenienza");
        if (provenienza.equals("email")) {
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("WEB-INF/view/GuiCittadino/"
                            + "reimposta-password.jsp");
            dispatcher.forward(req, resp);
        } else if (provenienza.equals("reimposta-password")) {
            String email = req.getParameter("email");
            String pwd = req.getParameter("pwd");
            String pwd2 = req.getParameter("pwd2");
            String utente=req.getParameter("utente");
            FacadeDAO service = new FacadeDAO();

            if (pwd.equals(pwd2)
                    && Pattern.matches("^(?=.*\\d)(?=.*[a-z])"
                    + "(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", pwd)) {

                if(utente.equals("cittadino"))
                    service.doUpdatePasswordByEmail(email, pwd);
                else
                    service.doUpdatePasswordByEmailImpiegato(email, pwd);

                RequestDispatcher dispatcher =
                        req.getRequestDispatcher("login.jsp");
                dispatcher.forward(req, resp);
            } else {
                throw new MyServletException("Le password non corrispondono");
            }
        }
    }
}
