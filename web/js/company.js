$(function () {
    $('#frmCompany').validate({
        rules: {
            companyname: {
                maxlength: 40,
                required: true
            },
            companyneighborhood: {
                maxlength: 50,
                required: true
            },
            companyzipcode: {
                maxlength: 10,
                number: true,
                required: true
            },
            companycity: {
                maxlength: 50,
                required: true
            },
            companycountry: {
                required: true

            },
            companystate: {
                maxlength: 50,
                required: true
            },
            companyregion: {
                maxlength: 50,
                required: true
            },
            companystreet: {
                maxlength: 50,
                required: true
            },
            companystreetnumber: {
                maxlength: 7,
                number: true,
                required: true
            },
            companyphone: {
                maxlength: 15,
                number: true,
                required: true
            },
            companyrfc: {
                maxlength: 13,
                required: true
            },
            companylogo: {
                maxlength: 255,
                required: true
            }
        },
        messages: {
            companyname: {
                maxlength: "Introduzca maximo 40 caracteres",
                required: "Capture el nombre de la categoria"
            },
            companyneighborhood: {
                maxlength: "Introduzca maximo 50 caracteres",
                required: "Capture la colonia de la categoria"
            },
            companyzipcode: {
                maxlength: "Introduzca maximo 10 caracteres",
                number: "Introduzca un numero",
                required: "Capture el codigo postal de la categoria"
            },
            companycity: {
                maxlength: "Introduzca maximo 50 caracteres",
                required: "Capture la ciudad de la categoria"
            },
            companycountry: {
                required: "Elija un pais"

            },
            companystate: {
                maxlength: "Introduzca maximo 50 caracteres",
                required: "Capture el estado de la categoria"
            },
            companyregion: {
                maxlength: "Introduzca maximo 50 caracteres",
                required: "Capture la region de la categoria"
            },
            companystreet: {
                maxlength: "Introduzca maximo 50 caracteres",
                required: "Capture la calle de la categoria"
            },
            companystreetnumber: {
                maxlength: "Introduzca maximo 7 caracteres",
                number: "Introduzca un numero",
                required: "Capture el numero de local de la categoria"
            },
            companyphone: {
                maxlength: "Introduzca maximo 15 caracteres",
                number: "Introduzca un numero",
                required: "Capture el numero de telefono de la categoria"
            },
            companyrfc: {
                maxlength: "Introduzca maximo 13 caracteres",
                required: "Capture el RFC de la categoria"
            },
            companylogo: {
                maxlength: "Introduzca maximo 255 caracteres",
                required: "Inserte un logo"
            }
        },
        highlight: function (element) {
            $(element).closest('.input-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.input-group').removeClass('has-error');
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
            newCompany($('#companyname').val(),$('#companyneighborhood').val(),$('#companyzipcode').val(),$('#companycity').val(),$('#companycountry').val(),$('#companystate').val(),$('#companyregion').val()
                    ,$('#companystreet').val(),$('#companystreetnumber').val(),$('#companyphone').val(),$('#companyrfc').val(),$('#companylogo').val());
            return false;
        }
    });
    $('#tbCompany').DataTable({
        ajax: {
            url: 'GetCompanyAll',
            type:'GET',
            dataSrc: function (json) {
                console.log(json.msg);
                return $.parseJSON(json.msg);
            }, 
        },
            responsive: true,
        columns: [
            {data: 'companyid'
            },
            {data: 'companyname'
            },
            {data: 'neighborhood'
            },
            {data: 'zipcode'
            },
            {data: 'city'
            },
            {data: 'country'
            },
            {data: 'state'
            },
            {data: 'region'
            },
            {data: 'street'
            },
            {data: 'streetnumber'
            },
            {data: 'phone'
            },
            {data: 'rfc'
            },
            {data: 'logo'
            },
            {data: function (row) {
                    
                    //console.log(row);
                        str = " <div align='center'>"
                        str += "<button id='btnEliminarCompany' class='btn btn-danger btn-xs' onClick='confirmar(" + row['companyid'] + ")'><i class='glyphicon glyphicon-trash'></i> Eliminar </button>";
                        str += "&nbsp;<button id='btnEditarCompany' class = 'btn btn-success btn-xs' onClick='showCompany(" + row['companyid'] + ",\"" + row['companyname']+ "\",\"" + row['rfc']+ "\",\""+row['neighborhood']+"\","+row['zipcode']+",\""+row['city']+"\",\""+row['country']+"\",\""+row['state']+"\",\""+row['region']+"\",\""+row['street']+"\","+row['streetnumber']+","+row['phone']+",\""+row['logo']+"\")'><i class='glyphicon glyphicon-edit'></i> Modificar </button>";
                        str += "</div";
                        return str;
                }
            }
        ]
    });
    $('#frmEditCompany').validate({
        rules: {
            companyname2: {
                maxlength: 40,
                required: true
            },
            companycity2: {
                maxlength: 50,
                required: true
            },
            companycountry2: {
                maxlength: 50,
                required: true
            },
            companyneighborhood2: {
                maxlength: 50,
                required: true
            },
            companyzipcode2: {
                maxlength: 10,
                number:true,
                required: true
            },
            companystate2: {
                maxlength: 50,
                required: true
            },
            companyregion2: {
                maxlength: 50,
                required: true
            },
            companystreet2: {
                maxlength: 50,
                required: true
            },
            companystreetnumber2: {
                maxlength: 7,
                number:true,
                required: true
            },
            companyphone2: {
                maxlength: 15,
                number:true,
                required: true
            },
            companyrfc2: {
                maxlength: 13,
                required: true
            },
            companylogo2: {
                maxlength: 255,
                required: true
            }
        },
        messages: {
            companyname2: {
                maxlength: "Introduzca maximo 40 caracteres",
                required: "Capture el nombre"
            },
            companycity2: {
                maxlength: "Introduzca maximo 50 caracteres",
                required: "Capture la ciudad"
            },
            companycountry2: {
                maxlength: "Introduzca maximo 50 caracteres",
                required: "Capture el pais"
            },
            companyneighborhood2: {
                maxlength: "Introduzca maximo 50 caracteres",
                required: "Capture la colonia"
            },
            companyzipcode2: {
                maxlength: "Introduzca maximo 10 caracteres",
                number:"Inserte solo numeros",
                required: "Capture el codigo postal"
            },
            companystate2: {
                maxlength: "Introduzca maximo 50 caracteres",
                required: "Capture el estado"
            },
            companyregion2: {
                maxlength: "Introduzca maximo 50 caracteres",
                required:"Capture la region"
            },
            companystreet2: {
                maxlength: "Introduzca maximo 50 caracteres",
                required: "Capture la calle"
            },
            companystreetnumber2: {
                maxlength: "Introduzca maximo 7 caracteres",
                number:"Inserte solo numeros",
                required: "Capture el numero de la calle"
            },
            companyphone2: {
                maxlength: "Introduzca maximo 15 caracteres",
                number:"Inserte solo numeros",
                required: "Capture el telefono"
            },
            companyrfc2: {
                maxlength: "Introduzca maximo 13 caracteres",
                required: "Capture el RFC"
            },
            companylogo2: {
                maxlength: "Introduzca maximo 255 caracteres",
                required: "Capture el logo"
            }
        },
        highlight: function (element) {
            $(element).closest('.input-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.input-group').removeClass('has-error');
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
            console.log("ID"+$('#companyid').val());
            updateCompany($('#companyid').val(),$('#companyname2').val(),$('#companycity2').val(),$('#companycountry2').val(),$('#companyneighborhood2').val(),$('#companyzipcodey2').val(),
            $('#companystate2').val(),$('#companyregion2').val(),$('#companystreet2').val(),$('#companystreetnumber2').val(),$('#companyphone2').val(),$('#companyrfc2').val(),$('#companylogo2').val());
            return false;
        }
    });
    $('#btnModificarCompany').on('click', function () {
        $('#frmEditCompany').submit();
    });
    
    $('#companycountry').selectpicker({});
});
function newCompany(nombre, neigh, zipcode, city, country, state, region, street, streetnumber, phone, rfc, logo) {
    //Tres partes en las que se divide la peticion AJAX
    $.ajax({
        url: "NewCompany",
        type: "post",
        data: {
            CompanyName: nombre,
            neighborhood: neigh,
            zipcode: zipcode,
            city: city,
            country: country,
            state: state,
            region: region,
            street: street,
            streetnumber: streetnumber,
            phone: phone,
            rfc: rfc,
            logo: logo
        }
    }).done(function (json) {
        if (json.code === 200) {
            $('#tbCompany').dataTable().api().ajax.reload();
            $('#companycity').val('');
            $('#companylogo').val('');
             $('#companynamey').val('');
             $('#companyneighborhood').val('');
             $('#companyphone').val('');
             $('#companyregion').val('');
             $('#companyrfc').val('');
             $('#companystate').val('');
             $('#companystreet').val('');
             $('#companystreetnumber').val('');
             $('#companyzipcode').val('');
            $.growl.notice({
                message: json.msg
            });
        } else {
            $.growl.error({message: json.msg});
        }
    }).fail();
}
function showCompany(companyid, companyname,rfc,neigh, zipcode, city, country, state, region, street, streetnumber, phone, logo) {
    console.log("Entro showCompany");
    console.log(companyid,companyname,rfc,neigh,zipcode, city, country, state, region, street, streetnumber, phone, logo);
    $('#companyid').val(companyid);
    $('#companyname2').val(companyname);
    $('#companyrfc2').val(rfc);
    $('#companycity2').val(city);
    //$('#companycountry2').val(country);
    $('#companylogo2').val(logo);
    $('#companyneighborhood2').val(neigh);
    $('#companyphone2').val(phone);
    $('#companyregion2').val(region);
    $('#companystate2').val(state);
    $('#companystreet2').val(street);
    $('#companystreetnumber2').val(streetnumber);
    $('#companyzipcodey2').val(zipcode);
    $('#modalCompany').modal('show');
}
function updateCompany(companyid,companyname, city, country,neigh, zipcode, state, region, street, streetnumber, phone, rfc, logo) {
    //Tres partes en las que se divide la peticion AJAX
    console.log('Update '+logo);
    $.ajax({
        url: "UpdateCompany",
        type: "post",
        data: {
            companyid: companyid,
            companyname: companyname,
            city:city,
            country:country,
            neigh:neigh,
            zipcode:zipcode,
            state:state,
            region:region,
            street:street,
            streetnumber:streetnumber,
            phone:phone,
            rfc:rfc,
            logo:logo
        }
    }).done(function (json) {
        console.log(json.code);
        if (json.code === 200) {
            $('#tbCompany').dataTable().api().ajax.reload();
            $('#companyname2').val('');
            $('#companyid').val('');
            $('#modalCompany').modal('hide');
            $.growl.notice({
                message: json.msg
            });

        } else {
            $.growl.error({message: 'No se pudo actualizar :('});
        }
    }).fail();
}
function confirmar(re) {
    bootbox.confirm({
        title: "Borrar Compañia",
        message: "¿Estas seguro de que deseas borrar esta compañia?",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> Cancel'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Confirm'
            }
        },
        callback: function (result) {
            if (result === true) {
                deleteCompany(re);
            }
        }
    });
}
function deleteCompany(companyid) {
    //Tres partes en las que se divide la peticion AJAX
    $.ajax({
        url: "DeleteCompany",
        type: "post",
        data: {
            companyid: companyid
        }
    }).done(function (json) {
        if (json.code === 200) {
            $('#tbCompany').dataTable().api().ajax.reload();
            $.growl.notice({
                message: "Se borro con exito! :)"
            });

        } else {
            $.growl.error({message: "No se pudo borrar :("});
        }
    }).fail();
}