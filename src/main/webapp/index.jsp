<%@ page import="java.time.LocalDate" %>
<%@ page import="java.sql.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <div class="page-content">
            <form method="POST" action="helloServlet">
                <label>CF: </label>
                <input type="text" name="cf" onkeyup="toUpperCase(this)" required/>
                <label>Username: </label>
                <input type="text" name="username" required/>
                <label>Password: </label>
                <input type="password" name="password"  required/>
                <label>Nome: </label>
                <input type="text" name="name" required/>
                <label>Cognome: </label>
                <input type="text" name="surname" required/>
                <label>Data di nascita: </label>
                <input type="date" name="birthDate" min="1800-01-01" max="<%=Date.valueOf(LocalDate.now())%>" required/>
                <label>Città di nascita: </label>
                <input type="text" name="birthCity" required/>
                <label>Sesso: </label>
                <br>
                <input type="radio" name="sex" value="M" required/>Maschio
                <input type="radio" name="sex" value="F" required/>Femmina
                <br>

                <input type="submit" value="Crea profilo paziente" />
            </form>

            <div class="row mb-3">
                <a href="login.jsp" class="appLink">Effettua il login</a>
            </div>

            <div class="row mb-3">
                <a href="register.jsp" class="appLink">Non hai un account? Registrati</a>
            </div>
        </div>
        <br/>
    </body>
</html>