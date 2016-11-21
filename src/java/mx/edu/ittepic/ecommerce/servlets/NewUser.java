/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import org.apache.commons.codec.digest.DigestUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.ittepic.ecommerce.ejb.EJBecommerce;

/**
 *
 * @author ernesto
 */
@WebServlet(name = "NewUser", urlPatterns = {"/NewUser"})
public class NewUser extends HttpServlet {
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
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("cache-control", "no-store");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String cellphone = request.getParameter("cellphone");
        String email = request.getParameter("email");
        String neigborhood = request.getParameter("neigborhood");
        String zipcode = request.getParameter("zipcode");
        String city = request.getParameter("city");
        String country = request.getParameter("country");        
        String state = request.getParameter("state");
        String region = request.getParameter("region");
        String street = request.getParameter("street");
        String streetnumber = request.getParameter("streetnumber");
        String photo = request.getParameter("photo");
        String companyid = request.getParameter("companyid");
        String roleid = request.getParameter("roleid");
        String gender = request.getParameter("gender");
        String passwordMD5=DigestUtils.md5Hex(password); 
              
        response.getWriter().print(ejb.newUser(username, passwordMD5, phone, neigborhood, zipcode, city, country, state, region, street, email, streetnumber, photo, cellphone, companyid, roleid, gender));
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
