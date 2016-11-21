/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function (){
    $('#tbCompany').DataTable({
        language: {
            url: "http://cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
        },
        ajax: {
            url: "GetCompany",
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
                    str += numeral(row["companyid"]).format('$0,0.00');
                    str += "</div>";
                    return str;
                }
            },
            {
                data: "companyname"
            },
            {
                data: "neighborhood"
            },
            {
                data: "zipcode"
            },
            {
                data: "city"
            },
            {
                data: "country"
            },
            {
                data: "state"
            },
            {
                data: "region"
            },
            {
                data: "street"
            },
            {
                data: "streetnumber"
            },
            {
                data: "phone"
            },
            {
                data: "rfc"
            },
            {
                data: "logo"
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



function newCompany(){
     $.ajax(
        {
         url: "NewCompany",
         type:"post",
         data: $('#frmCompany').serialize()     
        }
     ).done(
        function(data){
            //alert("Se realizó correctamente "+data.code);            
            if(data.code==200){
                
                $.growl.notice({message: data.msg});
                $('#tbCompany').dataTable().api().ajax.reload();
                //$('#rolename').val('');
                //$('#rolename').val('');
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
function deleteCompany(id) {
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
                            url: "DeleteCompany",
                            type: "post",
                            data: {companyid: id}
                        }
                ).done(
                        function (data) {
                            if (data.code === 200) {
                                //$.growl.notice({title: "Successful", message: data.msg });
                                swal("Eliminado", data.msg, "success");
                                $('#tbCompany').dataTable().api().ajax.reload();
                                //$('#rolename').val('');
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
                url: "UpdateCompany",
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
                    $('#tbCompany').dataTable().api().ajax.reload();
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



