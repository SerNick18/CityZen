package controller.operazioni_impiegato;

import com.google.gson.Gson;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.SegnalazioneInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet per caricare altre segnalazioni.
 */
@WebServlet("/carica-altre-segnalazioni")
public class CaricaAltreSegnalazioni extends HttpServlet {

    /**
     * Metodo doPost che richiama semplicemente il doGet.
     * @param req request
     * @param resp response
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * Metodo per caricare altre segnalazioni.
     * Salviamo lo stato delle segnalazioni, interroghiamo il db
     * per mostrare le successive 20 segnalazioni
     * @param req request
     * @param resp response
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore di input o output
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String stato = req.getParameter("stato");
        int offset = Integer.parseInt(req.getParameter("offset"));
        FacadeDAO service = new FacadeDAO();
        Gson gson = new Gson();

        List<SegnalazioneInterface> inoltrate = service
                .getSegnalazioniByStato(stato, offset);

        for (SegnalazioneInterface s : inoltrate) {
            s.getRiaperta();
        }

        String result = gson.toJson(inoltrate);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(result);
    }
}
