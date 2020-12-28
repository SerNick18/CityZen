package controller.operazioni_cittadino;

//import com.sun.prism.impl.BaseMesh;
import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;
import model.gestioneDati.modelObjects.SegnalazioneInterface;
//import org.omg.Messaging.SyncScopeHelper;

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
    /**.
     * Metodo doGet che chiama il metodo doPost
     * @param req request in cui si passano i campi sottomessi dal cittadino
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * Il metodo gestisce l'inoltro di un sollecito da parte del cittadino.
     * Il cittadino clicca il pulsante relativo alla segnalazione di interesse.
     * Il metodo controlla a quale bottone fa riferimento il cittadino e
     * inserisce nel database il valore aggiornato del numero di solleciti
     * @param req request in cui si passano i campi sottomessi dal cittadino
     * @param resp response
     * @throws ServletException per errori di validazione dei campi ed autorizzazioni di sicurezza
     * @throws IOException per errori relativi all'I/O
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cittadino cittadino;
        //controllo se il cittadino è autenticato
        if ((cittadino=(Cittadino)req.getSession().getAttribute("Cittadino"))==null)
            throw new MyServletException("Effettua il login per"
                    +" poter visualizzare questa pagina");

        String CF = cittadino.getCF();
        if(CF != null)
        {

            FacadeDAO service = new FacadeDAO();
            //Vengono salvate in una lista tutte le segnalazioni approvate
            List<SegnalazioneInterface> lista_approvate = service.getSegnalazioniByStato("approvata",0);
            //nel for si scorre la lista per ottenere l'id della segnalazione per cui effettuare il sollecito
            for(int i = 0; i<lista_approvate.size(); i++) {

                String button = "idSol" + lista_approvate.get(i).getId();
                //si controlla che il pulsante sia stato premuto e che si riferisca al pulsante premuto dal cittadino
                if (req.getParameter(button) != null) {
                    int id = lista_approvate.get(i).getId();

                    Segnalazione segnalazione = service.getSegnalazioneById(id);
                    int old_sol = segnalazione.getNumSolleciti();
                    segnalazione.setNumSolleciti(old_sol + 1);
                    //si aggiorna il valore nel database
                    service.modificaSegnalazione(segnalazione);
                }
            }
        }
        //si passa il controllo alla servlet ListApprovate
        //cosi da poter visualizzare il valore correttamente aggiornato
        RequestDispatcher dispatcher = req.getRequestDispatcher("ListApprovate");
        dispatcher.forward(req,resp);

    }
}
