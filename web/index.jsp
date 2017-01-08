<%-- 
    Document   : index
    Created on : Nov 30, 2016, 1:45:52 PM
    Author     : Nick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <link rel="shortcut icon" type="image/x-icon" href="Images/logo.png" />`
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="CSS/main-style.css" />
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="CSS/normalize.min.css">
        <title>Uchat</title>
    </head>
    <% session.invalidate();%>
<body>
    <img id = "logo" src ="Images/logo.png" alt="U-Chat Logo" height="100" width="100"/>
    <header><b>-CHAT</b></header>
    <div class="form">
   
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
        <!--<li class="tab"><a href="#guest">Guest</a></li>-->
      </ul>
      
      <div class="tab-content">
        <div id = "signup">
        <h1>Sign Up for Free</h1>
            <s:bean name="bean.Universities">
            <s:form action="reg">  
                <div class="field-wrap"><s:textfield name="userId" label="User ID" /></div>
                 <div class="field-wrap"><s:textfield name="email" label="Email Address" /></div>
                 <div class="field-wrap"><s:password name="password" label="Password" /></div>
                 <div class="field-wrap"><s:password name="confirmPassword" label="Confirm Password" /></div>
                 <div class="field-wrap"><s:select class="select" name="university" label="University" list="universities"/></div>
                <s:submit class="button button-block" type="submit" value="submit" />
            </s:form>
            </s:bean>
        </div>
        <div id = "login">
            <h1>Login</h1>
            <s:form action="log">
                <div class="field-wrap"><s:textfield name="id" label="Login ID" autocomplete="off"/></div>
                <div class="field-wrap"><s:password name="pass" label="Password" autocomplete="off"/></div>
                <s:submit class="button button-block" value="submit" />
                <p class="form-nav"><a href ="forgotPassword.jsp"> Forgot password? </a></p>
            </s:form>
        </div>
        <!--<div id="guest">
              <h1>Log in as guest user</h1>
              <form action="/" method="post">
                 
              <button class="button button-block"/>Chat</button>
              </form>
          </div>-->
      </div><!-- tab-content -->
</div> <!-- /form -->
    <script src='Scripts/jquery.min.js'></script>
    <script src="Scripts/loginscript.js"></script>
    </body>

</html>
