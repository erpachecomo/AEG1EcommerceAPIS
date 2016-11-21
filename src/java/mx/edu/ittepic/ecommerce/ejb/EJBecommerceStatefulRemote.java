/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.ejb;

import javax.ejb.Remote;

/**
 *
 * @author miguel
 */
@Remote
public interface EJBecommerceStatefulRemote {
    public String addProduct(String productcode, String productname, int productquantity, double productprice, String image);
    public String removeProduct(String productcode);
    public void remove();
    public void initialize();
    public String getCart();
    public String login(String username, String password);
    public int getUserid();
    public String getUsername();
}
