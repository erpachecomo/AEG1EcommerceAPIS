/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.ejb;

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
import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import mx.edu.ittepic.ecommerce.entities.ShoppingProduct;
import mx.edu.ittepic.ecommerce.entities.Users;
import mx.edu.ittepic.ecommerce.utils.Message;

/**
 *
 * @author miguel
 */
@Stateful
@Remote(EJBecommerceStatefulRemote.class)
@EJB(name = "ejb/EJBecommerceStateful", beanInterface = EJBecommerceStatefulRemote.class, beanName = "EJBecommerceStateful")
public class EJBecommerceStateful implements EJBecommerceStatefulRemote {
    List<ShoppingProduct> cart;
    EntityManager entity;
    String usernombre;
    int userid;
    
    @Override
    public String addProduct(String productcode, String productname, int productquantity, double productprice, String image) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ShoppingProduct sp = new ShoppingProduct();
        sp.setProductcode(productcode);
        sp.setProductname(productname);
        sp.setProductquantity(productquantity);
        sp.setProductprice(productprice);
        sp.setProductimage(image);
        
        for(ShoppingProduct p : cart){
            if(productcode.equals(p.getProductcode())){
                p.setProductquantity(p.getProductquantity()+productquantity);
                return new GsonBuilder().create().toJson(cart);
            }
        }
        cart.add(sp);
        return new GsonBuilder().create().toJson(cart);
    }

    @Override
    public String removeProduct(String productcode) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        for (ShoppingProduct p : cart) {
            if (p.getProductcode().equals(productcode)) {
                if(cart.remove(p))
                {
                    return new GsonBuilder().create().toJson(cart);
                }
            }
        }
        return "caca";
    }

    @Override
    @Remove
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PostConstruct
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        cart = new ArrayList<>();
    }

    @Override
    public String getCart() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return new GsonBuilder().create().toJson(cart);
    }

    @Override
    public String login(String username, String password) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        Message msg = new Message();
        Users user;
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try{
            Query q = entity.createNamedQuery("Users.findByUsername")
                    .setParameter("username", username).setParameter("password", password);
            user = (Users) q.getSingleResult();
            
            msg.setCode(200);
            msg.setMsg(gson.toJson(user));
            msg.setDetail("Ok");
            usernombre = user.getUsername();
            userid = user.getUserid();
            
            cart = new ArrayList<>();
            
        } catch (IllegalArgumentException e) {
            msg.setCode(422);
            msg.setMsg("Error de entidad, el usuario no es una entidad.");
            msg.setDetail(e.toString());
        } catch (IllegalStateException e) {
            msg.setCode(422);
            msg.setMsg("Error de entidad, el usuario no es una entidad o ha sido removido.");
            msg.setDetail(e.toString());
        } catch (QueryTimeoutException e) {
            msg.setCode(509);
            msg.setMsg("La operación tardo demasiado, por favor vuelve a intentarlo.");
            msg.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            msg.setCode(509);
            msg.setMsg("La operación tardo demasiado, por favor vuelve a intentarlo.");
            msg.setDetail(e.toString());
        } catch (PessimisticLockException e) {
            msg.setCode(400);
            msg.setMsg("Error, operación bloqueada (Pesimistic), no se realizo la transacción.");
            msg.setDetail(e.toString());
        } catch (LockTimeoutException e) {
            msg.setCode(400);
            msg.setMsg("Error, operación bloqueada (Lock), no se realizo la transacción.");
            msg.setDetail(e.toString());
        } catch (PersistenceException e) {
            msg.setCode(400);
            msg.setMsg("Error, operación bloqueada (Persistence), no se realizo la transacción.");
            msg.setDetail(e.toString());
        }
        return gson.toJson(msg);
    }

    @Override
    public int getUserid() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return userid;
    }

    @Override
    public String getUsername() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return usernombre;
    }

    
}
