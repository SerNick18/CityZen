package controller.gestioneUtenza;

import model.gestioneDati.facadeDataAccess.FacadeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/VerificaEmail")
public class VerificaEmailServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FacadeDAO service = new FacadeDAO();
        String email = request.getParameter("email");

        response.setContentType("text/xml");
        if (email != null && email.matches("[A-Za-z.]+[0-9]*@[A-Za-z.]+")
                && service.verificaEmail(email) == null) {
            response.getWriter().append("<ok/>");
        } else {
            response.getWriter().append("<no/>");
        }
    }

}
