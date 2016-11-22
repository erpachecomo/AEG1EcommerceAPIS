<%-- 
    Document   : mCompany
    Created on : Nov 21, 2016, 11:46:35 PM
    Author     : Cesar
--%>

<script type="text/javascript" src="js/Company.js"></script>
<script type="text/javascript" src="js/getURL.js"></script>
<script type="text/javascript" src="js/plugins/accounting.min.js"></script>

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
<div class ="container">
            <div id="modalRole" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h3>Modificar Company</h3>
                            </div>
                            <div class="modal-body">
                                <form id="frmEditRole">
                                    <div class="row">
                                        <div class="col-lg-6 col-md-6 col-sm-6">
                                            <div class="form-group">
                                                <label class="control-label" for="rolename2">Nombre de la compa�ia</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-user"></i>
                                                    </span>
                                                    <input class="form-control" id="rolename2" name="rolename2" placeholder="Nombre de la compa�ia">
                                                    <input type="hidden" id="roleid" name="roleid">
                                                </div>
                                            </div>
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
                <div class="col col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <form id="frmCompany" method="POST">
                        <div class="form-group">
                            <label class="control-label" for="companyname">Company</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" id="companyname" name="companyname" placeholder="Nombre de la Compa�ia">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="neighborhood">Neighborhood</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-cd"></i>
                                </span>
                                <input class="form-control" id="neighborhood" name="neigborhood" placeholder="Colonia">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="zipcode">Zip Code</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" id="zipcode" name="zipcode" placeholder="Codigo Postal">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="city">City</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" id="city" name="city" placeholder="Ciudad">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="country">Country</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" id="country" name="country" placeholder="Pais">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="state">State</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" id="state" name="state" placeholder="Estado">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="region">Region</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" id="region" name="region" placeholder="Region">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="street">Street</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" id="street" name="street" placeholder="Calle">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="streetnumber">Street Numbre</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" id="streetnumbre" name="streetnumber" placeholder="Numero">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="phone">Phone</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" id="phone" name="phone" placeholder="Telefono">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="rfc">RFC</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" id="rfc" name="rfc" placeholder="RFC">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="logo">Logo</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" id="logo" name="logo" placeholder="URL logo">
                            </div>
                        </div>
                        <div align = "right">
                            <button type="submit" class="btn btn-primary btn-md">
                                <i class="glyphicon glyphicon-save"></i>
                                Guardar</button> 
                        </div>
                    </form>
                </div>
            </div>
            <div class="row"><hr></div>
            <div class="row">
                <div class="col col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <table id="tbCompany">
                        <thead>
                            <tr>
                                <th>Clave</th>
                                <th>Compa�ia</th>
                                <th>Colonia</th>
                                <th>Codigo Postal</th>
                                <th>Ciudad</th>
                                <th>Pais</th>
                                <th>Estado</th>
                                <th>Region</th>
                                <th>Calle</th>
                                <th>Numero</th>
                                <th>Telefono</th>
                                <th>RFC</th>
                                <th>Logo</th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>