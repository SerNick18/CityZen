<%@ page import="com.example.CityZen.UtenteBean" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: pnapo
  Date: 13/12/2020
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Title</title>
    </head>
    <body>
    <div class="page-content">
        <table>
            <tbody>
            <% UtenteBean usr = (UtenteBean) request.getAttribute("utente"); %>
            <tr>
                <td><%=usr.getCf() %></td>
                <td><%=usr.getBirthCity()%></td>
                <td><%=usr.getName()%></td>
                <td><%=usr.getPassword() %></td>
                <td><%=usr.getUsername() %></td>
            </tr>
            </tbody>
        </table>
    </div>
    </div>
    </body>
</html>
