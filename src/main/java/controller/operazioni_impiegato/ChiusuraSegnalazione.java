package controller.operazioni_impiegato;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
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
import java.util.List;

/**
 * Servlet per chiudere una segnalazione.
 */
@WebServlet("/chiusuraSegnalazione")
public class ChiusuraSegnalazione extends HttpServlet {
    /**
     * Metodo per chiudere una segnalazione
     * Controlla se l'impiegato è loggato se non lo è manda un eccezione.
     * Successivamente controlliamo che la segnalazione
     * è stata selezionata e vediamo se è presente
     * e se il suo stato è su approvata. Se lo stato è
     * approvata possiamo chiuderla altrimenti mandiamo un'eccezione.
     * Lo stato della segnalazione approvata verrà cambiata su
     * chiusa e l'impiegato sarà reindirizzato sulla pagina
     * delle segnalazioni chiuse
     * @param req request
     * @param resp response
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Impiegato impiegato;
        Cittadino cittadino;
        if ((impiegato = (Impiegato)
                session.getAttribute("Impiegato")) == null) {
            throw new MyServletException("Effettua il login "
                    + "per visualizzare la pagina");
        }
        FacadeDAO service = new FacadeDAO();
        if (req.getParameter("id") != null) {
            Segnalazione segnalazione =
                    new FacadeDAO().getSegnalazioneById(
                            Integer.parseInt(req.getParameter("id")));
            if (segnalazione != null) {
                if (segnalazione.getStato().equals("approvata")) {
                    segnalazione.setStato("chiusa");
                    service.modificaSegnalazione(segnalazione);
                    impiegato.setNumSegnalazioniChiuse(
                            impiegato.getNumSegnalazioniChiuse() + 1);
                    service.modificaImpiegato(impiegato);
                    service.inserisciLavorazione(impiegato, segnalazione);
                    List<Impiegato> impiegati =
                            service.getImpiegatiOsservatori(segnalazione
                                    .getId());
                    cittadino = segnalazione.getCittadino();
                    segnalazione.addObserver(cittadino);
                    for (Impiegato i : impiegati) {
                        segnalazione.addObserver(i);
                    }
                    segnalazione.notifyObservers();
                    RequestDispatcher dispatcher =
                            req.getRequestDispatcher("WEB-INF/view/"
                                    + "GuiImpiegato/gui-impiegato.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    throw new MyServletException("Segnalazione non approvata");
                }
            } else {
                throw new MyServletException(
                        "Segnalazione non presente nel database");
            }
        } else {
            throw new MyServletException(("Indicare una segnalazione"));
        }
    }

    /**
     * Metodo doGet che richiama il metodo doPost.
     * @param req request
     * @param resp response
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
}

