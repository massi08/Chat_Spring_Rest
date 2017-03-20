<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="/css/materialize.min.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="/css/style.css"/>
    <link type="text/css" rel="stylesheet" href="/css/interface.css"/>
    <title> Connexion </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
</head>

<body>
<div class="disconnect row">
    <a class="back-to-salon darken-1 btn waves-effect waves-light blue" href="/admin.jsp">Page Admin</a>
    <a href="/Deconnexion.jsp"> Déconnexion</a>
</div>

<iframe class="chat-frame z-depth-1" src="salon/affichage" name="message"></iframe>
<div class="row">
    <form id="message-form" class="col s12 send-message-form" action="/back-office/messages/<%out.print(session.getAttribute("salon")+"/"+session.getAttribute("username"));%>/add" method="post" target="message">
        <div class="row valign-wrapper">
            <div class="input-field col valign">
                <input type="text" name="usermessage" id="icon_prefix2" class="message-input" autocomplete="off"></input>
                <label for="icon_prefix2">Message</label>
            </div>
            <div class="button-wrapper">
                <button type="submit" class="blue darken-1 waves-effect waves-light btn valign">Envoyer</button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.min.js"></script>
<script type="text/javascript" src="/js/init.js"></script>
<script type="text/javascript" src="/js/interface.js"></script>
</body>
</html>
