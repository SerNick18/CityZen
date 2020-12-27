package controller.operazioni_cittadino;

import com.sun.prism.impl.BaseMesh;
import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;
import model.gestioneDati.modelObjects.SegnalazioneInterface;
import org.omg.Messaging.SyncScopeHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/inoltroSol")
public class InoltroSollecito extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cittadino cittadino;
        if ((cittadino=(Cittadino)req.getSession().getAttribute("Cittadino"))==null)
            throw new MyServletException("Effettua il login per"
                    +" poter visualizzare questa pagina");

        String CF = cittadino.getCF();
        if(CF != null)
        {
            FacadeDAO service = new FacadeDAO();
            List<SegnalazioneInterface> lista_approvate = service.getSegnalazioniByStato("approvata",0);
            for(int i = 0; i<lista_approvate.size(); i++) {
                String button = "idSol" + lista_approvate.get(i).getId();
                if (req.getParameter(button) != null) {
                    int id = lista_approvate.get(i).getId();

                    Segnalazione segnalazione = service.getSegnalazioneById(id);
                    int old_sol = segnalazione.getNumSolleciti();
                    segnalazione.setNumSolleciti(old_sol + 1);
                    service.modificaSegnalazione(segnalazione);

                }
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("ListApprovate");
        dispatcher.forward(req,resp);

    }
}
