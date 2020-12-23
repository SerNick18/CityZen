package controller.operazioni_impiegato;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.SegnalazioneInterface;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/visualizza-segnalazioni-inoltrate")
public class VisualizzaSegnalazioniInoltrate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        FacadeDAO service=new FacadeDAO();
        List<SegnalazioneInterface> inoltrate=service.getSegnalazioniByStato("inoltrata", 0);

        req.setAttribute("inoltrate", inoltrate);

        RequestDispatcher dispatcher=req.getRequestDispatcher("WEB-INF/view/GuiImpiegato/visualizza-inoltrate.jsp");
        dispatcher.forward(req, resp);
    }

}
