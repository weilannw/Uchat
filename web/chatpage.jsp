<%-- 
    Document   : chatpage
    Created on : Dec 2, 2016, 5:00:37 PM
    Author     : Nick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" uri="/WEB-INF/tlds/mytags.tld" %>

<mt:login_check />


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" type="image/x-icon" href="Images/logo.png" />`
        <link rel="stylesheet" type="text/css" href="CSS/main-style.css" />
        <link rel="stylesheet" type="text/css" href="CSS/chat.css" />
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="CSS/font-awesome.css" />
        <link rel="stylesheet" type="text/css" href="CSS/normalize.min.css">
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
        <title>U-Chat</title>
    </head>
    <body>
        <header>
            <b><%=session.getAttribute("university")%></b>
        </header>
      
        <div id="chat-window">
            <div id="chat">
            </div>    
            <div id="text-entry" class="chat-box-footer">
                <div class="input-group" id="textfield">
                    <input maxlength="150" id="text" class="form-control" placeholder="Enter Text Here..." />
                <div class="input-group-btn"><button class="btn btn-info" type="button" onclick="sendText();">SEND</button></div>
                </div>
        </div>
        </div>
                <script>
    var chat;        
    var request;
    var chatResponse = {str : ""};
    var prevresponse = {str : ""};
    var voteResponse = {str : ""};
    window.onload = function() {
        try { 
            request = new XMLHttpRequest();
        }
        catch (e) {
            try { 
                request = new ActiveXObject('MSXML2.XMLHTTP.5.0');
            }
            catch (e) {
                request = false;
            }
        }
        if (! request) alert('Ajax setup failed');
        chat = document.getElementById("chat"); 
        receiveAll();
    }
    function doAjax(url, method) {
        request.open(method, url, true);
        request.onreadystatechange = updatePage;
        request.send(null);
    }
    function doAjax(url, getsResponse, responseMethod) {
        request.open("execute", url, true);
        if (getsResponse){
            request.onreadystatechange = responseMethod;
        }
        else{
            request.onreadystatechange = null;
        }
        request.send(null);
    }
    function getChatResponse(){
        updatePage(chatResponse);
    }
    function getVoteResponse(){
        updatePage(voteResponse);
    }
    function updatePage(response) {
        if (request.readyState == 4) {
            if (request.status == 200) {
                response.str = request.responseText;
            }
        }
    }
            var university = '<%=session.getAttribute("university")%>';
            var id = '<%=session.getAttribute("userid")%>';
            function sendText(){
                doAjax("sendtext?userid=" + id + "&message=" + document.getElementById('text').value
                        + "&university=" + university, false);
                document.getElementById("text").value="";
            }
            function receiveAll(){
                doAjax("receivetexts?university=" + university, true, getChatResponse);
                if(prevresponse.str.valueOf() !== chatResponse.str.valueOf()){
                    chat.innerHTML = chatResponse.str; 
                    chat.scrollTop = chat.scrollHeight;
                }
                prevresponse.str = chatResponse.str;
                setTimeout(receiveAll, 50);
            }
            function vote(messageTime, posterId, isUp){
                doAjax("voteontext?time=" + messageTime + "&poster=" + posterId
                        + "&isUp=" + isUp + "&voter=" + id + "&university=" + university, false);
            }        
        </script>  
    </body>
</html>

