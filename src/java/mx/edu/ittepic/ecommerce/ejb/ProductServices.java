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
import javax.annotation.Resource;
import javax.ejb.ApplicationException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import mx.edu.ittepic.ecommerce.entities.ApiKey;
import mx.edu.ittepic.ecommerce.entities.Checkout;

import mx.edu.ittepic.ecommerce.entities.Login;
import mx.edu.ittepic.ecommerce.entities.Product;
import mx.edu.ittepic.ecommerce.entities.Productcart;

import mx.edu.ittepic.ecommerce.entities.ShoppingProduct;
import mx.edu.ittepic.ecommerce.entities.Role;
import mx.edu.ittepic.ecommerce.entities.Sale;
import mx.edu.ittepic.ecommerce.entities.Salesline;
import mx.edu.ittepic.ecommerce.entities.UpdatePassword;
import mx.edu.ittepic.ecommerce.entities.Users;
import mx.edu.ittepic.ecommerce.utils.Message;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author miguel
 */
@Stateless
@Path("/product")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductServices {

    @Resource
    private SessionContext context;
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
    @Path("/newSale")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String newSale(Checkout checkout) {
        Message m = new Message();
        Sale s = new Sale();

        List<Productcart> productosusuario;
        Users u;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Query q;
        try {
            //entity.getTransaction().begin();
            q = entity.createNamedQuery("Users.findByUserid").setParameter("userid", Integer.parseInt(checkout.getUserid()));
            u = (Users) q.getSingleResult();
            s.setAmount(Double.parseDouble(checkout.getAmount()));
            s.setDate(new Date());
            s.setUserid(u);
            entity.persist(s); // Si el id existe hace un update, sino guarda una nuevo. Persistencia manejada por le contenedor
            entity.flush();
            System.out.println("Sale" + s.getSaleid() + " Insertado");

            q = entity.createNamedQuery("Productcart.findByUserid").setParameter("userid", Integer.parseInt(checkout.getUserid()));
            productosusuario = q.getResultList();
            for (Productcart producto : productosusuario) {
                q = entity.createNamedQuery("Product.findByProductid").setParameter("productid", producto.getProductid());
                Product producti = (Product) q.getSingleResult();
                Salesline salesline = new Salesline();
                salesline.setProductid(producti);
                salesline.setPurchprice((producti.getPurchprice()));
                salesline.setSaleprice(producto.getProductprice());
                salesline.setQuantity(producto.getQuantity());
                salesline.setSaleid(s);
                entity.persist(salesline); // Si el id existe hace un update, sino guarda una nuevo. Persistencia manejada por le contenedor
                entity.flush();
                System.out.println("Salesline " + salesline.getSaleslineid() + " Insertado");
                int stock = producti.getStock() - producto.getQuantity();
                if (stock < 0) {
                    //entity.getTransaction().rollback();
                    m.setCode(400);
                    m.setMsg("Error rollback");
                    m.setDetail("El stock es menor a lo pedido");
                    return gson.toJson(m);

                }
                q = entity.createNativeQuery("UPDATE product SET stock = " + stock + " WHERE productid = " + producti.getProductid());
                if (q.executeUpdate() == 1) {
                    System.out.println("Producto " + producti.getProductid() + " actualizado");
                } else {
                    System.out.println("NOOOOOOOOOOOOOOO Producto " + producti.getProductid() + " actualizado");
                }
            }
            q = entity.createNamedQuery("Productcart.findByUserid").setParameter("userid", Integer.parseInt(checkout.getUserid()));
            productosusuario = q.getResultList();
            for (Productcart producto : productosusuario) {
                entity.remove(producto);
                System.out.println("Producto eliminado");
            }
            m.setCode(200);
            m.setMsg("Checkout correcto");
            m.setDetail(s.getSaleid() + "");
            //entity.getTransaction().commit();

        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato.");
            m.setDetail(e.toString());
        } catch (EntityExistsException e) {
            m.setCode(400);
            m.setMsg("El rol que intentas ingresar ya existe.");
            m.setDetail(e.toString());
        } catch (IllegalArgumentException e) {
            m.setCode(422);
            m.setMsg("Error de entidad, el sale no es una entidad o ha sido removido.");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(509);
            m.setMsg("La transacción no pudo ser completada. Espera un momento y vuelve a intentar.");
            m.setDetail(e.toString());
        } catch (EntityNotFoundException e) {
            m.setCode(404);
            m.setMsg("El sale introducido no existe, no se puede actualizar.");
            m.setDetail(e.toString());
        }
        entity.close();
        return gson.toJson(m);
    }

    @POST
    @Path("/addProductToCart")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String addProductToCart(ShoppingProduct param) {

        List<Productcart> productosusuario = new ArrayList<>();
        Productcart product;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        int a;
        a = 0;
        try {
            Query q = entity.createNamedQuery("Productcart.findByUserid").setParameter("userid", Integer.parseInt(param.getUserid()));
            productosusuario = q.getResultList();
            a = q.getResultList().size();

            if (a == 0) {
                product = new Productcart();
                product.setProductid(Integer.parseInt(param.getProductid()));
                product.setProductimage(param.getProductimage());
                product.setProductname(param.getProductname());
                product.setProductprice(Double.parseDouble(param.getProductprice()));
                product.setQuantity(Integer.parseInt(param.getProductquantity()));
                product.setUserid(Integer.parseInt(param.getUserid()));
                product.setProductcode(param.getProductcode());
                entity.merge(product);
                productosusuario = q.getResultList();
            } else {
                for (Productcart p : productosusuario) {

                    if (p.getProductid().toString().equals(param.getProductid())) {
                        p.setQuantity(p.getQuantity() + Integer.parseInt(param.getProductquantity()));
                        entity.merge(p);
                        productosusuario = q.getResultList();
                        return gson.toJson(productosusuario);
                    }
                }
                product = new Productcart();
                product.setProductid(Integer.parseInt(param.getProductid()));
                product.setProductimage(param.getProductimage());
                product.setProductname(param.getProductname());
                product.setProductprice(Double.parseDouble(param.getProductprice()));
                product.setQuantity(Integer.parseInt(param.getProductquantity()));
                product.setUserid(Integer.parseInt(param.getUserid()));
                product.setProductcode(param.getProductcode());
                entity.persist(product);
                entity.flush();
                productosusuario = q.getResultList();
                return gson.toJson(productosusuario);
            }

        } catch (NoResultException e) {
            product = new Productcart();
            product.setProductid(Integer.parseInt(param.getProductid()));
            product.setProductimage(param.getProductimage());
            product.setProductname(param.getProductname());
            product.setProductprice(Double.parseDouble(param.getProductprice()));
            product.setQuantity(Integer.parseInt(param.getProductquantity()));
            product.setUserid(Integer.parseInt(param.getUserid()));
            product.setProductcode(param.getProductcode());
            entity.merge(product);

        }

        return gson.toJson(productosusuario);
    }

    @GET
    @Path("/login")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.TEXT_PLAIN})
    public String login(@QueryParam("username") String user, @QueryParam("pass") String pass) {

        Message m = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Users user1;
        String passmd5 = DigestUtils.md5Hex(pass + "");
        try {

            //u.setApikey(DigestUtils.md5Hex(u.getUserid()+""));
            Query q = entity.createNamedQuery("Users.findByUsername").setParameter("username", user);

            user1 = (Users) q.getSingleResult();
            user1.getCompanyid().setUsersList(null);
            user1.getRoleid().setUsersList(null);

            //      user.setSaleList(null);
            if (user1.getPassword().equals(passmd5)) {
                m.setCode(200);
                m.setMsg(gson.toJson(user1));
                m.setDetail("OK");
            } else {
                m.setCode(400);
                m.setMsg("NOT MATCHING");
                m.setDetail("OK");
            }
        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato.");
            m.setDetail(e.toString());
        } catch (EntityExistsException e) {
            m.setCode(400);
            m.setMsg("El rol que intentas ingresar ya existe.");
            m.setDetail(e.toString());
        } catch (IllegalArgumentException e) {
            m.setCode(422);
            m.setMsg("Error de entidad, el sale no es una entidad o ha sido removido.");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(509);
            m.setMsg("La transacción no pudo ser completada. Espera un momento y vuelve a intentar.");
            m.setDetail(e.toString());
        } catch (EntityNotFoundException e) {
            m.setCode(404);
            m.setMsg("El sale introducido no existe, no se puede actualizar.");
            m.setDetail(e.toString());
        } catch (NoResultException e) {
            m.setCode(404);
            m.setMsg("El usuario introducido no existe, no se puede actualizar.");
            m.setDetail(e.toString());
        }

        return gson.toJson(m);

    }

    @PUT
    @Path("/newPassword")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Message newPassword(UpdatePassword up) {
        Message m = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Users user;
            Query q = entity.createNamedQuery("Users.findByApikey").setParameter("apikey", up.getApikey());
            user = (Users) q.getSingleResult();

            if (user.getUserid() != (Integer.parseInt(up.getUserid()))) {
                m.setCode(400);
                m.setMsg("El id no es valido");
                m.setDetail("OK");
            } else {
                user.setPassword(DigestUtils.md5Hex(up.getPassword()));
                entity.merge(user);
                m.setCode(200);
                m.setMsg("Se modifico correctamente");
                m.setDetail("OK");

            }
        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato.");
            m.setDetail(e.toString());
        } catch (EntityExistsException e) {
            m.setCode(400);
            m.setMsg("El usuario que intentas ingresar ya existe.");
            m.setDetail(e.toString());
        } catch (IllegalArgumentException e) {
            m.setCode(422);
            m.setMsg("Error de entidad, el usuario no es una entidad o ha sido removido.");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(509);
            m.setMsg("La transacción no pudo ser completada. Espera un momento y vuelve a intentar.");
            m.setDetail(e.toString());
        } catch (EntityNotFoundException e) {
            m.setCode(404);
            m.setMsg("El valor introducido no existe, no se puede actualizar.");
            m.setDetail(e.toString());
        } catch (NoResultException e) {
            m.setCode(404);
            m.setMsg("El usuario introducido no existe, no se puede actualizar.");
            m.setDetail(e.toString());
        }
        return m;

    }

    @POST
    @Path("/checkapikey")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String checkapikey(ApiKey apikey1) {
        Message m = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String apikey = apikey1.getApikey();
        try {
            System.out.println("Apikey!!!!! " + apikey);
            Query q = entity.createNamedQuery("Users.findByApikey").setParameter("apikey", apikey);

            Users user1 = (Users) q.getSingleResult();
            user1.getCompanyid().setUsersList(null);
            user1.getRoleid().setUsersList(null);
            m.setCode(200);
            m.setMsg(gson.toJson(user1));
            m.setDetail("OK");

        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato.");
            m.setDetail(e.toString());
        } catch (EntityExistsException e) {
            m.setCode(400);
            m.setMsg("El rol que intentas ingresar ya existe.");
            m.setDetail(e.toString());
        } catch (IllegalArgumentException e) {
            m.setCode(422);
            m.setMsg("Error de entidad, el sale no es una entidad o ha sido removido.");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(509);
            m.setMsg("La transacción no pudo ser completada. Espera un momento y vuelve a intentar.");
            m.setDetail(e.toString());
        } catch (EntityNotFoundException e) {
            m.setCode(404);
            m.setMsg("El sale introducido no existe, no se puede actualizar.");
            m.setDetail(e.toString());
        } catch (NoResultException e) {
            m.setCode(404);
            m.setMsg("El usuario introducido no existe, no se puede actualizar.");
            m.setDetail(e.toString());
        }

        return gson.toJson(m);
    }
    
    @ApplicationException(rollback=true)
    public class StockException extends Exception{
    
    }
}
