package controller.operazioni_cittadino;

import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
    /**
     * Il metodo gestisce l'inoltro di un sollecito da parte del cittadino.
     * Il cittadino clicca il pulsante relativo alla segnalazione di interesse.
     * Il metodo controlla a quale bottone fa riferimento il cittadino ,
     * controlla se il cittadino ha già effettuato il sollecito
     * ad una segnalazione e inserisce nel database il valore aggiornato
     * relativo al numero di solleciti
     * @param req request in cui si passano i campi sottomessi dal cittadino
     * @param resp response
     * @throws ServletException per errori di validazione dei campi
     * ed autorizzazioni di sicurezza
     * @throws IOException per errori relativi all'I/O
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Cittadino cittadino;
        //controllo se il cittadino è autenticato
        if ((cittadino = (Cittadino)
                req.getSession().getAttribute("Cittadino")) == null) {
            throw new MyServletException("Effettua il login per"
                    + " poter visualizzare questa pagina");
        }
        String cf = cittadino.getCF();
        if (cf != null) {
            FacadeDAO service = new FacadeDAO();
            int id = Integer.parseInt(req.getParameter("id"));
            Segnalazione segnalazione = service.getSegnalazioneById(id);
            //controllo quale segnalazione il cittadino
            // ha scelto per effettuare il sollecito
            if (req.getParameter("id") != null) {

                //Un file conserverà i dati relativi al sollecito
                String path = getServletContext().getRealPath("")
                        + "/resources/solleciti.txt";
                File fileSollecitiR = new File(path);
                FileReader reader = new FileReader(fileSollecitiR);
                BufferedReader bufferR = new BufferedReader(reader);
                String riga = "";
                //leggo nel file e controllo se il cittadino ha già effettuato
                // un sollecito a questa segnalazione
                while ((riga = bufferR.readLine()) != null) {
                    String[] arrayRiga = riga.split(",");
                    if (arrayRiga[0].equals(cf)
                            && Integer.parseInt(arrayRiga[1])
                            == segnalazione.getId()) {
                        throw new MyServletException(
                                "hai già effettuato un sollecito "
                                        + "a questa segnalazione");
                    }
                }
                bufferR.close();
                reader.close();

                int oldSol = segnalazione.getNumSolleciti();
                segnalazione.setNumSolleciti(oldSol + 1);
                //si aggiorna il valore nel database
                service.modificaSegnalazione(segnalazione);

                //si aggiorna il valore dei solleciti
                // anche nel file relativo ai solleciti
                File fileSollecitiW = new File(path);
                FileWriter writer = new FileWriter(fileSollecitiW, true);
                BufferedWriter bufferW = new BufferedWriter(writer);
                bufferW.write(cf + "," + id + "\n");
                bufferW.flush();
                bufferW.close();
            }

        }
        //si passa il controllo alla servlet ListApprovate
        //cosi da poter visualizzare il valore correttamente aggiornato
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("ListApprovate");
        dispatcher.forward(req, resp);
    }
}
