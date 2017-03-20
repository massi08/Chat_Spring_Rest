$(document).ready(function () {
    $("#login").click(function () {
        var user = $('#user').val();
        var salon = $('#salon').val();
        if (user.trim() === '' || salon.trim() === '') {
            return;
        }
        var request = $.ajax({
            method: "POST",
            url: "/back-office/salon/login",
            dataType: "json",
            data: {
                user: user,
                salon: salon
            }
        });
        request.done(function (msg) {
            window.location = '/ajax/interface_ajax.html?salon=' + salon + '&user=' + user;
        });
        request.fail(function (jqXHR, textStatus) {
            Materialize.toast("Echec de la connexion", 4000);
        });
    });
});