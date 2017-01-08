<%-- 
    Document   : registrationSuccess
    Created on : Nov 19, 2016, 11:51:27 AM
    Author     : Nick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="mt" uri="/WEB-INF/tlds/mytags.tld" %>

<!DOCTYPE html>
<html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="CSS/main-style.css" />
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="CSS/normalize.min.css">
        <title>U-Chat</title>
    </head>
        <% session.invalidate(); %> 
    <body>
        <img id = "logo" src ="Images/logo.png" alt="U-Chat Logo" height="100" width="100"/>
    <header><b>-CHAT</b></header>
        <div class ="form">
        <p>You have successfully registered as: </p>
        <p>
        <ul>
            <li>User ID: <s:property value="%{userId}"/></li>
            <li>Email Address: <s:property value="%{email}"/></li>
            <li>University: <s:property value="%{university}"/></li>
        </ul>
        </p>
        <a href ="index.jsp"> Continue </a>
        </div>
    </body>
</html>