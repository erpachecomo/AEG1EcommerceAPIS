$(function ()
{
    $('#frmLogin').validate({
        rules: {
            username: {
                minlength: 3,
                maxlength: 20,
                required: true

            },
            password: {
                minlength: 3,
                maxlength: 20,
                required: true
            }
        },
        messages: {
            /*rolename: {
             minlength: "Introduzca al menos 3 caracteres" ,
             maxlength: "Introduzca maximo 20 caracteres" ,
             required: "Inrodusca el Nombre de Usuario"
             } ,*/
            rolename: {
                minlength: "Introduzca al menos 3 caracteres",
                maxlength: "Introduzca maximo 20 caracteres",
                required: "Inroduzca la contraseña"
            }
        },
        /*        highlight
         * Sirve para darlecolor y formato al mensaje de error color rojo debajo del elemento*/
        highlight: function (element)
        {
            $(element).closest('.form-group').addClass('has-error');
        },
        /*        unhighlight
         * Sirve para regresarle el color y formato al mensaje
         * de que el error ya fue corregido color azul debajo del elemento*/
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
        "username": $('#user').val(),
        "pass": $('#pwd').val()
    };
    console.log(para);
    $.ajax({
        url: 'webresources/product/login',
        type: "GET",
        data: para

    }).done(
            function (data1)
            {
                var data = $.parseJSON(data1);

                // alert("Se realizo correctamente"+data.code);
                if (data.code === 200)
                {
                    $.growl.notice({message: "Bienvenido " + $.parseJSON(data.msg).username});
                    $.post("/AEEcommerce/index.jsp", {apikey: $.parseJSON(data.msg).apikey, userid: $.parseJSON(data.msg).userid}, function () {
                        jQuery(location).attr('href', '/AEEcommerce/index.jsp');    
                    });
                    
                } else
                {
                    $.growl.error({message: data.msg + "" + data.details});
                }
            }
    ).fail(
            function ()
            {
                $.growl.error({message: "Algo va mal no se encuentra el servidor"})
            }
    );

}
