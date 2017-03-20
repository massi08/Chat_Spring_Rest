(function ($) {
    $(function () {
        $(".messages-wrapper").scroll(function () {
            var scroll = $(this).scrollTop();
            if (scroll > 0) {
                $(".message-status").addClass("z-depth-1");
            } else {
                $(".message-status").removeClass("z-depth-1");
            }
        });

        $(window).load(function () {
            if ($(".messages-wrapper").length)
                $(".messages-wrapper").scrollTop($(".messages-wrapper")[0].scrollHeight);
        });
    });
})(jQuery);