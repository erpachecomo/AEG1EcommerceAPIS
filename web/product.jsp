<%-- 
    Document   : product
    Created on : Nov 21, 2016, 11:51:55 PM
    Author     : Cesar
--%>

<script type="text/javascript" src="js/product.js"></script>
<script src="js/plugins/fileinput.min.js" type="text/javascript"></script>
<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>
<link href="css/fileinput.css" media="all" rel="stylesheet" type="text/css" />

<div class="container">
    <div id="modalProduct" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h3>Modificar producto</h3>
                </div>
                <div class="modal-body">
                    <form id="frmEditProduct" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-lg-1 col-md-1 col-sm-1"></div>
                            <div class="col-lg-10 col-md-10 col-sm-10">
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-barcode"></i>
                                        </span>
                                        <input class="form-control" id="code2" name="code2" placeholder="C�digo de producto">
                                        <input type="hidden" id="productid" name="productid">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-lamp"></i>
                                        </span>
                                        <input class="form-control" type= "text" id="productname2" name="productname2" placeholder="Nombre del producto">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-tag"></i>
                                        </span>
                                        <input class="form-control" type= "text" id="brand2" name="brand2" placeholder="Marca">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-inbox"></i>
                                        </span>
                                        <input class="form-control" type= "number" id="stock2" name="stock2" placeholder="Stock">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-sort"></i>
                                        </span>
                                        <input class="form-control" type= "number" id="reorderpoint2" name="reorderpoint2" placeholder="Punto de reorden">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-star"></i>
                                        </span>
                                        <select class="form-control" data-live-search="true" id="categoryid2" name="categoryid2">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-euro"></i>
                                        </span>
                                        <select class="form-control" data-live-search="true" id="currency2" name="currency2">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-usd"></i>
                                        </span>
                                        <input class="form-control" id="purchprice2" name="purchprice2" placeholder="Precio de compra">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-credit-card"></i>
                                        </span>
                                        <input class="form-control" id="salepricemay2" name="salepricemay2" placeholder="Precio de venta (Mayoreo)">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-credit-card"></i>
                                        </span>
                                        <input class="form-control" id="salepricemin2" name="salepricemin2" placeholder="Precio de venta (Menudeo)">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-camera"></i>
                                        </span>
                                        <input id="foto2" name="foto2" type="file" class="form-control" multiple=false data-preview-file-type="any"> 
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" id="btnModificar" class="btn btn-sm btn-primary">Guardar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
        <div class="col col-lg-10 col-md-10 col-sm-10 col-xs-10">
            <form id="frmProduct" method="POST" enctype="multipart/form-data">
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-barcode"></i>
                        </span>
                        <input class="form-control" id="code" name="code" placeholder="C�digo de producto">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-lamp"></i>
                        </span>
                        <input class="form-control" type= "text" id="productname" name="productname" placeholder="Nombre del producto">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-tag"></i>
                        </span>
                        <input class="form-control" type= "text" id="brand" name="brand" placeholder="Marca">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-inbox"></i>
                        </span>
                        <input class="form-control" type= "number" id="stock" name="stock" placeholder="Stock">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-sort"></i>
                        </span>
                        <input class="form-control" type= "number" id="reorderpoint" name="reorderpoint" placeholder="Punto de reorden">
                    </div>
                </div>

                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-star"></i>
                        </span>
                        <select class="form-control" data-live-search="true" id="categoryid" name="categoryid">
                        </select>
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-euro"></i>
                        </span>
                        <select class="form-control" data-live-search="true" id="currency" name="currency">
                        </select>
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-usd"></i>
                        </span>
                        <input class="form-control" id="purchprice" name="purchprice" placeholder="Precio de compra">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-credit-card"></i>
                        </span>
                        <input class="form-control" id="salepricemay" name="salepricemay" placeholder="Precio de venta (Mayoreo)">
                    </div>
                </div>
                <div class="form-group col col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-credit-card"></i>
                        </span>
                        <input class="form-control" id="salepricemin" name="salepricemin" placeholder="Precio de venta (Menudeo)">
                    </div>
                </div>
                <div class="form-group col col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-camera"></i>
                        </span>
                        <input id="foto" name="foto" type="file" class="form-control" multiple=false data-preview-file-type="any"> 
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
            <table id="tbProducts">
                <thead>
                    <tr>
                        <th>Clave</th>
                        <th>C�digo</th>
                        <th>Producto</th>
                        <th>Marca</th>
                        <th>Stock</th>
                        <th>Punto de reorden</th>
                        <th>Moneda</th>
                        <th>Categor�a</th>
                        <th>Precio de compra</th>
                        <th>Precio de venta (Mayoreo)</th>
                        <th>Precio de venta (Menudeo)</th>
                        <th>Operaciones</th>

                    </tr>
                </thead>
            </table>
        </div>
    </div>
</div>