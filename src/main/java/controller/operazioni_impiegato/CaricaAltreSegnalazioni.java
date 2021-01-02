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

@WebServlet("/carica-altre-segnalazioni")
public class CaricaAltreSegnalazioni extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String stato=req.getParameter("stato");
        int offset=Integer.parseInt(req.getParameter("offset"));
        FacadeDAO service=new FacadeDAO();
        Gson gson=new Gson();

        List<SegnalazioneInterface> inoltrate=service.getSegnalazioniByStato(stato, offset);

        for (SegnalazioneInterface s : inoltrate) {
            s.getRiaperta();
        }

        String result=gson.toJson(inoltrate);
        resp.setContentType("application/json");
        PrintWriter out= resp.getWriter();
        out.println(result);
    }
}
