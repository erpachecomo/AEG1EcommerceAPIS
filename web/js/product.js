$(function () {
    $('#tbProducts').DataTable({
        responsive: true,
        language: {
            url: "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
        },
        ajax: {
            url: "GetProducts", 
            dataSrc: function (json) {
                return $.parseJSON(json["msg"]);
            }
        },
        columns: [
            {
                data: function (row) {
                    str = " <div align='right'>";
                    str += row['productid'];
                    str += "</div>";
                    return str;
                }
            }, //clave
            {
                data: "code"
            },
            {
                data: "productname"
            },
            {
                data: "brand"
            },
            {
                data: "stock"
            },
            {
                data: "reorderpoint"
            },
            {
                data: "currency"
            },
            {
                data: function (row) {
                    return row['categoryid']['categoryname'];
                }
            },
            {
                data: "purchprice"
            },
            {
                data: "salepricemay"
            },
            {
                data: "salepricemin"
            },
            {
                data: function (row) {
                    str = "<div align = 'center'>";
                    str += "<button id='btnBorrar' class='btn btn-danger btn-xs' onclick='deleteProduct(" + row['productid'] + ")'><i class='glyphicon glyphicon-trash'></i></button>";
                    str += "&nbsp;<button id='btnEditar' class='btn btn-success btn-xs' onclick='showProduct(" +
                            row['productid'] + "," +
                            "\"" + row['code'] + "\"," +
                            "\"" + row['productname'] + "\"," +
                            "\"" + row['brand'] + "\"," +
                            "\"" + row['stock'] + "\"," +
                            "\"" + row['reorderpoint'] + "\"," +
                            "\"" + row['currency'] + "\"," +
                            row['categoryid']['categoryid'] + "," +
                            "\"" + row['purchprice'] + "\"," +
                            "\"" + row['salepricemay'] + "\"," +
                            "\"" + row['salepricemin'] + "\"" +
                            ")'><i class='glyphicon glyphicon-edit'></i></button>";
                    str += "<div>";
                    return str;
                }
            }
        ]
    });
    $('#btnModificar').on('click', function () {
        $('#frmEditProduct').submit();
    });
    $.ajax({
        url: 'data/currencies.json',
        type: 'GET',
        dataType: 'json'
    }).done(function (json) {

        $.each(json, function (i, currency) {
            $('<option></option>', {text: currency.name + ' (' + currency.symbol + ')'}).attr('value', currency.cc).appendTo('#currency');
            $('<option></option>', {text: currency.name + ' (' + currency.symbol + ')'}).attr('value', currency.cc).appendTo('#currency2');
        });
    });
    $.ajax({
        url: 'GetCategory',
        type: 'GET',
        dataType: 'json'
    }).done(function (json) {
        if (json.code === 200)
            $.each($.parseJSON(json.msg), function (i, row) {
                $('<option></option>', {text: row.categoryname}).attr('value', row.categoryid).appendTo('#categoryid');
                $('<option></option>', {text: row.categoryname}).attr('value', row.categoryid).appendTo('#categoryid2');
            });

    });
    $('#frmProduct').validate({
        rules: {
            code: {
                minlength: 3,
                maxlength: 20,
                required: true
            },
            productname: {
                minlength: 6,
                maxlength: 20,
                required: true
            },
            brand: {
                maxlength: 20,
                required: true
            },
            stock: {
                required: true
            },
            reorderpoint: {
                required: true
            },
            currency: {
                required: true
            },
            categoryid: {
                required: true
            },
            purchprice: {
                required: true
            },
            salepricemin: {
                required: true
            },
            salepricemay: {
                required: true
            },
            foto:{
                required: true,
                accept: "jpg,png,jpeg"
            }
        },
        messages: {
            code: {
                minlength: "Introduzca al menos 3 caracteres",
                maxlength: "Introduzca máximo 20 caracteres",
                required: "Capture el código de producto"

            },
            productname: {
                minlength: "Introduzca al menos 6 caracteres",
                maxlength: "Introduzca máximo 20 caracteres",
                required: "Capture el nombre de producto"
            },
            brand: {
                
                maxlength: "Introduzca máximo 20 caracteres",
                required: "Introduce la marca del producto"
            },
            stock: {
                required: "Introduce el Stock"
            },
            reorderpoint: {
                required: "Introduce el punto de reorden"
            },
            currency: {
                required: "Selecciona una moneda"
            },
            categoryid: {
                required: "Selecciona una categoria"
            },
            purchprice: {
                required: "Introduce un precio de compra"
            },
            salepricemay: {
                required: "Introduce un precio de mayoreo"
            },
            salepricemin: {
                required: "Introduce un precio de menudeo"
            },
            foto:{
                required: "Introduce una imagen",
                accept: "La imagen debe ser tipo PNG o JPG"
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
            newProduct();
            return false;
        }

    });
    $('#frmEditProduct').validate({
        rules: {
            code2: {
                minlength: 3,
                maxlength: 20,
                required: true
            },
            productname2: {
                minlength: 6,
                maxlength: 20,
                required: true
            },
            brand2: {
                maxlength: 20,
                required: true
            },
            stock2: {
                required: true
            },
            reorderpoint2: {
                required: true
            },
            currency2: {
                required: true
            },
            categoryid2: {
                required: true
            },
            purchprice2: {
                required: true
            },
            salepricemin2: {
                required: true
            },
            salepricemay2: {
                required: true
            },
            foto2:{
                required: true,
                accept: "jpg,png,jpeg"
            }

        },
        messages: {
            code2: {
                minlength: "Introduzca al menos 3 caracteres",
                maxlength: "Introduzca máximo 20 caracteres",
                required: "Capture el código de producto"

            },
            productname2: {
                minlength: "Introduzca al menos 6 caracteres",
                maxlength: "Introduzca máximo 20 caracteres",
                required: "Capture el nombre de producto"
            },
            brand2: {
                maxlength: "Introduzca máximo 20 caracteres",
                required: "Introduce la marca del producto"
            },
            stock2: {
                required: "Introduce el Stock"
            },
            reorderpoint2: {
                required: "Introduce el punto de reorden"
            },
            currency2: {
                required: "Selecciona una moneda"
            },
            categoryid2: {
                required: "Selecciona una categoria"
            },
            purchprice2: {
                required: "Introduce un precio de compra"
            },
            salepricemay2: {
                required: "Introduce un precio de mayoreo"
            },
            salepricemin2: {
                required: "Introduce un precio de menudeo"
            },
            foto2:{
                required: "Introduce una imagen",
                accept: "La imagen debe ser tipo PNG o JPG"
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
            updateProduct();
            return false;
        }

    });



});

function updateProduct() {
    swal(
            {
                title: "¿Estas seguro que deseas actualizar este registro?", text: "",
                type: "warning", showCancelButton: true,
                confirmButtonColor: "#DD6B55", confirmButtonText: "Aceptar!",
                cancelButtonText: "Cancelar", closeOnConfirm: false,
                closeOnCancel: false
            }, function (isConfirm) {
        if (isConfirm) {
            
            /*var para = {
                "productid": $('#productid').val(),
                "code": $('#code2').val(),
                "productname": $('#productname2').val(),
                "brand": $('#brand2').val(),
                "stock": $('#stock2').val(),
                "reorderpoint": $('#reorderpoint2').val(),
                "currency": $('#currency2').val(),
                "categoryid": $('#categoryid2').val(),
                "purchprice": $('#purchprice2').val(),
                "salepricemay": $('#salepricemay2').val(),
                "salepricemin": $('#salepricemin2').val(),
                "foto":$("#foto2").val()
            };*/
            var data = new FormData($('#frmEditProduct')[0]);
            ///Comienza a Borrar    
            $.ajax({
                url: "UpdateProduct",
                type: "post",
                /*Manda todo el formulario
                 * como mandar parametros por separado en data:
                 */
                data: data,
                contentType:false,
                processData:false

            }).done(
                    function (data) {
                        // alert("Se realizo correctamente"+data.code); 
                        if (data.code === 200) {
                            $.growl.notice({message: data.msg + " " + data.details});
                            swal("Actualizado!", "El registro se Actualizo correctamente", "success");
                            $('#tbProducts').dataTable().api().ajax.reload();
                            $('#modalProduct').modal("hide");
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

function newProduct() {
    var data = new FormData($('#frmProduct')[0]);
    console.log(data);
    $.ajax(
            {
                url: "NewProduct",
                type: "post",
                data: data,
                contentType:false,
                processData:false/*{
                    /*"code": $('#code').val(),
                    "productname": $('#productname').val(),
                    "brand": $('#brand').val(),
                    "stock": $('#stock').val(),
                    "reorderpoint": $('#reorderpoint').val(),
                    "currency": $('#currency').val(),
                    "categoryid": $('#categoryid').val(),
                    "purchprice": $('#purchprice').val(),
                    "salepricemay": $('#salepricemay').val(),
                    "salepricemin": $('#salepricemin').val(),
                    "photo": $('#foto').val()
                    
                }*/
            }
    ).done(
            function (data) {
                //alert("Se realizó correctamente "+data.code);            
                if (data.code == 200) {

                    $.growl.notice({message: data.msg});
                    $('#tbProducts').dataTable().api().ajax.reload();
                    cleanFields();
                } else {
                    $.growl.error({message: data.msg});
                    //alert($('#purchprice').val());
                }
            }
    ).fail(
            function () {
                $.growl.error({message: "El servidor no está disponible 😞"});
            }
    );
}

function deleteProduct(productid) {
    swal(
            {
                title: "¿Estas seguro que deseas eliminar este registro?", text: "",
                type: "warning", showCancelButton: true,
                confirmButtonColor: "#DD6B55", confirmButtonText: "Aceptar!",
                cancelButtonText: "Cancelar", closeOnConfirm: false,
                closeOnCancel: false
            }, function (isConfirm) {
        if (isConfirm) {

            var para = {
                "productid": productid
            };
            ///Comienza a Borrar    
            $.ajax({
                url: "DeleteProduct",
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
                            swal("Eliminado!", "El registro se elimino correctamente", "success");
                            $('#tbProducts').dataTable().api().ajax.reload();
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


function cleanFields() {
    $('#productname').val('');
    $('#code').val('');
    $('#brand').val('');

}

function showProduct(productid, code, productname, brand, stock, reorderpoint, currency, categoryid, purchprice, salepricemay, salepricemin) {
    $('#productid').val(productid);
    $('#code2').val(code);
    $('#productname2').val(productname);
    $('#brand2').val(brand);
    $('#stock2').val(stock);
    $('#reorderpoint2').val(reorderpoint);
    $('#currency2').val(currency);
    $('#categoryid2').val(categoryid);
    $('#purchprice2').val(purchprice);
    $('#salepricemay2').val(salepricemay);
    $('#salepricemin2').val(salepricemin);
    
    var text = currency;
    $('.bootstrap-select .filter-option').text(text);
    $('select[name=currency2]').val(currency);
    $('select[name=currency2]').change();
    text = categoryid;
    $('.bootstrap-select .filter-option').text(text);
    $('select[name=categoryid2]').val(categoryid);
    $('select[name=categoryid2]').change();
    $('#modalProduct').modal("show");
}