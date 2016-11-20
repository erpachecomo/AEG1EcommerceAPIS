/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.xml.ws.runtime.dev.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.ittepic.ecommerce.ejbs.CartBeanRemote;
import mx.edu.ittepic.ecommerce.entities.Users;
import mx.edu.ittepic.ecommerce.utils.Message;

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
        processRequest(request, response);
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
        response.setHeader("Cache-Control", "no-store");
        response.setContentType("application/json;charset=UTF-8");
        CartBeanRemote cart;
        cart = (CartBeanRemote) request.getSession().getAttribute("ejbsession");
        
        PrintWriter o = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Message m;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        
        if(cart==null){
            try {
                //Necesarias para instanciar un ejb desde jndi
                InitialContext ic = new InitialContext();
                cart = (CartBeanRemote) ic.lookup("java:comp/env/ejb/CartBean");
                request.getSession().setAttribute("ejbsession",cart);
                m = gson.fromJson(cart.login(username, password), Message.class);
                //System.out.println(m.getCode()+" blablabla "+m.getMsg());
                if(m.getCode()==200){
                    request.getSession().setAttribute("ejbsession", cart);
                    //response.sendRedirect("index.html");
                }
                o.print(gson.toJson(m));
                
            } catch (NamingException ex) {
                Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            response.sendRedirect("login.html");
            
        }
        
        
        
        
        
        
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

