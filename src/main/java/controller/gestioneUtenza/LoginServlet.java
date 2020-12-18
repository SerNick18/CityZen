package controller.gestioneUtenza;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pwd = req.getParameter("password");

        if(email.compareTo("")==0 || !Pattern.matches("[A-Za-z.]+[0-9]*@[A-Za-z.]+", email) || pwd.compareTo("")==0){
            //errore
        }else{
            FacadeDAO service=new FacadeDAO();
            Cittadino c = service.login(email, pwd);

            HttpSession sn=req.getSession();

            if(c!=null){
                sn.setAttribute("Utente", c);
                RequestDispatcher dispatcher=req.getRequestDispatcher("/index.jsp");
                dispatcher.forward(req, resp);
            }else{
                //email o password sbagliate
            }
        }

    }
}
