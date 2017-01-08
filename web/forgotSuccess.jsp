<%-- 
    Document   : forgotSuccess
    Created on : Dec 5, 2016, 10:51:12 PM
    Author     : Nick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" type="image/x-icon" href="Images/logo.png" />`
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="CSS/main-style.css" />
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="CSS/normalize.min.css">
        <title>Forgot Password</title>
    </head>
    <body>
        <img id = "logo" src ="Images/logo.png" alt="U-Chat Logo" height="100" width="100"/>
        <header><b>-CHAT</b></header>
        
        <div class="form">
            <h1>Success!</h1>
            <p>We sent a temporary code to <s:property value="%{email}"/> that you can use to 
                log in and change your password. Enter the code below:</p>
            <p class="labelStyle">User: <%=session.getAttribute("userid")%></p>
        <s:form action="code">
                <div class="field-wrap"><s:password name="code" label="Code" autocomplete="off"/></div>
                <s:submit class="button button-block" value="submit" />
                <p class="form-nav"><a href ="index.jsp"> Back to Home Page </a></p>
        </s:form>
        </div>
    </body>
</html>

