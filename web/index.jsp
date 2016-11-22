<%-- 
    Document   : newjsp
    Created on : Nov 21, 2016, 10:33:29 PM
    Author     : Cesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% boolean in = false;%>

<!DOCTYPE html>

<html>
    <head>
        <title>AE Comercio Electrónico</title>
        <meta charset="UTF-8">                                              
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <script type="text/javascript" src="js/plugins/jquery-3.1.0.min.js"></script>
        <script type="text/javascript" src="js/plugins/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.growl.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src ="js/plugins/dataTables.responsive.min.js"></script>
        <script type="text/javascript" src ="js/plugins/bootstrap-select.min.js"></script>
        <script type="text/javascript" src="js/plugins/sweetalert.min.js"></script>       
        <script type="text/javascript" src="js/plugins/accounting.min.js"></script>
        <script type="text/javascript" src="js/app.js"></script>


        <link rel="stylesheet" type="text/css" href="css/sweetalert.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-select.min.css">
        <link rel="stylesheet" href="css/jquery.growl.css">
        <link rel="stylesheet" href="css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="css/responsive.dataTables.min.css">
    </head>
    <body>
        
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
       <!-- <p align="right">  <a href="cerrar.jsp" id="cs">Cerrar Sesion</a><p>-->
        <div class="row">&nbsp;</div>
        <div class="container">
            <div id="nav">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="principal.jsp">
                            Inicio
                        </a>
                    </li>
                    <li>
                        <a href="mRoles.jsp">Roles</a>
                    </li>
                    <li>
                        <a href="user.jsp">Usuarios</a>
                    </li>
                    <li>
                        <a href="category.jsp">Categorías</a>
                    </li>
                    <li>
                        <a href="company.jsp">Compañías</a>
                    </li>
                    <li>
                        <a href="product.jsp">Productos</a>
                    </li>
                    <li>
                        <a href="catalog.jsp">Catalogo</a>
                    </li>
                    <li >
                        <a href="cerrar.jsp" id="cs">Cerrar Sesion</a>
                    </li>
                </ul>
            </div>
            <div class="row">&nbsp;</div>
            <div id="content">
                <div align="center">
                    <img src="img/jaguar.jpg" alt="Jaguar">
                </div>
            </div>
        </div>
        
    </body>
</html>
