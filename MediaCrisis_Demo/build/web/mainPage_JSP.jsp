<%-- 
    Document   : mainPage_JSP
    Created on : Feb 2, 2020, 5:28:20 PM
    Author     : Administrator
--%>


<%@page import="MediaCrisis.Model.UserLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
           UserLogin result =(UserLogin)session.getAttribute("USERLOGIN"); 
            out.print("Hello User: You have logged in with username "+ result.getUsername()); %>
            <br>
        <%
            out.print("Your role is: "+ result.getRole()); 
        %> 
    </body>
</html>
