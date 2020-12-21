package controller.gestioneProfilo;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**.
 * Servlet per mandare una mail
 * in caso l'utente voglia recuperare la sua password.
 */
@WebServlet("/manda-email")
public class MandaEmail extends HttpServlet {
    /**
     * Si forza il flusso di esecuzione sul metodo doGet.
     * Anche se si riceve una richiesta di tipo POST, si richiama il
     * metodo che gestisce le richieste di tipo GET.
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la servlet
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     *      quando la servlet gestisce la richiesta
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
    /**
     * Il metodo recupera dalla richiesta il parametro email,
     * effettua prima un controllo nel database, nel caso in cui
     * il controllo dia esito positivo provvederà ad inviare una mail
     * all'utente con le informazioni da seguire per reimpostare la propria
     * password, mentre nel caso in cui non sia presente nel database notifica
     * alla pagina con cui è stata fatta la richiesta l'errore riscontrato.
     * Inoltre nel caso in cui l'invio della mail non sia andato a buon fine,
     * verrà notificato sempre alla stessa pagina l'errore.
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp oggetto che contiene la risposta che la servlet
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     *      quando la servlet gestisce la richiesta
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final int port = 587;
        String email = req.getParameter("email");
        PrintWriter out = resp.getWriter();
        FacadeDAO service = new FacadeDAO();

        Cittadino c = service.verificaEmail(email);

        if (c != null) {
            //manda email
            String host = "smtp.gmail.com";
            String oggetto = "Reimposta la password";
            String testo = "Gentile Utente, "
                    + "Puoi reimpostare la tua password premendo "
                    + "il pulsante di seguito: \n"
                    + "<form action=\"http://localhost:8080"
                    + "/CityZen_war_exploded/reimposta-password\" "
                    + "method=\"post\">\n"
                    + "    <input type=\"hidden\" name=\"email\""
                    + " value=\"" + email + "\" />\n"
                    + "    <input type=\"hidden\" name=\"provenienza\""
                    + " value=\"email\" />\n"
                    + "    <button>Reimposta Password</button>\n"
                    + "</form>";

            Properties p = new Properties();
            p.put("mail.smtp.auth", "true"); //enable authentication
            p.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            p.put("mail.smtp.host", host);
            p.put("mail.smtp.port", port);

            Session sessione = Session.getDefaultInstance(p,
                    new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("progettoC04@gmail.com",
                            "TestProgetto4");
                }
            });

            MimeMessage mail = new MimeMessage(sessione);

            try {
                mail.setFrom(new InternetAddress("no-reply@scafati.it"));
                mail.addRecipients(Message.RecipientType.TO, c.getEmail());
                mail.setSubject(oggetto);
                mail.setContent(testo, "text/html");

                Transport.send(mail);
                resp.setContentType("text/plain");
                out.println("ok");
            } catch (Exception ex) {
                out.println("errore");
            }
        } else {
            //cittadino non registrato
            resp.setContentType("text/plain");
            out.println("non-registrato");
        }

    }
}
