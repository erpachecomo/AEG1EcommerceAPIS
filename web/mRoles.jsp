<%-- 
    Document   : mRoles
    Created on : Nov 21, 2016, 11:49:37 PM
    Author     : Cesar
--%>

<script type="text/javascript" src="js/Role.js"></script>

<link rel="stylesheet" href="css/bootstrap-select.min.css">
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
                                <h3>Modificar Rol</h3>
                            </div>
                            <div class="modal-body">
                                <form id="frmEditRole">
                                    <div class="row">
                                        <div class="col-lg-10 col-md-10 col-sm-10">
                                            <div class="form-group">
                                                <label class="control-label" for="rolename2">Rol</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-user"></i>
                                                    </span>
                                                    <input class="form-control" id="rolename2" name="rolename2" placeholder="Nombre del rol">
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
                    <form id="frmRole" method="POST">
                        <div class="form-group">
                            <label class="control-label" 
                                   for="rolename">Rol</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" id="rolename" name="rolename" placeholder="Nombre del rol">
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
                <div class="col col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <table id="tbRoles">
                        <thead>
                            <tr>
                                <th>Clave</th>
                                <th>Nombre de rol</th>
                                <th>Operaciones</th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
