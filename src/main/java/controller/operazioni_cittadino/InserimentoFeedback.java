package controller.operazioni_cittadino;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Feedback;
import model.gestioneDati.modelObjects.Segnalazione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/inserimentoFeedback")
public class InserimentoFeedback extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cittadino cittadino = (Cittadino) session.getAttribute("Cittadino");
        if(cittadino == null){
            throw new MyServletException("Effettua il login per"
                    + " poter inserire un feedback");
        }
        String provenienza = req.getParameter("provenienza");
        if (provenienza != null && provenienza.equalsIgnoreCase("listaChiuse")) {
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("WEB-INF/view/GuiCittadino/"
                            + "inserimento-feedback.jsp");
            dispatcher.forward(req, resp);
        } else {
            FacadeDAO service = new FacadeDAO();
            Segnalazione segnalazione;
            int valutazione;
            int idSegnalazione;
            try {
                String id = req.getParameter("id");
                idSegnalazione = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                throw new MyServletException("id della segnalazione"
                        + " non valido");
            }
            segnalazione = service.getSegnalazioneById(idSegnalazione);
            if (segnalazione == null) {
                throw new MyServletException("La segnalazione cercata non esiste");
            }
            String descrizione = req.getParameter("descrizione");
            if (descrizione == null
                    || descrizione.equals("")) {
                throw new MyServletException("La descrizione non può essere vuota");
            }
            if (descrizione.length()>500 || descrizione.length()<10){
                throw new MyServletException("La descrizione deve essere lunga minimo 10 caratteri e massimo 500");
            }

            try {
                valutazione =
                        Integer.parseInt(req.getParameter("valutazione"));
                if(valutazione<1 || valutazione>5){
                    throw new MyServletException("La valutazione non può" +
                            "essere minore di 1 o maggiore di 5");
                }
            } catch (NumberFormatException e) {
                throw new MyServletException("Valutazione non valida");
            }

            if (service.isCittadinoFeedbackSegnalazione(cittadino.getCF(),idSegnalazione)) {

                req.setAttribute("notificaErrore","Hai già inserito un feedback" +
                        " per questa segnalazione!");
                RequestDispatcher dispatcher =
                        req.getRequestDispatcher("WEB-INF/view/GuiCittadino/"
                                + "gui-cittadino.jsp");
                dispatcher.forward(req, resp);
            } else {
                req.setAttribute("notificaSuccesso","Feedback inserito correttamente");
                Feedback feedback = new Feedback(cittadino,segnalazione,descrizione,valutazione, new Date());
                service.doInsertFeedback(feedback);
                RequestDispatcher dispatcher =
                        req.getRequestDispatcher("WEB-INF/view/GuiCittadino/"
                                + "gui-cittadino.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
