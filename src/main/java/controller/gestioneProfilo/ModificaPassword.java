package controller.gestioneProfilo;

import controller.gestioneUtenza.MyServletException;
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
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * Classe per modificare la password del cittadino.
 */
@WebServlet("/modificaPassword")
public class ModificaPassword extends HttpServlet {
    /**
     * Il metodo svolge una duplice funzione.
     * Nel caso in cui nella request sia presente un paramentro
     * "provienienza", in base al contenuto di questo paramentro
     * viene inoltrata la richiesta alla giusta pagina jsp.
     * Nel caso in cui nella request non sia presente un paramentro
     * "provenienza", il metodo si occupa di modificare la password
     * dell'utente registrato. Effettuando controlli sulle password e, in caso
     * di esito positivo, aggiornando la password nel database.
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la servlet
     *  deve ritornare al cliente
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     *  quando la servlet gestisce la richiesta
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        FacadeDAO service = new FacadeDAO();
        String provenienza = req.getParameter("provenienza");
        if (provenienza != null && provenienza.equalsIgnoreCase("profilo")) {
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("WEB-INF/view/GuiCittadino/"
                            + "modifica_password.jsp");
            dispatcher.forward(req, resp);
        } else
            if (provenienza == null
                    || !provenienza.equalsIgnoreCase("profilo")) {
                HttpSession session = req.getSession();
                Cittadino cittadino =
                        (Cittadino) session.getAttribute("Cittadino");
                Impiegato impiegato =
                        (Impiegato) session.getAttribute("Impiegato");
                if (cittadino != null && impiegato == null) {
                    String oldPass = req.getParameter("oldPass");
                    String newPass = req.getParameter("newPass");
                    String newPass2 = req.getParameter("newPass2");
                    String passwordHash;
                    try {
                        MessageDigest digest =
                                MessageDigest.getInstance("SHA-1");
                        digest.reset();
                        digest.update(oldPass.getBytes(StandardCharsets.UTF_8));
                        passwordHash = String.format("%040x",
                                new BigInteger(1, digest.digest()));
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                    String passwordAttuale = cittadino.getPwd();
                    if (!passwordAttuale.equals(passwordHash)) {
                        throw new MyServletException(
                                "La vecchia password non corrisponde "
                                + "con la password attuale");
                    }
                    if (!Pattern.matches("^(?=.*\\d)(?=.*[a-z])"
                                   + "(?=.*[A-Z])"
                            + "(?=.*[a-zA-Z]).{8,}$", newPass)) {
                        throw new MyServletException("Le due password"
                                + " nuove non rispettano il formato ("
                                + "Almeno 8 caratteri, 1 lettera maiuscola,"
                                + " 1 minuscola, 1 numero"
                                + " ed 1 carattere speciale");
                    }
                    if (!newPass.equals(newPass2)) {
                        throw new MyServletException("Le password nuove "
                                + "non corrispondono");
                    }
                    service.doUpdatePasswordByEmail(
                            cittadino.getEmail(), newPass);
                    RequestDispatcher dispatcher =
                            req.getRequestDispatcher("WEB-INF/view/"
                                   + "GuiCittadino/gui-cittadino.jsp");
                    dispatcher.forward(req, resp);
                }
                if (impiegato != null && cittadino == null) {
                    String oldPass = req.getParameter("oldPass");
                    String newPass = req.getParameter("newPass");
                    String newPass2 = req.getParameter("newPass2");
                    String passwordHash;
                    try {
                        MessageDigest digest =
                                MessageDigest.getInstance("SHA-1");
                        digest.reset();
                        digest.update(oldPass.getBytes(StandardCharsets.UTF_8));
                        passwordHash = String.format("%040x",
                                new BigInteger(1, digest.digest()));
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                    String passwordAttuale = impiegato.getPwd();
                    if (!passwordAttuale.equals(passwordHash)) {
                        throw new MyServletException(
                                "La vecchia password non corrisponde "
                                        + "con la password attuale");
                    }
                    if (!Pattern.matches("^(?=.*\\d)(?=.*[a-z])"
                            + "(?=.*[A-Z])"
                            + "(?=.*[a-zA-Z]).{8,}$", newPass)) {
                        throw new MyServletException("Le due password"
                                + " nuove non rispettano il formato ("
                                + "Almeno 8 caratteri, 1 lettera maiuscola,"
                                + " 1 minuscola, 1 numero"
                                + " ed 1 carattere speciale");
                    }
                    if (!newPass.equals(newPass2)) {
                        throw new MyServletException("Le password nuove "
                                + "non corrispondono");
                    }
                    service.doUpdatePasswordByEmailImpiegato(
                            impiegato.getEmail(), newPass);
                    RequestDispatcher dispatcher =
                            req.getRequestDispatcher("WEB-INF/view/"
                                    + "GuiImpiegato/gui-impiegato.jsp");
                    dispatcher.forward(req, resp);
                }
                if ((impiegato == null && cittadino == null)
                        || (impiegato != null && cittadino != null)) {
                    RequestDispatcher dispatcher =
                            req.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(req, resp);
                }

            }
    }

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
}
