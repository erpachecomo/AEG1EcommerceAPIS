/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.ejb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mx.edu.ittepic.ecommerce.entities.Login;
import mx.edu.ittepic.ecommerce.entities.Product;
import mx.edu.ittepic.ecommerce.entities.Productcart;

import mx.edu.ittepic.ecommerce.entities.ShoppingProduct;
import mx.edu.ittepic.ecommerce.entities.Role;
import mx.edu.ittepic.ecommerce.utils.Message;

/**
 *
 * @author miguel
 */
@Stateless
@Path("/product")
public class ProductServices {

    @PersistenceContext
    private EntityManager entity;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @GET
    @Path("/list")
    @Produces({MediaType.TEXT_PLAIN})
    public String getRoles() {
        List<Role> listRoles;
        Message msg = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Query q = entity.createNamedQuery("Role.findAll");
        listRoles = q.getResultList();
        for (int i = 0; i < listRoles.size(); i++) {
            listRoles.get(i).setUsersList(null);
        }

        msg.setCode(200);
        msg.setMsg(gson.toJson(listRoles));
        msg.setDetail("OK");//595 ms 717 71ms

        return gson.toJson(msg);
    }

    @GET
    @Path("/listProducts")
    @Produces({MediaType.TEXT_PLAIN})
    public String getProducts() {
        List<Product> products;
        Message msg = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Query q = entity.createNamedQuery("Product.findAll");

            products = q.getResultList();
            for (Product p : products) {
                p.getCategoryid().setProductList(null);
            }
            msg.setCode(200);
            msg.setMsg(gson.toJson(products));
            msg.setDetail("OK");
        } catch (IllegalArgumentException e) {
            msg.setCode(422);
            msg.setMsg("Error de entidad, el producto no es una entidad.");
            msg.setDetail(e.toString());
        } catch (IllegalStateException e) {
            msg.setCode(422);
            msg.setMsg("Error de entidad, el producto no es una entidad o ha sido removido.");
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

    @POST
    @Path("/prueba")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Message prueba(Login param) {
        //Login l=new GsonBuilder().create().fromJson(param, Login.class);

        //return "["+"{user: \""+l.getUsername()+"\",password: "+l.getPassword()+"}]";
        Message m = new Message();
        m.setCode(202);
        m.setMsg("Correcto recibido");
        m.setDetail("OK");
        return m;
    }

    @POST
    @Path("/addProductToCart")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Productcart addProductToCart(ShoppingProduct param) {

        Productcart product;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        
        try {
            Query q = entity.createNamedQuery("Productcart.findByUserid").setParameter("userid", param.getUserid());
            product = (Productcart)q.getSingleResult();
            product.setQuantity(product.getQuantity()+Integer.parseInt(param.getProductquantity()));

        } catch (NoResultException e) {
            product = new Productcart();
            product.setProductid(Integer.parseInt(param.getProductid()));
            product.setProductimage(param.getProductimage());
            product.setProductname(param.getProductname());
            product.setProductprice(Double.parseDouble(param.getProductprice()));
            product.setQuantity(Integer.parseInt(param.getProductquantity()));
            product.setUserid(Integer.parseInt(param.getUserid()));
            product.setProductcode(param.getProductcode());

        }
        return product;
    }
}
