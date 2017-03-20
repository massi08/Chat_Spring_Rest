(function ($) {
    $(function () {
        $("#get-message-modal-form").submit(function () {
            var member = $('#get-message-input').val();
            //console.log("hERRRRRRRRRRRE");
            var memberTrim = member.trim();
            var baseTarget = "/back-office/message/";
            if ($.isNumeric(memberTrim)) {
                var generatedAttribute = baseTarget + memberTrim;
                $("#get-message-modal-form").attr('action', generatedAttribute);
            }
        });

        $("#get-all-messages-from-modal-form").submit(function () {
            var member = $('#get-all-messages-from-input').val();
            var memberTrim = member.trim();
            var baseTarget = "/back-office/messages/get-from-";
            if ($.isNumeric(memberTrim)) {
                var generatedAttribute = baseTarget + memberTrim;
                $("#get-all-messages-from-modal-form").attr('action', generatedAttribute);
            }
        });

        $(document).keyup(function (e) {
            if (e.keyCode == 27) { //Si on appuie sur escape
                leanModalAction(".modal", "close", true);
            }
        });

        var modalHandler = function (idModal) {
            $("#open-" + idModal).on("click", function () {
                leanModalAction("#" + idModal + "-modal", "open", true);
            });

            $("#" + idModal + "-close").click(function () {
                leanModalAction("#" + idModal + "-modal", "close", true);
            });

            $("#" + idModal + "-modal-submit").on("click", function () {
                var member = $("#" + idModal + "-input").val();
                if (member.trim() != '') {
                    $("#" + idModal + "-modal-form").submit();
                    setTimeout(function () {
                        $("#" + idModal + "-modal-form")[0].reset();
                    }, 0);
                }
                leanModalAction("#" + idModal + "-modal", "close", true);
            });
        };

        modalHandler("get-message");
        modalHandler("add-member");
        modalHandler("modify-pseudo");
        modalHandler("delete-last-message");
        modalHandler("get-all-messages-from");
        modalHandler("delete-salon");
    });
})(jQuery);