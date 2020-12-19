package controller.gestioneUtenza;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
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
        String pwd = req.getParameter("pwd");

        FacadeDAO service=new FacadeDAO();
        HttpSession sn=req.getSession();

        if(email.compareTo("")==0 || !Pattern.matches("[A-Za-z.]+[0-9]*@[A-Za-z.]+", email) || pwd.compareTo("")==0){
            throw new MyServletException("Controlla di aver inserito correttamente i campi");
        }else if(email.contains("@scafati.it")){ //login impiegato
            Impiegato impiegato = service.loginImpiegato(email, pwd);

            if(impiegato!=null){
                sn.setAttribute("Impiegato", impiegato);
                RequestDispatcher dispatcher=req.getRequestDispatcher("WEB-INF/view/GuiImpiegato/gui-impiegato.jsp");
                dispatcher.forward(req, resp);
            }else{
                throw new MyServletException("Email o password errati");
            }
        }else{ //login cittadino
            Cittadino cittadino = service.loginCittadino(email, pwd);

            if(cittadino!=null){
                sn.setAttribute("Cittadino", cittadino);
                RequestDispatcher dispatcher=req.getRequestDispatcher("WEB-INF/view/GuiCittadino/gui-cittadino.jsp");
                dispatcher.forward(req, resp);
            }else{
                throw new MyServletException("Email o password errati");
            }
        }

    }
}
