/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $('#frmRole').validate({
        rules: {
            rolename: {
                minlength: 3,
                maxlength: 20,
                required: true
            }
        },
//Mensajes para validacion form
        messages: {
            rolename: {
                required: "Capture el nombre del rol",
                minlength: "Introduzca al menos 3 caracteres",
                maxlength: "No introduzca más de 20 caracteres"
            }
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function (error, element) {
            if (element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        },
        submitHandler: function (form) {
            guardarCliente();
            return false;
        }
    });
});

function guardarCliente() {
    $.ajax(
        {
            url: "NewRole", //Dirección del Servlet
            type: "post", //Método por el que se enviarán los datos
            data: $("#frmRole").serialize() //El formulario
        }
    ).done(
        
    ).fail(
        function () { //Esto se ejecutará cuando hubo un error 
            alert("Ocurrió un error, intentelo de nuevo");
        }
    );
}
