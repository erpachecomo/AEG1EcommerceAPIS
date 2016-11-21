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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mx.edu.ittepic.ecommerce.entities.Login;
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
    public String getRoles(){
        List<Role> role = new ArrayList<>();
        Query q = entity.createNamedQuery("Role.findAll");
        role = q.getResultList();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        
        return gson.toJson(role);
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
}