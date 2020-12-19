package controller.gestioneUtenza;

import model.gestioneDati.facadeDataAccess.FacadeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/VerificaCodiceFiscale")
public class VerificaCodiceFiscaleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         *
         * controllo se il codice fiscale
         * è già presente nel database.
         *
         * */
        FacadeDAO service = new FacadeDAO();
        String cf = request.getParameter("cf");
        response.setContentType("text/xml");
        if (cf != null && cf.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$")
                && service.verificaCodiceFiscale(cf) == null) {
            response.getWriter().append("<ok/>");
        } else {
            response.getWriter().append("<no/>");
        }
    }
}
