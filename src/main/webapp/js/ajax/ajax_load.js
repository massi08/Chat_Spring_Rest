$(document).ready(function () {
    var loadAllSalon = function () {
        var path = window.location.search;
        var args = new Array();
        args = path.replace("?", "").split("&");
        var salon = args[0].split("=")[1];
        var user = args[1].split("=")[1];
        var request = $.ajax({
            method: "GET",
            url: "/back-office/users/salons-list/" + salon + "/" + user ,
            dataType: "json",
        });

        request.done(function (salons) {
            printSalons(salons);
        });
        request.fail(function (jqXHR, textStatus) {
            Materialize.toast("Impossible de récupérer les salons", 4000);
        });
    };

    var printSalons = function (message) {
        var allMessages = "";

        var messagesList = message.salonPerUser;

        for (var i = 0; i < messagesList.length; i++) {
            var messageObject = messagesList[i];
            alert(messageObject);


            allMessages += " <div id=\""  + " \" class=\"received-message " + " z-depth-1\">\n" +
                "      <span>" + messageObject + "</span>\n" +
                "    </div>"
        }

        if ($(".messages-wrapper").length) {
            $("#message-wrapper").append(allMessages);
            $(".messages-wrapper").scrollTop($(".messages-wrapper")[0].scrollHeight);
        }
    };

    loadAllSalon();

});