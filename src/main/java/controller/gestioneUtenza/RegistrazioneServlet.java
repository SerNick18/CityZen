package controller.gestioneUtenza;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/registrazione")
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
        //indirizzo è la via
        String indirizzo = req.getParameter("indirizzo");
        String civico = req.getParameter("civico");
        String citta = req.getParameter("città");
        String email = req.getParameter("email");
        String numSegnalazioni = req.getParameter("numSegnalazioni");
        String numSegnApp = req.getParameter("numSegnApp");

        //controllo iniziale
        if(req.getSession().getAttribute("cittadino")==null) {

            //controllo email
            if (email.compareTo("") == 0 || !Pattern.matches("[A-Za-z.]+[0-9]*@[A-Za-z.]+", email)) {
                //errore
            }
            //controllo cf
            if (cf.compareTo("") == 0 || !Pattern.matches("/^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$/i", cf)) {
                //errore
            }
            //contollo nome
            if (nome.compareTo("") == 0 || !Pattern.matches("/^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$/i", nome)) {
                //errore
            }
            //controllo cognome
            if (cognome.compareTo("") == 0 || !Pattern.matches("/^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$/i", cognome)) {
                //errore
            }
            //controllo pwd1
            if (pwd1.compareTo("") == 0 || !Pattern.matches("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$", pwd1)) {
                //errore
            }

            if (!pwd2.equals(pwd1)) {
                //errore
            }

            if(indirizzo.compareTo("") == 0){
                //errore
            }


        }


    }
}