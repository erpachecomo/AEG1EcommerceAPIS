<%-- 
    Document   : user
    Created on : Nov 21, 2016, 11:55:35 PM
    Author     : Cesar
--%>
<script type="text/javascript" src="js/user.js"></script>
    <%
            if (request.getParameter("apikey") != null) {
                System.out.println(request.getParameter("apikey"));
                session.setAttribute("apikey", request.getParameter("apikey"));

            }
            if (session.getAttribute("apikey") != null) {
                out.write("<input type='hidden' id='apikey' name='apikey' val ='" + session.getAttribute("apikey") + "'>");
            }
            if(session.getAttribute("apikey") == null){
                  response.sendRedirect("/AEEcommerce/login.html");

            }

        %>
<div class="container">
    <div id="modalUser" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h3>Modificar usuario</h3>
                </div>
                <div class="modal-body">
                    <form id="frmEditUser">
                        <div class="row">
                            <div class="col-lg-10 col-md-10 col-sm-10">
                                <!--Wooo-->
                                <input type="hidden" id="userid" name="userid">
                                <input type="hidden" id="companyid" name="companyid">
                                <input type="hidden" id="roleid" name="roleid">
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-user"></i>
                                        </span>
                                        <input class="form-control" id="username2" name="username2" placeholder="Nombre de usuario">
                                    </div>
                                </div>
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" 
                                           for="phone">Telefono</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-phone-alt"></i>
                                        </span>
                                        <input class="form-control" type= "tel" id="phone2" name="phone2" placeholder="Telefono">
                                    </div>
                                </div>
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" 
                                           for="cellphone">Celular</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-phone"></i>
                                        </span>
                                        <input class="form-control" type= "tel" id="cellphone2" name="cellphone2" placeholder="Telefono Celular">
                                    </div>
                                </div>
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" 
                                           for="email">Correo electr�nico</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-envelope"></i>
                                        </span>
                                        <input class="form-control" type= "email" id="email2" name="email2" placeholder="Correo electr�nico">
                                    </div>
                                </div>
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" 
                                           for="photo">Fotograf�a</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-camera"></i>
                                        </span>
                                        <input class="form-control" type= "text" id="photo2" name="photo2" placeholder="Fotograf�a">
                                    </div>
                                </div>
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" for="cbCompany">Compa�ia</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-briefcase"></i>
                                        </span>
                                        <select class="form-control" data-live-search="true" id="cbCompany2" name="cbCompany2">
                                            <!--option value="11">ITT</option-->
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" for="cbRole">Rol</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-user"></i>
                                        </span>
                                        <select class="form-control" data-live-search="true" id="cbRole2" name="cbRole2">

                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" for="cbGender">Genero</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-star"></i>
                                        </span>
                                        <select class="form-control" data-live-search="true" id="cbGender2" name="cbGender2">
                                            <option value="M">Masculino</option>
                                            <option value="F">Femenino</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" 
                                           for="cbCountries">Pa�s</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-globe"></i>
                                        </span>
                                        <select class="form-control" data-live-search="true" name="cbCountries2" id="cbCountries2">
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" 
                                           for="zip">C�digo Postal</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-barcode"></i>
                                        </span>
                                        <input class="form-control" type= "text" id="zip2" name="zip2" placeholder="C�digo Postal">
                                    </div>
                                </div>
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" 
                                           for="region">Regi�n</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-map-marker"></i>
                                        </span>
                                        <input class="form-control" type= "text" id="region2" name="region2" placeholder="Regi�n">
                                    </div>
                                </div>
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" 
                                           for="state">Estado</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-home"></i>
                                        </span>
                                        <input class="form-control" type= "text" id="state2" name="state2" placeholder="Estado">
                                    </div>
                                </div>

                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" 
                                           for="city">Ciudad</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-home"></i>
                                        </span>
                                        <input class="form-control" type= "text" id="city2" name="city2" placeholder="Ciudad">
                                    </div>
                                </div>
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" 
                                           for="neigborhood">Colonia</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-home"></i>
                                        </span>
                                        <input class="form-control" type= "text" id="neigborhood2" name="neigborhood2" placeholder="Colonia o Vecindario">
                                    </div>
                                </div>
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" 
                                           for="street">Calle</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-road"></i>
                                        </span>
                                        <input class="form-control" type= "text" id="street2" name="street2" placeholder="Calle">
                                    </div>
                                </div>
                                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <!--label class="control-label" 
                                           for="streetnumber">N�mero</label-->
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-home"></i>
                                        </span>
                                        <input class="form-control" type= "text" id="streetnumber2" name="streetnumber2" placeholder="Numero de calle">
                                    </div>
                                </div><!--Wooo-->
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" id="btnModificar" class="btn btn-sm btn-primary">Guardar</button>
                </div>

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
        <div class="col col-lg-10 col-md-10 col-sm-10 col-xs-10">
            <form id="frmUsr" method="POST">
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-user"></i>
                        </span>
                        <input class="form-control" id="username" name="username" placeholder="Nombre de usuario">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="password">Contrase�a</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-lock"></i>
                        </span>
                        <input class="form-control" type= "password" id="password" name="password" placeholder="Contrase�a">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="password2">Confirma la contrase�a</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-lock"></i>
                        </span>
                        <input class="form-control" type= "password" id="password2" name="password2" placeholder="Vuelve a escribir la contrase�a">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="phone">Telefono</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-phone-alt"></i>
                        </span>
                        <input class="form-control" type= "tel" id="phone" name="phone" placeholder="Telefono">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="cellphone">Celular</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-phone"></i>
                        </span>
                        <input class="form-control" type= "tel" id="cellphone" name="cellphone" placeholder="Telefono Celular">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="email">Correo electr�nico</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-envelope"></i>
                        </span>
                        <input class="form-control" type= "email" id="email" name="email" placeholder="Correo electr�nico">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="photo">Fotograf�a</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-camera"></i>
                        </span>
                        <input class="form-control" type= "text" id="photo" name="photo" placeholder="Fotograf�a">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" for="cbCompany">Compa�ia</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-briefcase"></i>
                        </span>
                        <select class="form-control" data-live-search="true" id="cbCompany" name="cbCompany">
                            <!--option value="11">ITT</option-->
                        </select>
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" for="cbRole">Rol</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-user"></i>
                        </span>
                        <select class="form-control" data-live-search="true" id="cbRole" name="cbRole">

                        </select>
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" for="cbGender">Genero</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-star"></i>
                        </span>
                        <select class="form-control" data-live-search="true" id="cbGender" name="cbGender">
                            <option value="m">Masculino</option>
                            <option value="f">Femenino</option>
                        </select>
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="cbCountries">Pa�s</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-globe"></i>
                        </span>
                        <select class="form-control" data-live-search="true" name="cbCountries" id="cbCountries">
                        </select>
                    </div>
                </div>

                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="zip">C�digo Postal</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-barcode"></i>
                        </span>
                        <input class="form-control" type= "text" id="zip" name="zip" placeholder="C�digo Postal">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="region">Regi�n</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-map-marker"></i>
                        </span>
                        <input class="form-control" type= "text" id="region" name="region" placeholder="Regi�n">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="state">Estado</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-home"></i>
                        </span>
                        <input class="form-control" type= "text" id="state" name="state" placeholder="Estado">
                    </div>
                </div>

                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="city">Ciudad</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-home"></i>
                        </span>
                        <input class="form-control" type= "text" id="city" name="city" placeholder="Ciudad">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="neigborhood">Colonia</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-home"></i>
                        </span>
                        <input class="form-control" type= "text" id="neigborhood" name="neigborhood" placeholder="Colonia o Vecindario">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="street">Calle</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-road"></i>
                        </span>
                        <input class="form-control" type= "text" id="street" name="street" placeholder="Calle">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <!--label class="control-label" 
                           for="streetnumber">N�mero</label-->
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-home"></i>
                        </span>
                        <input class="form-control" type= "text" id="streetnumber" name="streetnumber" placeholder="Numero de calle">
                    </div>
                </div>
                <div align = "right">
                    <button type="submit" 
                            class="btn btn-primary btn-md">
                        <i class="glyphicon glyphicon-save"></i>
                        Guardar</button> 
                </div>
            </form>
        </div>
    </div>
    <div class="row"><hr></div>
    <div class="row">
        <div class="col col-lg-10 col-md-10 col-sm-10 col-xs-10">
            <table id="tbUsers">
                <thead>
                    <tr>
                        <th>Clave</th>
                        <th>Nombre de usuario</th>
                        <th>Telefono</th>
                        <th>Telefono celular</th>
                        <th>Fotograf�a</th>
                        <th>Correo electr�nico</th>
                        <th>Compa��a</th>
                        <th>Rol</th>
                        <th>Genero</th>
                        <th>Pa�s</th>
                        <th>C�digo postal</th>
                        <th>Regi�n</th>
                        <th>Estado</th>
                        <th>Ciudad</th>
                        <th>Colonia</th>
                        <th>Calle</th>
                        <th>Numero de calle</th>
                        <th>Operaciones</th>

                    </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
