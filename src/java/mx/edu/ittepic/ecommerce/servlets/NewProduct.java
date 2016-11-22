/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import mx.edu.ittepic.ecommerce.ejb.EJBecommerce;

/**
 *
 * @author ernesto
 */
@WebServlet(name = "NewProduct", urlPatterns = {"/NewProduct"})
@MultipartConfig
public class NewProduct extends HttpServlet {
    @EJB
    private EJBecommerce ejb;

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
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store");
        PrintWriter out = response.getWriter();
        
        
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
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store");
        processRequest(request, response);
        System.out.println("Estamos en el servlet");
        String productname = request.getParameter("productname");
        String code = request.getParameter("code");
        String brand = request.getParameter("brand");
        String purchprice = request.getParameter("purchprice");
        String stock = request.getParameter("stock");
        String salepricemin = request.getParameter("salepricemin");
        String salepricemay = request.getParameter("salepricemay");
        String currency = request.getParameter("currency");
        String reorderpoint = request.getParameter("reorderpoint");
        String categoryid = request.getParameter("categoryid");
        System.out.println(code   +  productname);
        
        Part filePart = request.getPart("foto");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        
        PrintWriter p = response.getWriter();
        
        p.print(ejb.newProduct(code, productname, brand, purchprice, stock, salepricemin, salepricemay, reorderpoint, currency, categoryid, filePart));
        
        
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
