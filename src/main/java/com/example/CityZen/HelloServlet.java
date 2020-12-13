package com.example.CityZen;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/helloServlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
        String cf = request.getParameter("cf");
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        String name = request.getParameter("name");
        String cogn = request.getParameter("surname");
        String city = request.getParameter("birthCity");
        String bd = request.getParameter("birthDate");
        String s = request.getParameter("sex");
        UtenteBean usr = new UtenteBean(cf, username, pass, name, cogn, city, bd, s);
        request.setAttribute("utente", usr);
        request.getRequestDispatcher("/WEB-INF/stampa.jsp").forward(request, response);
    }


    public void destroy() {
    }
}