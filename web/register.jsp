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
        <div class = "form" id = "registration-form">
        <h1>Register</h1>
            <s:bean name="bean.Universities">
            <s:form action="reg">  
                <s:textfield class="field" name="userId" label="User ID" />
                <s:textfield class="field" name="email" label="Email Address" />
                <s:password class="field" name="password" label="Password" />
                <s:password class="field" name="confirmPassword" label="Confirm Password" />
                <s:select class="select" name="university" label="University" list="universities"/>
                <s:submit id="submit-button" value="submit" />
            </s:form>
            </s:bean>
        </div>
    </body>
</html>
