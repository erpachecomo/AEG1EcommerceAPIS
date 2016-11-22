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
@WebServlet(name = "UpdateProduct", urlPatterns = {"/UpdateProduct"})
@MultipartConfig
public class UpdateProduct extends HttpServlet {
@EJB
EJBecommerce ejbM;
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
        
        String productid = request.getParameter("productid");
        String productname = request.getParameter("productname2");
        String code = request.getParameter("code2");
        String brand = request.getParameter("brand2");
        String purchprice = request.getParameter("purchprice2");
        String stock = request.getParameter("stock2");
        String salepricemin = request.getParameter("salepricemin2");
        String salepricemay = request.getParameter("salepricemay2");
        String currency = request.getParameter("currency2");
        String reorderpoint = request.getParameter("reorderpoint2");
        String categoryid = request.getParameter("categoryid2");
        
        Part filePart = request.getPart("foto2");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        
        PrintWriter out = response.getWriter();
        out.print(ejbM.updateProduct(productid, code, productname, brand, purchprice, stock, salepricemin, salepricemay, reorderpoint, currency, categoryid, filePart));
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
