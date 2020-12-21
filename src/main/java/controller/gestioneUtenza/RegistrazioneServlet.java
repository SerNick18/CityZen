package controller.gestioneUtenza;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;
/**
 *In questa servlet viene gestità la registrazione.
 **/
@WebServlet("/register")
public class RegistrazioneServlet extends HttpServlet {
    /**
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    /**
     * Metodo che viene chiamata quando l'utente invia una richiesta di registrazione.
     * Controlla se l'utente ha già effettuato il login e se è loggato lancia un'eccezione.
     * Riceve dalla request tutti i parametri con il quale l'utente può registrarsi.
     * Effettua controlli sugli appositi parametri e memorizza il Cittadino nel database.
     *
     * @param req request
     * @param resp response
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cf = req.getParameter("cf");
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String pwd1= req.getParameter("pwd1");
        String pwd2 = req.getParameter("pwd2");
        String via = req.getParameter("via");
        String civico = req.getParameter("civico");
        String citta = req.getParameter("citta");
        String email = req.getParameter("email");
        FacadeDAO service = new FacadeDAO();
        /**
         * controlli sui dati di input
         */
        if(req.getSession().getAttribute("Cittadino")==null) {
            Cittadino cittadino;
            /**
             *
             * controllo sul valore del nome
             *
             * */
            if (nome.compareTo("") == 0 || !Pattern.matches("^[A-Za-z]+$", nome)) {
                throw new MyServletException("Il nome inserito non è valido!");
            }
            /**
             *
             * controllo sul valore del cognome
             *
             * */
            if (cognome.compareTo("") == 0 || !Pattern.matches("^[A-Za-z]+$", cognome)) {
                throw new MyServletException("Il cognome inserito non è valido");
            }
            /**
             *
             *controllo sul valore dell'email
             *
             **/
            cittadino=service.verificaEmail(email);
            if (email.compareTo("") == 0 ||
                    !Pattern.matches("[A-Za-z.]+[0-9]*@[A-Za-z.]+", email)
                    || email.contains("@scafati.it") || cittadino!=null) {
                throw new MyServletException("Email errata!");
            }
            /**
             *
             * controllo sul valore del cf
             *
             * */
            cittadino=service.verificaCodiceFiscale(cf);
            if (cf.compareTo("") == 0 ||
                    !Pattern.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$", cf)
                    || cittadino!=null) {
                throw new MyServletException("Codice fiscale errato!");
            }
            /**
             *
             * controllo sul valore del civico
             *
             * */
            if (civico.compareTo("") == 0
                    || !Pattern.matches("^[0-9]{1,3}$", civico)) {
                throw new MyServletException("Inserire un numero civico valido!");
            }
            /**
             *
             * controllo sul valore della via
             *
             * */
            if (via.compareTo("") == 0
                    || !Pattern.matches("^([A-Za-z]\\s?)*$", via)) {
                throw new MyServletException("Inserire una via valida!");
            }
            /**
             *
             * controllo sul valore della citta
             *
             * */
            if (citta.compareTo("") == 0
                    || !Pattern.matches("^[A-Za-z]+$", citta)) {
                throw new MyServletException("Inserire il nome di una città valido!");
            }
            /**
             * controllo password
             * la password deve contenere almeno 8 caratteri, almeno una lettera maiuscola, almeno una lettera minuscola,
             * almeno un numero ed almeno un carattere speciale.
             */
            if (pwd1.compareTo("") == 0
                    || !Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", pwd1)) {
                throw new MyServletException("La password deve contenere almeno 8 caratteri, " +
                        "almeno una lettera maiuscola, una lettera minuscola,\n" +
                        "             * un numero ed un carattere speciale.");
            }
            /**
             *
             * controllo confronto password
             *
             * */
            if (!pwd2.equals(pwd1)) {
                throw new MyServletException("Le due password non corrispondono");
            }
            /**
             *
             * registrazione nel database del cittadino
             *
             * */
            cittadino = new Cittadino(cf, nome, cognome, pwd1, via,
                    Integer.parseInt(civico), citta, email, 0, 0);
            service.registraCittadino(cittadino);
            req.getSession().setAttribute("Cittadino", cittadino);
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }else{
            /**
             * redirigere il cittadino al profilo ed indicargli che deve fare logout per potersi
             * registrare con un altro account
             */
            Cittadino cittadino = (Cittadino) req.getSession().getAttribute("Cittadino");
            //throw new MyServletException("Sei già loggato come "+cittadino.getEmail());
            resp.sendRedirect(req.getContextPath()+"/WEB-INF/pagina_profilo.jsp");
        }

    }
}