<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="css/style.css"/>
  <link type="text/css" rel="stylesheet" href="css/interface.css"/>
  <title> Connexion </title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
</head>

<body>
<div class="disconnect row">
  <a class="back-to-salon" href="/interface.html">Retour au salon</a>
  <a href="Deconnexion.jsp"> Déconnexion</a>
</div>

<div class="admin-links col">
  <h4>Chat version Spring</h4>
  <a class="blue darken-1 waves-effect waves-light btn" href="/back-office/salon/<%out.print(session.getAttribute("salon")+"/"+session.getAttribute("username"));%>">Aller vers le chat</a>

  <h4>Opérations sur les utilisateurs</h4>
  <a id="open-add-member" class="blue darken-1 waves-effect waves-light btn">Ajouter un membre</a>
  <a id="open-modify-pseudo" class="blue darken-1 waves-effect waves-light btn">Modifier Pseudo</a>

  <h4>Opérations sur les messages</h4>
  <a class="blue darken-1 waves-effect waves-light btn" href="/back-office/messages/<%out.print(session.getAttribute("salon")+"/"+session.getAttribute("username"));%>/all">Liste des messages</a>
  <a id="open-get-message" class="blue darken-1 waves-effect waves-light btn">Récuper un message</a>
  <a id="open-get-last-message" class="blue darken-1 waves-effect waves-light btn" href="/back-office/messages/<%out.print(session.getAttribute("salon")+"/"+session.getAttribute("username"));%>/last">Récuper le dernier message</a>
  <a id="open-delete-last-message" class="blue darken-1 waves-effect waves-light btn">Supprimer le dernier message</a>

  <h4>Opérations sur le salon</h4>
  <a class="blue darken-1 waves-effect waves-light btn" href="/back-office/users/salons-list/<%out.print(session.getAttribute("salon")+"/"+session.getAttribute("username"));%>">Voir Mes Salons</a>
  <a class="blue darken-1 waves-effect waves-light btn" href="/back-office/salon/<%out.print(session.getAttribute("salon")+"/"+session.getAttribute("username"));%>/message-number">Nombres de messages sur ce salon</a>
  <a id="open-get-all-messages-from" class="blue darken-1 waves-effect waves-light btn">Récupérer tous les message à partir de</a>
  <a id="open-delete-salon" class="blue darken-1 waves-effect waves-light btn">Supprimer ce salon</a>
</div>

<div id="get-message-modal" class="modal">
  <div class="modal-content">
    <h4>Récuperer un message</h4>
    <div class="row">
      <form id="get-message-modal-form" class="col s12" method="get" action="/back-office/message/<%out.print(session.getAttribute("salon")+"/"+session.getAttribute("username"));%>/">
        <div class="row modal-form-row">
          <div class="input-field col s12">
            <input name="numero" id="get-message-input" type="text" class="validate">
            <label for="get-message-input">Numéro du message</label>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div class="modal-footer">
    <a id="get-message-close" class="waves-effect waves-blue btn-flat">Annuler</a>
    <a id="get-message-modal-submit" class="modal-action waves-effect waves-blue btn-flat">Valider</a>
  </div>
</div>

<div id="add-member-modal" class="modal">
  <div class="modal-content">
    <h4>Ajouter un membre</h4>
    <div class="row">
      <form id="add-member-modal-form" class="col s12" method="post" action="back-office/users/add/<%out.print(session.getAttribute("salon")+"/"+session.getAttribute("username"));%>">
        <div class="row modal-form-row">
          <div class="input-field col s12">
            <input name="user" id="add-member-input" type="text" class="validate">
            <label for="add-member-input">Pseudo</label>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div class="modal-footer">
    <a id="add-member-close" class="waves-effect waves-blue btn-flat">Annuler</a>
    <a id="add-member-modal-submit" class="modal-action waves-effect waves-blue btn-flat">Ajouter</a>
  </div>
</div>

<div id="delete-last-message-modal" class="modal">
  <div class="modal-content">
    <h4>Supprimer dernier message?</h4>
    <div class="row">
      <form id="delete-last-message-modal-form" class="col s12" method="delete" action="/back-office/messages/<%out.print(session.getAttribute("salon")+"/"+session.getAttribute("username"));%>/delete">
        <div class="row modal-form-row">
          <div class="input-field col s12">
            <input name="new_username" id="delete-last-message-input" type="text" class="validate" value="yes" disabled>
            <label for="delete-last-message-input">Confirmer</label>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div class="modal-footer">
    <a id="delete-last-message-close" class="waves-effect waves-blue btn-flat">Annuler</a>
    <a id="delete-last-message-modal-submit" class="modal-action waves-effect waves-blue btn-flat">Supprimer</a>
  </div>
</div>

<div id="modify-pseudo-modal" class="modal">
  <div class="modal-content">
    <h4>Modification du pseudo</h4>
    <div class="row">
      <form id="modify-pseudo-modal-form" class="col s12" method="post" action="back-office/users/modify/<%out.print(session.getAttribute("salon")+"/"+session.getAttribute("username"));%>">
        <div class="row modal-form-row">
          <div class="input-field col s12">
            <input name="new_username" id="modify-pseudo-input" type="text" class="validate">
            <label for="modify-pseudo-input">Nouveau Pseudo</label>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div class="modal-footer">
    <a id="modify-pseudo-close" class="waves-effect waves-blue btn-flat">Annuler</a>
    <a id="modify-pseudo-modal-submit" class="modal-action waves-effect waves-blue btn-flat">Modifier</a>
  </div>
</div>

<div id="get-all-messages-from-modal" class="modal">
  <div class="modal-content">
    <h4>Récupérer les messages à partir d'un message</h4>
    <div class="row">
      <form id="get-all-messages-from-modal-form" class="col s12" method="get" action="">
        <div class="row modal-form-row">
          <div class="input-field col s12">
            <input name="messageNbr" id="get-all-messages-from-input" type="text" class="validate">
            <label for="get-all-messages-from-input">Numéro du message</label>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div class="modal-footer">
    <a id="get-all-messages-from-close" class="waves-effect waves-blue btn-flat">Annuler</a>
    <a id="get-all-messages-from-modal-submit" class="modal-action waves-effect waves-blue btn-flat">Modifier</a>
  </div>
</div>

<div id="delete-salon-modal" class="modal">
  <div class="modal-content">
    <h4>Modification du pseudo</h4>
    <div class="row">
      <form id="delete-salon-modal-form" class="col s12" method="put" action="back-office/salon/<%out.print(session.getAttribute("salon")+"/"+session.getAttribute("username"));%>/delete">
        <div class="row modal-form-row">
          <div class="input-field col s12">
            <input name="new_username" id="delete-salon-input" type="text" class="validate" value="oui">
            <label for="delete-salon-input">Confirmer</label>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div class="modal-footer">
    <a id="delete-salon-close" class="waves-effect waves-blue btn-flat">Annuler</a>
    <a id="delete-salon-modal-submit" class="modal-action waves-effect waves-blue btn-flat">Supprimer</a>
  </div>
</div>


<div id="popup-overlay"></div>

<script type="text/javascript" src="./js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="./js/materialize.min.js"></script>
<script type="text/javascript" src="./js/init.js"></script>
<script type="text/javascript" src="./js/admin.js"></script>
</body>
</html>
