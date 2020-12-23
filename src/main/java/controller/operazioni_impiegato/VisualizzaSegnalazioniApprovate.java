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

@WebServlet("/visualizza-segnalazioni-approvate")
public class VisualizzaSegnalazioniApprovate extends HttpServlet {
    /**
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        FacadeDAO service=new FacadeDAO();
        List<SegnalazioneInterface> approvate =service.getSegnalazioniByStato("approvata", 0);

        req.setAttribute("approvata", approvate);

        RequestDispatcher dispatcher=req.getRequestDispatcher("WEB-INF/view/GuiImpiegato/visualizza-approvate.jsp");
        dispatcher.forward(req, resp);
    }
}