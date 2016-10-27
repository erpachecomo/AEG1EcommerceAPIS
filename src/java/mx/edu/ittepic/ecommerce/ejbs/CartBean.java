/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.ejbs;

import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import mx.edu.ittepic.ecommerce.entities.ProductDetail;

/**
 *
 * @author ernesto
 */
@Stateful
@Remote(CartBeanRemote.class)
@EJB(name = "ejb/CartBean", beanInterface = CartBeanRemote.class, beanName = "CartBean")
//Ruta en donde lo puede buscar (def por el usuario), Interfaz, y el nombre para el JNDI
public class CartBean implements CartBeanRemote {

    List<ProductDetail> cart;

    @Override
    public String addProduct(String productid, String productname, String code, String quantity, String image, String unitprice) {
        int id = Integer.parseInt(productid);
        boolean addNew = true;
        for (ProductDetail p2 : cart) {
            if (p2.getId() == id) {
                p2.setQuantity(p2.getQuantity() + Integer.parseInt(quantity));
                addNew = false;
            }
        }
        if (addNew) {
            ProductDetail p = new ProductDetail();
            p.setId(Integer.parseInt(productid));
            p.setName(productname);
            p.setCode(code);
            p.setImage(image);
            p.setQuantity(Integer.parseInt(quantity));
            p.setUnitPrice(Double.parseDouble(unitprice));
            cart.add(p);
        }
        return new GsonBuilder().create().toJson(cart);
    }

    @Override
    public String removeProduct(String productid) {
        int id = Integer.parseInt(productid);
        for (ProductDetail p : cart) {
            if (p.getId() == id) {
                cart.remove(p);
            }
        }
        return new GsonBuilder().create().toJson(cart);
    }

    @Override
    @Remove
    public void remove() {
        //Aquí se cierran las conexiones a la BD
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PostConstruct
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        cart = new ArrayList<>();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
