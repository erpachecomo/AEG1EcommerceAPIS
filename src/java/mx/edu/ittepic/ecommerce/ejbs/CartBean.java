/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.ejbs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.edu.ittepic.ecommerce.entities.ProductDetail;
import mx.edu.ittepic.ecommerce.entities.Users;
import mx.edu.ittepic.ecommerce.utils.Message;

/**
 * @author ernesto
 */
@Stateful
@Remote(CartBeanRemote.class)
@EJB(name = "ejb/CartBean", beanInterface = CartBeanRemote.class, beanName = "CartBean")
//Ruta en donde lo puede buscar (def por el usuario), Interfaz, y el nombre para el JNDI
public class CartBean implements CartBeanRemote {
    @PersistenceContext
    EntityManager entity;
    List<ProductDetail> cart;
    String username;
    int userid;
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

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
        cart=null;
    }

    @Override
    @PostConstruct
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        cart = new ArrayList<>();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public String login(String username, String password) {
        Message m = new Message();
        Users u = new Users();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try {
            Query q = entity.createNamedQuery("Users.login")
                    .setParameter("username", username)
                    .setParameter("password", password);
            u = (Users) q.getSingleResult();
            m.setCode(200);
            m.setMsg("OK");
            m.setDetail("Login correcto");
            this.username=u.getUsername();
            this.userid=u.getUserid();
            cart = new ArrayList<>();
        } catch (NoResultException e) {
            m.setCode(403);
            m.setMsg("No autorizado");
            m.setDetail(e.toString());
        }

        return gson.toJson(m);

    }
}
