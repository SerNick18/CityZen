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

@WebServlet("/register")
public class RegistrazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
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

        /**
         * controlli sui dati di input
         */
        if(req.getSession().getAttribute("Cittadino")==null) {
            //controllo email
            if (email.compareTo("") == 0 || !Pattern.matches("[A-Za-z.]+[0-9]*@[A-Za-z.]+", email) || email.contains("@scafati.it")) {
                throw new MyServletException("Email errata!");
            }
            //controllo cf
            if (cf.compareTo("") == 0 || !Pattern.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$", cf)) {
                throw new MyServletException("Codice fiscale errato!");
            }
            //contollo nome
            if (nome.compareTo("") == 0 || !Pattern.matches("^[A-Za-z]+$", nome)) {
                throw new MyServletException("Il nome inserito non è valido!");
            }
            //controllo cognome
            if (cognome.compareTo("") == 0 || !Pattern.matches("^[A-Za-z]+$", cognome)) {
                throw new MyServletException("Il cognome inserito non è valido");
            }
            /**
             * la password deve contenere almeno 8 caratteri, almeno una lettera maiuscola, almeno una lettera minuscola,
             * almeno un numero ed almeno un carattere speciale.
             */
            if (pwd1.compareTo("") == 0 || !Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", pwd1)) {
                throw new MyServletException("La password deve contenere almeno 8 caratteri, " +
                        "almeno una lettera maiuscola, una lettera minuscola,\n" +
                        "             * un numero ed un carattere speciale.");
            }
            if (!pwd2.equals(pwd1)) {
                throw new MyServletException("Le due password non corrispondono");
            }
            if (via.compareTo("") == 0 || !Pattern.matches("^([A-Za-z]\\s?)*$", via)) {
                throw new MyServletException("Inserire una via valida!");
            }
            if (civico.compareTo("") == 0 || !Pattern.matches("^[0-9]{1,3}$", civico)) {
                throw new MyServletException("Inserire un numero civico valido!");
            }
            if (citta.compareTo("") == 0 || !Pattern.matches("^[A-Za-z]+$", citta)) {
                throw new MyServletException("Inserire il nome di una città valido!");
            }
            FacadeDAO service = new FacadeDAO();
            Cittadino cittadino = new Cittadino(cf, nome, cognome, pwd1, via,
                    Integer.parseInt(civico), citta, email, 0, 0);
            service.registraCittadino(cittadino);
            req.getSession().setAttribute("Cittadino", cittadino);
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
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