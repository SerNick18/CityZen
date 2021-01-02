package controller.operazioni_impiegato;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Impiegato;
import model.gestioneDati.modelObjects.Segnalazione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  Servlet per rifiutare una segnalazione.
 */
@WebServlet("/rifiutoSegnalazione")
public class RifiutoSegnalazione extends HttpServlet {
    /**
     * Il metodo svolge la funzione di chiusura
     * di una segnalazione. La chiusura può essere fatta
     * solo se l'impiegato ha effettuato l'accesso e
     * solo se la segnalazione ha come stato "inoltrata"
     * @param req oggetto che contiene la richiesta da parte di un client
     * @param resp  oggetto che contiene la risposta che la servlet
     * deve ritornare al cliente
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     * quando la servlet gestisce la richiesta
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int idSegnalazione;
        HttpSession session = req.getSession();
        Impiegato impiegato = (Impiegato) session.getAttribute("Impiegato");
        if (impiegato == null) {
            throw new MyServletException("Effettua il login per"
                    + " poter visualizzare questa pagina");
        } else {
            FacadeDAO service = new FacadeDAO();
            try {
                idSegnalazione = Integer.parseInt(req.getParameter("id"));
            } catch (NumberFormatException e) {
                throw new MyServletException("id della segnalazione"
                        + " non corretto");
            }

            Segnalazione segnalazione =
                    service.getSegnalazioneById(idSegnalazione);

            if (segnalazione == null) {
                throw new MyServletException("Non esiste nessuna"
                        + " segnalazione con questo id");
            }

            if (segnalazione.getStato().equals("inoltrata")) {
                segnalazione.setStato("rifiutata");
                service.modificaSegnalazione(segnalazione);
                service.inserisciLavorazione(impiegato, segnalazione);
                RequestDispatcher dispatcher =
                        req.getRequestDispatcher("WEB-INF/view/GuiImpiegato"
                                + "/gui-impiegato.jsp");
                dispatcher.forward(req, resp);
            } else {
                throw new MyServletException("La segnalazione non può essere "
                        + "rifiutata perchè il suo stato è: "
                        + segnalazione.getStato());

            }
        }
    }
    /**
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
}
