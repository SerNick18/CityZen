package controller.gestioneProfilo;

import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/profilo")
public class VisualizzaProfilo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address ="";
        HttpSession session = request.getSession();
        Impiegato impiegato =(Impiegato)session.getAttribute("Impiegato");
        Cittadino cittadino = (Cittadino)session.getAttribute("Cittadino");
        if(cittadino!= null) {
            address = "WEB-INF/view/GuiCittadino/profilo.jsp";
        } else
            if(impiegato!=null) {
                address = "WEB-INF/view/GuiImpiegato/profilo.jsp";
            }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
