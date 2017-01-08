<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="mt" uri="/WEB-INF/tlds/mytags.tld" %>

<mt:login_check />

<jsp:useBean id="info" class="bean.UserInfo" scope="session"/>

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
    <%
        info.setInfo((String) session.getAttribute("userid"));
        String userId = info.getUserId();
        String email = info.getEmail();
        String university = info.getUniversity();
        session.setAttribute("email", email);
        session.setAttribute("university", university);
    %>
    <s:set var="email"><%= email%></s:set>
    <s:set var="university"><%= university%></s:set>
        <body>
            <div id = "top-panel">
                <img id = "logo" src ="Images/logo.png" alt="U-Chat Logo" height="100" width="100"/>
                <header><b>-CHAT</b></header>
            </div>

        <div id = "navbar">
            <p id =welcome-message>
                Logged in as <a href="profileView.jsp"><%= userId %></a>
            </p>
            <ul>
                <li><a href="index.jsp" class="buttons green">Logout</a></li>                
                <li><a href="chatpage.jsp" class="buttons green">Chat</a></li>
            </ul>
        </div>

        <div class="form">
            <ul class="tab-group">
                <li class="tab active"><a href="#profile">Profile</a></li>
                <li class="tab"><a href="#edit-profile">Edit Info</a></li>
                <li class="tab"><a href="#change-password">Password</a></li>
            </ul>
            <div class="tab-content">
            <div id = "profile">
                <div>
                    <h1><%= userId%>'s Profile </h1>
                    <p>Email Address: <%= email%></p>
                    <p>University: <%= university%></p>
                </div>
            </div>

            <div id = "edit-profile">
                <div id = "edit-form">
                    <h1>Edit <%= userId%>'s Profile</h1>
                    <s:bean name="bean.Universities">
                        <s:form action="editProfile">  
                            <s:textfield class="field" name="email" label="Email Address" value="%{#email}"/>
                            <s:select class="select" name="university" label="University" list="universities" value="%{#university}"/>
                            <s:submit id="submit-button" value="submit" />
                        </s:form>
                    </s:bean>
                </div>
            </div>

            <div id = "change-password">
                <div  id = "edit-form">
                    <h1>Change <%= userId%>'s Password</h1>
                    <s:form action="changePass">  
                        <s:password class="field" name="password" label="New Password"/>
                        <s:password class="field" name="confirmPassword" label="Confirm Password"/>
                        <s:submit id="submit-button" value="submit" />
                    </s:form>
                </div>
            </div>
            </div>
        </div>
    <script src='Scripts/jquery.min.js'></script>
    <script src="Scripts/loginscript.js"></script>
    </body>
</html>
