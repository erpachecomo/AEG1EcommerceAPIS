$(function () {
    
    $('#tbUsers').DataTable({
        responsive: true,
        language: {
            url: "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
        },
        ajax:{
            url: "GetUsers",
            dataSrc: function(json){
                return $.parseJSON(json["msg"]);
            }
        },
        columns: [
            {
                data: function (row) {
                    str = " <div align='right'>";
                    str += row['userid'];
                    str += "</div>";
                    return str;
                }
            },//clave
            {
                data: "username"
            },
            {
                data: "phone"
            },
            {
                data: "cellphone"
            },
            {
                data: "photo"
            },
            {
                data: "email"
            },
            {
                data: function (row) {
                    return row['companyid']['companyname'];
                    
                }
            },
            {
                data: function (row) {
                    return row['roleid']['rolename'];
                    
                }
            },
            {
                data: "gender"
            },
            {
                data: "country"
            },
            {
                data: "zipcode"
            },
            {
                data: "region"
            },
            {
                data: "state"
            },
            {
                data: "city"
            },
            {
                data: "neigborhood"
            },
            {
                data: "street"
            },
            {
                data: "streetnumber"
            },
            {
                data: function(row){
                    str = "<div align = 'center'>";
                    str += "<button id='btnBorrar' class='btn btn-danger btn-xs' onclick='deleteUsername("+row['userid']+")'><i class='glyphicon glyphicon-trash'></i></button>";
                    str += "&nbsp;<button id='btnEditar' class='btn btn-success btn-xs' onclick='showUser("+
                            row['userid']+","+
                            "\""+row['username']+"\","+
                            "\""+row['phone']+"\","+
                            "\""+row['cellphone']+"\","+
                            "\""+row['photo']+"\","+
                            "\""+row['email']+"\","+
                            row['companyid']['companyid']+","+
                            row['roleid']['roleid']+","+
                            "\""+row['gender']+"\","+
                            "\""+row['country']+"\","+
                            "\""+row['zipcode']+"\","+
                            "\""+row['region']+"\","+
                            "\""+row['state']+"\","+
                            "\""+row['city']+"\","+
                            "\""+row['neigborhood']+"\","+
                            "\""+row['street']+"\","+
                            "\""+row['streetnumber']+"\","+
                            "\""+row['companyid']['companyname']+"\","+
                            "\""+row['roleid']['rolename']+"\""+
                            ")'><i class='glyphicon glyphicon-edit'></i></button>";
                    str += "<div>";
                    return str;
                }
            }
        ]
    });
    $('#btnModificar').on('click', function (){
        $('frmEditUser').submit();
    });
    $.ajax({
                url: 'https://restcountries.eu/rest/v1/all',
                type: 'GET',
                dataType: 'json'
            }).done(function (json) {

                $.each(json, function (i, country) {
                    $('<option></option>', {text: country.name}).attr('value', country.alpha2Code.toLowerCase()).appendTo('#cbCountries');
                    $('<option></option>', {text: country.name}).attr('value', country.alpha2Code.toLowerCase()).appendTo('#cbCountries2');
                });
            });
    $.ajax({
        url: 'GetRoles',
        type: 'GET',
        dataType: 'json'
    }).done(function(json){
        if(json.code === 200)
            $.each($.parseJSON(json.msg), function(i,row){
                $('<option></option>',{text:row.rolename}).attr('value',row.roleid).appendTo('#cbRole');
                $('<option></option>',{text:row.rolename}).attr('value',row.roleid).appendTo('#cbRole2');
            });
        
    });
    
    $.ajax({
        url: 'GetCompanies',
        type: 'GET',
        dataType: 'json'
    }).done(function(json){
        if(json.code === 200)
            $.each($.parseJSON(json.msg), function(i,row){
                $('<option></option>',{text:row.companyname}).attr('value',row.companyid).appendTo('#cbCompany');
                $('<option></option>',{text:row.companyname}).attr('value',row.companyid).appendTo('#cbCompany2');
            });
    });
    $('#zip').on('keyup', function (e) {
        var zip_in = $(this);

// Make HTTP Request
            $.ajax({
                url: "http://api.zippopotam.us/" + $('#cbCountries').val() + "/" + zip_in.val(),
                cache: false,
                dataType: "json",
                type: "GET",
                success: function (result, success) {
                    // Make the city and state boxes visible
                    // US Zip Code Records Officially Map to only 1 Primary Location
                    places = result['places'][0];
                    $("#neigborhood").val(places['place name']);
                    $("#state").val(places['state']);
                    zip_in.removeClass('has-error');
                },
                error: function (result, success) {
                    zip_in.addClass('has-error');
                }
            });
    });
    $('#zip2').on('keyup', function (e) {
        var zip_in = $(this);

// Make HTTP Request
            $.ajax({
                url: "http://api.zippopotam.us/" + $('#cbCountries2').val() + "/" + zip_in.val(),
                cache: false,
                dataType: "json",
                type: "GET",
                success: function (result, success) {
                    // Make the city and state boxes visible
                    // US Zip Code Records Officially Map to only 1 Primary Location
                    places = result['places'][0];
                    $("#neigborhood2").val(places['place name']);
                    $("#state2").val(places['state']);
                    zip_in.removeClass('has-error');
                },
                error: function (result, success) {
                    zip_in.addClass('has-error');
                }
            });
    });
    /*$('#cbCompany').selectpicker();
    $('#cbCountries').selectpicker();
    $('#cbGender').selectpicker();
    $('#cbRole').selectpicker();*/
    $.validator.addMethod("phone", function(value, element) {
                var re = new RegExp(/^\+?[1-9]\d{1,14}$/);
                return this.optional(element) || re.test(value);
            }, "Telefono invalido: Por favor verificalo.");
    $('#frmUsr').validate({
        rules: {
            username: {
                minlength: 3,
                maxlength: 20,
                required: true
            },
            password: {
                minlength: 6,
                maxlength: 20,
                required: true
            },
            password2: {
                minlength: 6,
                maxlength: 20,
                required: true,
                equalTo: "#password"
            },
            phone:{
                phone: true
            },
            cellphone:{
                phone: true
            },
            email:{
                email: true,
                required: true
            },
            photo:{
                url: true
            },
            cbCompany:{
                required: true
            },
            cbRole:{
                required: true
            },
            cbGender:{
                required: true
            },
            cbCountries:{
                required: true
            }
          
        },
        messages: {
            username: {
                minlength: "Introduzca al menos 3 caracteres",
                maxlength: "Introduzca máximo 20 caracteres",
                required: "Capture el nombre de usuario"
                
            },
            password: {
                minlength: "Introduzca al menos 6 caracteres",
                maxlength: "Introduzca máximo 20 caracteres",
                required: "Capture la contraseña"
            },
            password2: {
                minlength: "Introduzca al menos 6 caracteres",
                maxlength: "Introduzca máximo 20 caracteres",
                required: "Vuelva a capturar la contraseña",
                equalTo: "Las contraseñas no coinciden"
            },
            phone: {
                phone: "Telefono inválido"
            },
            cellphone: {
                phone: "Telefono celular inválido"
            },
            email: {
                email: "Correo electrónico inválido",
                required: "Capture el correo electrónico"
            },
            photo: {
                url: "URL de fotografía inválida"
            },
            cbCompany: {
                required: "Selecciona una compañía"
            },
            cbRole: {
                required: "Selecciona un rol"
            },
            cbGender: {
                required: "Selecciona un género"
            },
            cbCountries: {
                required: "Selecciona un país"
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
            newUser();
            return false;
        }

    });
    
    
    
});

function updateUser(roleid, rolename){
    swal(
            {
                title: "¿Estas seguro que deseas actualizar este registro?", text: "",
                type: "warning", showCancelButton: true,
                confirmButtonColor: "#DD6B55", confirmButtonText: "Aceptar!",
                cancelButtonText: "Cancelar", closeOnConfirm: false,
                closeOnCancel: false
            }, function (isConfirm) {
        if (isConfirm) {

            var para = {
                "roleid": $('#roleid').val(),
                "rolename": $('#rolename2').val()

            };
            ///Comienza a Borrar    
            $.ajax({
                url: "UpdateRole",
                type: "post",
                /*Manda todo el formulario
                 * como mandar parametros por separado en data:
                 */
                data: para

            }).done(
                    function (data) {
                        // alert("Se realizo correctamente"+data.code); 
                        if (data.code === 200) {
                            $.growl.notice({message: data.msg + " " + data.details});
                            swal("Actualizado!", "El registro se Actualizo correctamente", "success");
                            $('#tbRoles').dataTable().api().ajax.reload();
                            $('#modalRole').modal("hide");
                            //$('#rolename').val("");
                        } else
                            $.growl.error({message: data.msg + "" + data.details});
                    }
            ).fail(
                    function () {
                        $.growl.error({message: "Algo va mal no se encuentra el servidor"})
                    }
            );
        } else {
            swal("Cancelado", "Accion Cancelada", "error");
        }
    });
}

function newUser(){
    
    
     $.ajax(
        {
         url: "NewUser",
         type:"post",
         data: {
            roleid : $('#cbRole').val(),
            companyid:$('#cbCompany').val(),
            country: $('#cbCountries').val(),
            gender: $('#cbGender').val(),
            username: $('#username').val(),
            password: $('#password').val(),
            phone: $('#phone').val(),
            neigborhood: $('#neigborhood').val(),
            zipcode: $('#zip').val(),
            city: $('#city').val(),
            state: $('#state').val(),
            region: $('#region').val(),
            street: $('#street').val(),
            email: $('#email').val(),
            streetnumber: $('#streetnumber').val(),
            photo: $('#photo').val(),
            cellphone: $('#cellphone').val()
         }
        }
     ).done(
        function(data){
            //alert("Se realizó correctamente "+data.code);            
            if(data.code==200){
                
                $.growl.notice({message: data.msg});
                $('#tbUsers').dataTable().api().ajax.reload();
                cleanFields();
            }
            else{
                console.log($.parseJSON(data))
                $.growl.error({message: data.msg});
            }
        }
     ).fail(
        function(){
           $.growl.error({message: "El servidor no está disponible 😞"}); 
       }
     );
}


function cleanFields(){
    $('#username').val('');
    $('#password').val('');
    $('#password2').val('');   
    
}
function showUser(userid,username,phone,cellphone,photo,email,companyid,roleid,gender,country,zipcode,region,state,city,neigborhood,street,streetnumber,companyname,rolename){
    $('#userid').val(userid)
    $('#roleid').val(roleid)
    $('#companyid').val(companyid)
    $('#username2').val(username)
    $('#phone2').val(phone)
    $('#cellphone2').val(cellphone)
    $('#photo2').val(photo)
    $('#email2').val(email)
    
    var text = companyname;
       $('select[name=selValue]').val(1);
   $('select[name=selValue]').change();
   
    $('.bootstrap-select .filter-option').text(text);
    $('select[name=cbCompany2]').val(companyid);
    $('select[name=cbCompany2]').change();
    text = rolename;
    $('.bootstrap-select .filter-option').text(text);
    $('select[name=cbRole2]').val(roleid);
    $('select[name=cbRole2]').change();
    text = gender;
    $('.bootstrap-select .filter-option').text(text);
    $('select[name=cbGender2]').val(gender);
    $('select[name=cbGender2]').change();
    text = country;
    $('.bootstrap-select .filter-option').text(text);
    $('select[name=cbCountries2]').val(country);
    $('select[name=cbCountries2]').change();
    
    
    $('#zip2').val(zipcode)
    $('#region2').val(region)
    $('#state2').val(state)
    $('#city2').val(city)
    $('#neigborhood2').val(neigborhood)
    $('#street2').val(street)
    $('#streetnumber2').val(streetnumber)
    $('#modalUser').modal("show");
}