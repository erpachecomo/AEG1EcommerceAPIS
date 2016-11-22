<%-- 
    Document   : category
    Created on : Nov 21, 2016, 11:38:26 PM
    Author     : Cesar
--%>

<script type="text/javascript" src="js/category.js"></script>
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
<div id="modalCategory" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3>Modificar Categoría</h3>
            </div>
            
            <div class="modal-body">
                <form id="frmEditCategory">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
                        <div class="form-group">
                            <label class="control-label" for="categoryname2">Categorías</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input  type="hidden" class="form-control" id="categoryid" name="categoryid">
                                <input class="form-control" id="categoryname2" name="categoryname2" placeholder="Nombre categoria">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnModificar" type="button" class="btn btn-sm btn-primary ">Guardar</button>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col col-lg-12 col-md-12 col-sm-12 col-xs-12" >
        <form id="frmCategory" method="POST">
            <div class="form-group" >
                <label class="control-label" for="categoryname">Categoría</label>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="glyphicon glyphicon-user"></i>
                    </span>
                    <input class="form-control" id="categoryname" name="categoryname" placeholder="Nombre de categoría">
                </div>
                
                <!--Ejemplo Quitar despues-->
                <div align="right">
                    <button type="submit" class="btn btn-primary btn-ls">
                        <i class="glyphicon glyphicon-save"></i>
                        Guardar
                    </button>
                </div>
        </form>
    </div>
</div>
<div class="row"><hr></div>
<div class="row">
    <div class="col col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <table id="tbCategory">
            <thead>
                <tr>
                    <th>Clave</th>
                    <th>Categoría</th>
                    <th>Operaciones</th>
                </tr>
            </thead>
        </table>
    </div>
</div>
</div>
    