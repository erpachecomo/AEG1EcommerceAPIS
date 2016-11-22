<%-- 
    Document   : cerrar
    Created on : Nov 22, 2016, 12:14:46 AM
    Author     : Cesar
--%>

<%
    session.setAttribute("apikey", null);
    
    if (request.getParameter("apikey") != null) {
        System.out.println(request.getParameter("apikey"));
        session.setAttribute("apikey", request.getParameter("apikey"));

    }
    if (session.getAttribute("apikey") != null) {
        out.write("<input type='hidden' id='apikey' name='apikey' val ='" + session.getAttribute("apikey") + "'>");
    }
    if (session.getAttribute("apikey") == null) {
        response.sendRedirect("/AEEcommerce/login.html");

    }
%>