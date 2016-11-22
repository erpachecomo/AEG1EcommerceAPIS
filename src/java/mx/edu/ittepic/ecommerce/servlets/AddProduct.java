/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.servlets;

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
import mx.edu.ittepic.ecommerce.ejb.EJBecommerceStatefulRemote;

/**
 *
 * @author miguel
 */
@WebServlet(name = "AddProduct", urlPatterns = {"/AddProduct"})
public class AddProduct extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control","no-store");
        PrintWriter out = response.getWriter();
        
        EJBecommerceStatefulRemote cart = (EJBecommerceStatefulRemote) request.getSession().getAttribute("ejbsession");
        
        String productName = request.getParameter("productname");
        String productCode = request.getParameter("code");
        int productQuantity = Integer.parseInt(request.getParameter("quantity"));
        double productPrice = Double.parseDouble(request.getParameter("unitprice"));
        String productImage = request.getParameter("image");
        
        if(cart==null){
            InitialContext ic;
            
            try{
                ic = new InitialContext();
                cart = (EJBecommerceStatefulRemote) ic.lookup("java:global/AEEcommerce/ejbs/EJBecommerceStateful");
                request.getSession().setAttribute("ejbsession", cart);
                
                out.printf(cart.addProduct(productCode, productName, productQuantity, productPrice, productImage));
                
            }catch (NamingException ex) {
                Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else{
            out.printf(cart.addProduct(productCode, productName, productQuantity, productPrice, productImage));
        }
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
        processRequest(request, response);
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
