$(function ()
{
    $('#frmLogin').validate({
        rules: {
            lang: 'es',
            username: {
                minlength: 3,
                maxlength: 20,
                required: true,
                /*remote: {
                    url: "ExistUser",
                    type: "POST"
                }*/

            },
            password: {
                minlength: 3,
                maxlength: 20,
                required: true
            }
        },
        messages: {
            username: {
                //remote: "El usuario no existe, por favor elige otro"
            }
        },
        /*        highlight
         * Sirve para darlecolor y formato al mensaje de error color rojo debajo del elemento*/
        highlight: function (element)
        {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element)
        {
            $(element).closest('.form-group').removeClass('has-error');
        },
        /*le da color rojo a los mensajes*/
        errorElement: 'span',
        errorClass: 'help-block',
        /*sirve para que el mensaje no se desface del icono
         *  y aparesca en la parte baja del elemento sin afectar la vista*/
        errorPlacement: function (error, element)
        {
            if (element.parent('.input-group').length)
            {
                error.insertAfter(element.parent());
            } else
            {
                error.insertAfter(element);
            }
        },
        /*Realiza la accion cuando un boton es precionado*/
        submitHandler: function (form)
        {
            login()
            return false;
        }

    });
});

function login()
{
    var para = {
        "username": $('#username').val(),
        "password": $('#password').val()
    };
    $.ajax({
        url: "LoginUser",
        type: "POST",
        /*Manda todo el formulario
         * como mandar parametros por separado en data:
         */
        data: para

    }).done(
            function (data)
            {
                // alert("Se realizo correctamente"+data.code);
                if (data.code === 200)
                {
                    $.growl.notice({message: data.msg + " " + data.detail});
                    window.location="index.html";
                    
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

}
$.extend($.validator.messages, {
    required: "Este campo es obligatorio.",
    remote: "Por favor, rellena este campo.",
    email: "Por favor, escribe una dirección de correo válida.",
    url: "Por favor, escribe una URL válida.",
    date: "Por favor, escribe una fecha válida.",
    dateISO: "Por favor, escribe una fecha (ISO) válida.",
    number: "Por favor, escribe un número válido.",
    digits: "Por favor, escribe sólo dígitos.",
    creditcard: "Por favor, escribe un número de tarjeta válido.",
    equalTo: "Por favor, escribe el mismo valor de nuevo.",
    extension: "Por favor, escribe un valor con una extensión aceptada.",
    maxlength: $.validator.format("Por favor, no escribas más de {0} caracteres."),
    minlength: $.validator.format("Por favor, no escribas menos de {0} caracteres."),
    rangelength: $.validator.format("Por favor, escribe un valor entre {0} y {1} caracteres."),
    range: $.validator.format("Por favor, escribe un valor entre {0} y {1}."),
    max: $.validator.format("Por favor, escribe un valor menor o igual a {0}."),
    min: $.validator.format("Por favor, escribe un valor mayor o igual a {0}."),
    nifES: "Por favor, escribe un NIF válido.",
    nieES: "Por favor, escribe un NIE válido.",
    cifES: "Por favor, escribe un CIF válido."
});