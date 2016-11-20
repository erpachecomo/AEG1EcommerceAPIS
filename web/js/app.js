$(function () {
    $('#nav a').on('click', function (e) {
        e.preventDefault();
        var page = $(this).attr('href');
        if (page != 'cerrar') {
            $('#content').load(page);
            $('#nav li').removeClass('active');
            $(this).parent().addClass('active');
        }
    });

    $('#cerrarsesion').on('click', function (e) {
        e.preventDefault();
        $.ajax({
            url: "CerrarSesion",
            type: "POST",
            dataType: 'json'

        }).done(
                function (data)
                {
                    if (data.code === 200)
                    {
                        $.growl.notice({message: data.msg + " " + data.detail});
                        window.location = "login.html";
                        //$(location.attr('href',''));

                    } else
                    {
                        $.growl.error({message: data.msg + "" + data.detail});
                    }
                }
        ).fail(
                function ()
                {
                    $.growl.error({message: "Algo va mal no se encuentra el servidor"})
                }
        );
    });

});
