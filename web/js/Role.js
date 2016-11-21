/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function (){
    var tel = document.getElementById("tel");
    tel.setCustomValidity(" El formato debe se +99(99)9999-9999");
    $.ajax({
        url:'GetRoles',
        type: 'GET',
        dataType:'json'
    }).done(function (json){
        if(json.code===200){
            $.each($.parseJSON(json.msg)), function(i, row){
                $('<option></option>',{text:row.rolename}).attr('value', row.roleid).append('#cbRoles');
            };
        }
    });
  /* $('#frmRole').validate({
       rules:{
           rolename:{
               minlength: 3,
               maxlength: 30,
               required: true
           }
       },
       messages:{
           rolename:{
               minlength: "El rol debe tener minimo 3 caracteres",
               maxlength: "El rol debe tener maximo 30 caracteres",
               required: "El rol requiere un nombre"
           }
       },
       highlight: function(element){
           $(element).closest('.form-group').addClass('has-error');
       },
       unhighlight: function(element){
           $(element).closest('.form-group').removeClass('has-error');
       },
       errorElement:'span',
       errorClass:'help-block',
       errorPacement: function(error,element){
           if(element.parent('.input-group').length){
               error.insertAfter(element.parent());
           }else{
               error.insertAfter(element);
           }
       },
       submitHandler: function(form){
            newRole();
           return false;
       }
   });*/
   /*$('#tbRoles').DataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
        },
        ajax: {
            url: "GetRoles",
            dataSrc: function(json){
                console.log( json['msg']);
                return $.parseJSON(json["msg"]);
            }
        },
        columns: [
            {
                data: "roleid"
            },
            {
                data: "rolename"
            },
            {
                data: function(row){
                    str = "<button id='btnBorrar' class='btn btn-danger btn-xs' onclick='deleteRole("+row['roleid']+")'>Borrar</button>";
                    return str;
                }
            }
        ]
    });*/
    $('#tbRoles').DataTable({
        language: {
            url: "http://cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
        },
        ajax: {
            url: "GetRoles",
            dataSrc: function (json) {

                return $.parseJSON(json['msg']);
            }
        },
        columns: [
            {
                data: function (row) {
                    str = "<div align='right'>";
                    //str+= accounting.formatMoney( row["roleid"] );
                    //otra opcion
                    str += numeral(row["roleid"]).format('$0,0.00');
                    str += "</div>";
                    return str;
                }
            },
            {
                data: "rolename"
            },
            {
                data: function (row) {
                    str = "<div align='center'>";
                    str += "<button id='btnBorrar' class='btn btn-danger btn-xs' onclick='deleteRole(" + row["roleid"] + ")'><i class='glyphicon glyphicon-trash'></i></button>";
                    str += "&nbsp;<button id='btnEditar' class = 'btn btn-success btn-xs' onClick = 'showRole(" + row['roleid'] + ",\"" + row['rolename'] + "\")'><i class='glyphicon glyphicon-edit'></i></button>";
                    str += "<div>";
                    return str;
                }
            }
        ]


    });
});



function newRole(){
     $.ajax(
        {
         url: "NewRole",
         type:"post",
         data: $('#frmRole').serialize()     
        }
     ).done(
        function(data){
            //alert("Se realizó correctamente "+data.code);            
            if(data.code==200){
                
                $.growl.notice({message: data.msg});
                $('#tbRoles').dataTable().api().ajax.reload();
                $('#rolename').val('');
            }
            else{
                $.growl.error({message: data.msg});
            }
        }
     ).fail(
        function(){
           $.growl.error({message: "El servidor no está disponible 😞"}); 
       }
     );
}

/*function deleteRole(roleid){
     $.ajax(
        {
         url: "DeleteRole",
         type:"post",
         data: roleid     
        }
     ).done(
        function(data){
            //alert("Se realizó correctamente "+data.code);            
            if(data.code==200){
                
                $.growl.notice({message: data.msg});
                $('#tbRoles').dataTable().api().ajax.reload();
            }
            else{
                $.growl.error({message: data.msg});
            }
        }
     ).fail(
        function(){
           $.growl.error({message: "El servidor no está disponible 😞"}); 
       }
     );
}*/
function deleteRole(id) {
    swal({title: "¿Estás seguro que deseas eliminar?",
        text: "No podrás recuperar la información después de borrarla.",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Sí, eliminar",
        closeOnConfirm: false},
            function () {
                $.ajax(
                        {
                            url: "DeleteRole",
                            type: "post",
                            data: {roleid: id}
                        }
                ).done(
                        function (data) {
                            if (data.code === 200) {
                                //$.growl.notice({title: "Successful", message: data.msg });
                                swal("Eliminado", data.msg, "success");
                                $('#tbRoles').dataTable().api().ajax.reload();
                                $('#rolename').val('');
                            } else {
                                $.growl.error({message: data.msg});
                            }
                        }
                ).fail(
                        function (data) {
                            //$.growl.notice({message: "Algo va mal"});
                            swal({title: "Error", text: "Algo va mal, no se pudo eliminar", type: "error", confirmButtonText: "Cerrar"});
                        }
                );

            }
    );

}

function showRole(roleid, rolename) {
    $('#roleid').val(roleid);
    $('#rolename2').val(rolename);
    $('#modalRole').modal("show");

}
function updateRole() {
    $.ajax(
            {
                url: "UpdateRole",
                type: "post",
                data: {
                    roleid: $('#roleid').val(),
                    rolename: $('#rolename2').val()
                }
            }
    ).done(
            function (data) {
                //alert("Se realizó correctamente "+data.code);            
                if (data.code == 200) {
                    $.growl.notice({message: data.msg});
                    $('#tbRoles').dataTable().api().ajax.reload();
                    $('#modalRole').modal("toggle");
                } else {
                    $.growl.error({message: data.msg});
                }
            }
    ).fail(
            function () {
                $.growl.error({message: "El servidor no está disponible :("});
            }
    );
}

