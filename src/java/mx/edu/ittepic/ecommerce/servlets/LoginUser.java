/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.servlets;

import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.ittepic.ecommerce.ejb.EJBecommerceStatefulRemote;
import mx.edu.ittepic.ecommerce.utils.Message;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author ernesto
 */
@WebServlet(name = "LoginUser", urlPatterns = {"/LoginUser"})
public class LoginUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        EJBecommerceStatefulRemote ejb = (EJBecommerceStatefulRemote) request.getSession().getAttribute("ejbsession");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordMD5=DigestUtils.md5Hex(password); 
        if(ejb ==null){
            InitialContext ic;
            try{
                ic = new InitialContext();
                ejb = (EJBecommerceStatefulRemote) ic.lookup("java:comp/env/ejb/EJBecommerceStateful"); 
                

                 Message m = new GsonBuilder().create().fromJson(ejb.login(username, passwordMD5), Message.class);

                 
                 if(m.getCode()==200){
                     request.getSession().setAttribute("ejbsession", ejb);
                 }
                 out.print(new GsonBuilder().create().toJson(m));
            } catch (NamingException ex) {
                out.print(ex.toString());
            }
        }else{
            response.sendRedirect("index.html");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

