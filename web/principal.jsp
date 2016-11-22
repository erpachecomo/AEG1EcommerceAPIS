<%-- 
    Document   : principal
    Created on : Nov 21, 2016, 11:50:50 PM
    Author     : Cesar
--%>

<script type="text/javascript" src="js/getURL.js"></script>
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
<div align="center">
    <img src="img/jaguar.jpg" alt="Jaguar">
</div>
