<%@ page import="com.capgemini.heskuelita.core.beans.User" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>Login Page</title>
</head>

<body>
<%User us = (User)session.getAttribute ("user");%>
Bienvenido : <%=us.getUserName() + " [ " + us.getEmail()  + " ]"%>
</body>

</html>