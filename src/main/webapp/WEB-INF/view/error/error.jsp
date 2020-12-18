<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 18/12/2020
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<h1>Errore ${requestScope['javax.servlet.error.status_code']}</h1>
<!-- Scegliere una delle due opzioni per stampare l'eccezione:
la prima stampa un breve messaggio d'errore, la seconda lo stacktrace completo. -->
<pre>${requestScope['javax.servlet.error.status_code']}</pre>
<body>

</body>
</html>
