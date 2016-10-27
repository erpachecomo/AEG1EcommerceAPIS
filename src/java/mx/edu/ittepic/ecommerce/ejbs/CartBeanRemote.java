/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.ejbs;

import javax.ejb.Remote;

/**
 *
 * @author ernesto
 */
@Remote
public interface CartBeanRemote{
    public String addProduct(String productid, String productname,String code, String quantity, String image, String unitprice);
    public String removeProduct(String productid);
    public void remove();
    public void initialize();
        
}
