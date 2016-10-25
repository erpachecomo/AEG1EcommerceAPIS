
    $( function ()
    {
        $( '#frmCategory' ).validate( {
            rules: {
                categoryname: {
                    minlength: 2 ,
                    maxlength: 20 ,
                    required: true
                }
            } ,
            messages: {
                categoryname: {
                    minlength: "Introduzca al menos 2 caracteres" ,
                    maxlength: "Introduzca maximo 20 caracteres" ,
                    required: "Capture el nombre de la categoría"
                }
            } ,
            highlight: function ( element )
            {
                $( element ).closest( '.form-group' ).addClass( 'has-error' );
            } ,
            unhighlight: function ( element )
            {
                $( element ).closest( '.form-group' ).removeClass( 'has-error' );
            } ,
            errorElement: 'span' ,
            errorClass: 'help-block' ,
            errorPlacement: function ( error , element )
            {
                if ( element.parent( '.input-group' ).length )
                {
                    error.insertAfter( element.parent() );
                }
                else
                {
                    error.insertAfter( element );
                }
            } ,
            submitHandler: function ( form )
            {
                newCategory();
                return false;
            }

        } );
        $( '#tbCategory' ).DataTable( {
            languaje: {
                url: "//cdn.datatables.net/plug-ins/1.10.10/i18n/Spanish.json"
            } ,
            ajax: {
                url: "GetCategory" ,
                dataSrc: function ( json )
                {
                    //console.log("Resultado:"+json['msg']);
                    return $.parseJSON( json['msg'] );
                }
            } ,
            columns: [
                {
                    data: function ( row )
                    {
                        str = " <div align='right'>";
                        str += accounting.formatMoney( row["categoryid"] );
                        str += "</div>";
                        return str;
                    }
                } ,
                {
                    data: "categoryname"
                } ,
                {
                    data: function ( row )
                    {
                        //console.log(row);
                        str = " <div align='center'>"
                        str += "<button id='btnBorrar' class='btn btn-danger btn-xs' onClick='deleteCategory(" + row['categoryid'] + ")'><i class='glyphicon glyphicon-trash'></i> Eliminar </button>";
                        str += "&nbsp;<button id='btnEditar' class = 'btn btn-success btn-xs' onClick = 'showCategory(" + row['categoryid'] + ",\"" + row['categoryname'] + "\")'><i class='glyphicon glyphicon-edit'></i> Modificar </button>";
                        str += "</div>"
                        return str;

                    }

                } ]
        } );
        
        $( '#frmEditCategory' ).validate( {
            rules: {
                categoryname2: {
                    minlength: 3 ,
                    maxlength: 20 ,
                    required: true
                }
            } ,
            messages: {
                categoryname2: {
                    minlength: "Introduzca al menos 2 caracteres" ,
                    maxlength: "Introduzca maximo 20 caracteres" ,
                    required: "Capture el nombre de la categoria"
                }
            } ,
            highlight: function ( element )
            {
                $( element ).closest( '.form-group' ).addClass( 'has-error' );
            } ,
            unhighlight: function ( element )
            {
                $( element ).closest( '.form-group' ).removeClass( 'has-error' );
            } ,
            errorElement: 'span' ,
            errorClass: 'help-block' ,
            errorPlacement: function ( error , element )
            {
                if ( element.parent( '.input-group' ).length )
                {
                    error.insertAfter( element.parent() );
                }
                else
                {
                    error.insertAfter( element );
                }
            } ,
            submitHandler: function ( form )
            {
                updateCategory();
                return false;
            }

        } );
        $( '#btnModificar' ).on( 'click' , function ()
        {
            $( '#frmEditCategory' ).submit();
        } );
        $.ajax( {
            url: 'GetCategory' ,
            type: 'GET' ,
            dataType: 'json'
        } ).done( function ( json )
        {
            if ( json.code === 200 )
                $('#tbCategory').dataTable().api().ajax.reload();
        } );
    } );

    function newCategory()
    {
        $.ajax( {
            url: "NewCategory" ,
            type: "post" ,
            /*Manda todo el formulario
             * como mandar parametros por separado en data:
             */
            data: $( '#frmCategory' ).serialize()
        } ).done(
          function ( data )
          {
              // alert("Se realizo correctamente"+data.code);
              if ( data.code === 200 )
              {

                  $.growl.notice( { message: data.msg + " " + data.details } );
                  $( '#tbCategory' ).dataTable().api().ajax.reload();
                  $( '#categoryname' ).val( "" );
              }
              else
                  $.growl.error( { message: data.msg + "" + data.details } );
          }
        ).fail(
          function ()
          {
              $.growl.error( { message: "Algo va mal no se encuentra el servidor" } )
          }
        );
    }

    function deleteCategory( categoryid )
    {

        swal(
          {
            title: "¿Estas seguro que deseas eliminar este registro?" , text: "accounting plugin 42123123: " + accounting.formatMoney( 42123123 ) ,
              type: "warning" , showCancelButton: true ,
              confirmButtonColor: "#DD6B55" , confirmButtonText: "Aceptar!" ,
              cancelButtonText: "Cancelar" , closeOnConfirm: false ,
              closeOnCancel: false
          } , function ( isConfirm )
        {
            if ( isConfirm )
            {

                var para = {
                    "categoryid": categoryid
                };
                ///Comienza a Borrar
                $.ajax( {
                    url: "DeleteCategory" ,
                    type: "post" ,
                    /*Manda todo el formulario
                     * como mandar parametros por separado en data:
                     */
                    data: para

                } ).done(
                  function ( data )
                  {
                      // alert("Se realizo correctamente"+data.code);
                      if ( data.code === 200 )
                      {              
                         $.growl.notice( { message: data.msg + " " + data.details } );
                          swal( "Eliminado!" , "El registro se elimino correctamente" , "success" );
                          $('#tbCategory').dataTable().api().ajax.reload();
                          $( '#categoryname' ).val( "" );
                      }
                      else{
                          $.growl.error( { message: data.msg + "" + data.details } );
                      }
                  }
                ).fail(
                  function ()
                  {
                      $.growl.error( { message: "Algo va mal no se encuentra el servidor" } )
                  }
                );
            }
            else
            {
                swal( "Cancelado" , "Accion Cancelada" , "error" );
            }
        } );

    }

    function showCategory( categoryid , categoryname )
    {
        $( '#categoryid' ).val( categoryid );
        $( '#categoryname2' ).val( categoryname );
        $( '#modalCategory' ).modal( "show" );
    }


    function updateCategory()
    {
        swal(
          {
              title: "¿Estas seguro que deseas actualizar este registro?" , text: "" ,
              type: "warning" , showCancelButton: true ,
              confirmButtonColor: "#DD6B55" , confirmButtonText: "Aceptar!" ,
              cancelButtonText: "Cancelar" , closeOnConfirm: false ,
              closeOnCancel: false
          } , function ( isConfirm )
        {
            if ( isConfirm )
            {

                var para = {
                    "categoryid": $( '#categoryid' ).val() ,
                    "categoryname": $( '#categoryname2' ).val()

                };
                ///Comienza a Borrar
                $.ajax( {
                    url: "UpdateCategory" ,
                    type: "post" ,
                    /*Manda todo el formulario
                     * como mandar parametros por separado en data:
                     */
                    data: para

                } ).done(
                  function ( data )
                  {
                      // alert("Se realizo correctamente"+data.code);
                      if ( data.code === 200 )
                      {
                          $.growl.notice( { message: data.msg + " " + data.details } );
                          swal( "Actualizado!" , "El registro se Actualizo correctamente" , "success" );
                          $( '#tbCategory' ).dataTable().api().ajax.reload();
                          $( '#modalCategory' ).modal( "hide" );
                      }
                      else
                          $.growl.error( { message: data.msg + "" + data.details } );
                  }
                ).fail(
                  function ()
                  {
                      $.growl.error( { message: "Algo va mal no se encuentra el servidor" } )
                  }
                );
            }
            else
            {
                swal( "Cancelado" , "Accion Cancelada" , "error" );
            }
        } );

    }


