/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.ejb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import mx.edu.ittepic.ecommerce.entities.Category;
import mx.edu.ittepic.ecommerce.entities.Company;
import mx.edu.ittepic.ecommerce.entities.Product;
import mx.edu.ittepic.ecommerce.entities.Role;
import mx.edu.ittepic.ecommerce.entities.Sale;
import mx.edu.ittepic.ecommerce.entities.Users;
import mx.edu.ittepic.ecommerce.utils.Message;

/**
 *
 * @author ernesto
 */
@Stateless
public class EJBecommerce {

    @PersistenceContext
    EntityManager entity;

    public String getRoles() {
        List<Role> listRoles;
        Message msg = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Query q = entity.createNamedQuery("Role.findAll");
        listRoles = q.getResultList();
        /////////////////
        /*String roles = "[";
    for (Role listrole : listRoles) {
        roles+="{\"roleid\":"+listrole.getRoleid()+","+"\"rolename\":\""+listrole.getRolename()+"\"},";
        
    }
    roles=roles.substring(0,roles.length()-1);
    roles += "]";// 721ms*/
        ////////////////
        for (int i = 0; i < listRoles.size(); i++) {
            listRoles.get(i).setUsersList(null);
        }
        ////////////////1.24 s/ 730 ms 86ms//

        msg.setCode(200);
        //msg.setMsg(roles);
        msg.setMsg(gson.toJson(listRoles));
        msg.setDetail("OK");//595 ms 717 71ms

        return gson.toJson(msg);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public String getRole(String roleid) {
        Message m = new Message();
        Role r;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            //r.setRoleid(Integer.parseInt(roleid));
            Query q = entity.createNamedQuery("Role.findByRoleid").setParameter("roleid", Integer.parseInt(roleid));
            //Query q = entity.createNamedQuery("Role.findByRoleid").set;
            //q.setParameter("roleid", roleid);

            r = (Role) q.getSingleResult();
            r.setUsersList(null);
            m.setCode(200);
            m.setMsg(gson.toJson(r));
            m.setDetail("OK");
        } catch (NoResultException e) {
            m.setCode(200);
            m.setMsg("");
            m.setDetail("OK");
        }
        return gson.toJson(m);
    }

    public String getRoleByName(String rolename) {
        Message m = new Message();
        List<Role> listRoles;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        //try{

        Query q = entity.createNativeQuery("select * from role where rolename like '%" + rolename + "%'", Role.class);
        listRoles = q.getResultList();
        m.setCode(200);
        m.setMsg(gson.toJson(listRoles));
        m.setDetail("OK");
        //}
        return gson.toJson(m);
    }

    public String deleteRole(String roleid) {

        Message m = new Message();
        Role r;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {

            Query q = entity.createNamedQuery("Role.findByRoleid").setParameter("roleid", Integer.parseInt(roleid));
            r = (Role) q.getSingleResult();
            r.setUsersList(null);

            entity.remove(r);
            entity.flush();

            m.setCode(200);
            m.setMsg("El rol fue eliminado correctamente");
            m.setDetail("OK");
        } catch (NoResultException e) {
            m.setCode(404);
            m.setMsg("El rol no fue encontrado.");
            m.setDetail(e.getMessage());
        } catch (NonUniqueResultException e) {
            m.setCode(400);
            m.setMsg("El rol no es unico, comunicate con el administrador.");
            m.setDetail(e.getMessage());
        } catch (IllegalStateException e) {
            m.setCode(422);
            m.setMsg("Error de entidad, el rol no es una entidad o ha sido removido.");
            m.setDetail(e.toString());
        } catch (QueryTimeoutException e) {
            m.setCode(422);
            m.setMsg("La operación tardo demasiado, por favor vuelve a intentarlo.");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(509);
            m.setMsg("La operación tardo demasiado, por favor vuelve a intentarlo.");
            m.setDetail(e.toString());
        }
        return gson.toJson(m);
    }

    public String newRole(String rolename) {
        Message m = new Message();
        Role r = new Role();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            r.setRolename(rolename);
            entity.persist(r); // Si el id existe hace un update, sino guarda una nuevo. Persistencia manejada por le contenedor
            entity.flush();

            m.setCode(200);
            m.setMsg("El rol se creó correctamente");
            m.setDetail(r.getRoleid() + "");
        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato '" + rolename + "'.");
            m.setDetail(e.toString());
        } catch (EntityExistsException e) {
            m.setCode(400);
            m.setMsg("El rol que intentas ingresar ya existe: '" + rolename + "'.");
            m.setDetail(e.toString());
        } catch (IllegalArgumentException e) {
            m.setCode(422);
            m.setMsg("Error de entidad, el rol no es una entidad o ha sido removido.");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(509);
            m.setMsg("La transacción no pudo ser completada. Espera un momento y vuelve a intentar.");
            m.setDetail(e.toString());
        } catch (EntityNotFoundException e) {
            m.setCode(404);
            m.setMsg("El rol introducido(" + rolename + ") no existe, no se puede actualizar.");
            m.setDetail(e.toString());
        }
        return gson.toJson(m);
    }

    public String updateRole(String roleid, String rolename) {
        Message m = new Message();
        Role r = new Role();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Query q = entity.createNamedQuery("Role.updateRole")
                .setParameter("rolename", rolename)
                .setParameter("roleid", Integer.parseInt(roleid));
        //Query q = entity.createNativeQuery("UPDATE role SET rolename = '"+rolename+"' WHERE roleid = "+roleid);
        try {
            if (q.executeUpdate() == 1) {
                m.setCode(200);
                m.setMsg("El rol se actualizó correctamente");
                m.setDetail("OK");
            } else {
                m.setCode(400);
                m.setMsg("No se realizo la actualización");
                m.setDetail("No existe el rol especificado");
            }

            /*int roleidint=Integer.parseInt(roleid);
        r.setRoleid(roleidint);    
        r.setRolename(rolename);
        //El refresh es para actualizar una entidad
        entity.refresh(entity.merge(r)); // Si el id existe hace un update, sino guarda una nuevo. Persistencia manejada por le contenedor
        entity.flush();*/
        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato '" + roleid + "'.");
            m.setDetail(e.toString());
        } catch (IllegalArgumentException e) {
            m.setCode(422);
            m.setMsg("Error de entidad, el rol no es una entidad o ha sido removido.");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(509);
            m.setMsg("La transacción no pudo ser completada. Espera un momento y vuelve a intentar.");
            m.setDetail(e.toString());
        } catch (EntityNotFoundException e) {
            m.setCode(404);
            m.setMsg("El rol introducido(" + roleid + ") no existe, no se puede actualizar.");
            m.setDetail(e.toString());
        }
        return gson.toJson(m);

    }

    public String newProduct(String code, String productname, String brand, String purchprice, String stock, String salepricemin, String salepricemay, String reorderpoint, String currency, String categoryid) {
        Message m = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Product p = new Product();
        Category c;

        try {
            c = entity.find(Category.class, Integer.parseInt(categoryid));
            p.setCode(code);
            p.setBrand(brand);
            p.setCategoryid(c);
            p.setCurrency(currency);
            p.setProductname(productname);
            p.setPurchprice(Double.parseDouble(purchprice));
            p.setReorderpoint(Integer.parseInt(reorderpoint));
            p.setSalepricemay(Double.parseDouble(salepricemay));
            p.setSalepricemin(Double.parseDouble(salepricemin));
            p.setStock(Integer.parseInt(stock));

            entity.persist(p);
            entity.flush();

            m.setCode(200);
            m.setMsg("El producto se creó correctamente");
            m.setDetail(p.getProductid() + "");
        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato '" + productname + "'.");
            m.setDetail(e.toString());
        } catch (EntityExistsException e) {
            m.setCode(400);
            m.setMsg("El producto que intentas ingresar ya existe: '" + productname + "'.");
            m.setDetail(e.toString());
        } catch (IllegalArgumentException e) {
            m.setCode(422);
            m.setMsg("Error de entidad, el producto no es una entidad o ha sido removido.");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(509);
            m.setMsg("La transacción no pudo ser completada. Espera un momento y vuelve a intentar.");
            m.setDetail(e.toString());
        } catch (EntityNotFoundException e) {
            m.setCode(404);
            m.setMsg("El producto introducido(" + productname + ") no existe, no se puede actualizar.");
            m.setDetail(e.toString());
        }
        return gson.toJson(m);
    }

    public String getReorderProducts() {
        List<Product> products;
        Message msg = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Query q = entity.createNamedQuery("Product.findReorderProducts");

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

    public String getProductById(String productid) {
        Product product;
        Message msg = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Query q = entity.createNamedQuery("Product.findByProductid");
            q.setParameter("productid", Integer.parseInt(productid));

            product = (Product) q.getSingleResult();
            product.getCategoryid().setProductList(null);
            msg.setCode(200);
            msg.setMsg(gson.toJson(product));
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
        }
        return gson.toJson(msg);
    }

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

    public String getProductByName(String productname) {
        Message m = new Message();
        List<Product> listProducts;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        //try{

        Query q = entity.createNativeQuery("select * from product where productname like '%" + productname + "%'", Product.class);
        listProducts = q.getResultList();
        for (Product p : listProducts) {
            p.getCategoryid().setProductList(null);
        }
        m.setCode(200);
        m.setMsg(gson.toJson(listProducts));
        m.setDetail("OK");
        //}
        return gson.toJson(m);
    }

    public String deleteProduct(String productid) {
        Message m = new Message();
        Product r;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {

            Query q = entity.createNamedQuery("Product.findByProductid").setParameter("productid", Integer.parseInt(productid));
            r = (Product) q.getSingleResult();

            entity.remove(r);
            entity.flush();

            m.setCode(200);
            m.setMsg("El product fue eliminado correctamente");
            m.setDetail("OK");
        } catch (NoResultException e) {
            m.setCode(404);
            m.setMsg("El producto no fue encontrado.");
            m.setDetail(e.getMessage());
        } catch (NonUniqueResultException e) {
            m.setCode(400);
            m.setMsg("El producto no es unico, comunicate con el administrador.");
            m.setDetail(e.getMessage());
        } catch (IllegalStateException e) {
            m.setCode(422);
            m.setMsg("Error de entidad, el producto no es una entidad o ha sido removido.");
            m.setDetail(e.toString());
        } catch (QueryTimeoutException e) {
            m.setCode(422);
            m.setMsg("La operación tardo demasiado, por favor vuelve a intentarlo.");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(509);
            m.setMsg("La operación tardo demasiado, por favor vuelve a intentarlo.");
            m.setDetail(e.toString());
        }
        return gson.toJson(m);
    }

    public String updateProduct(String productid, String code, String productname, String brand, String purchprice,
            String stock, String salepricemin, String salepricemay, String reorderpoint, String currency, String categoryid) {
        Message m = new Message();
        Product r = new Product();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Query q = entity.createNativeQuery("UPDATE product SET productname = '" + productname + "',"
                + "code = '" + code + "'"
                + ",brand = '" + brand + "'"
                + ",purchprice = " + purchprice + ""
                + ",stock = " + stock + ""
                + ",salepricemin = " + salepricemin + ""
                + ",salepricemay = " + salepricemay + ""
                + ",reorderpoint = " + reorderpoint + ""
                + ",currency = '" + currency + "'"
                + ",categoryid = " + categoryid + ""
                + " WHERE productid = " + productid);

        try {
            if (q.executeUpdate() == 1) {
                m.setCode(200);
                m.setMsg("El producto se actualizó correctamente");
                m.setDetail("OK");
            } else {
                m.setCode(400);
                m.setMsg("No se realizo la actualización");
                m.setDetail("No existe el producto especificado");
            }

        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato '" + productid + "'.");
            m.setDetail(e.toString());
        } catch (IllegalArgumentException e) {
            m.setCode(422);
            m.setMsg("Error de entidad, el producto no es una entidad o ha sido removido.");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(509);
            m.setMsg("La transacción no pudo ser completada. Espera un momento y vuelve a intentar.");
            m.setDetail(e.toString());
        } catch (EntityNotFoundException e) {
            m.setCode(404);
            m.setMsg("El producto introducido(" + productid + ") no existe, no se puede actualizar.");
            m.setDetail(e.toString());
        }
        return gson.toJson(m);

    }

    public String login(String user, String password) {
        Message m = new Message();
        Users u = new Users();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try {
            Query q = entity.createNamedQuery("Users.login")
                    .setParameter("username", user)
                    .setParameter("password", password);
            u = (Users) q.getSingleResult();
            m.setCode(200);
            m.setMsg("OK");
            m.setDetail("Login correcto");
        } catch (NoResultException e) {
            m.setCode(403);
            m.setMsg("No autorizado");
            m.setDetail(e.toString());
        }

        return gson.toJson(m);

    }

    public String newUser(String username, String password, String phone, String neigborhood, String zipcode,
            String city, String country, String state, String region, String street, String email,
            String streetnumber, String photo, String cellphone, String companyid, String roleid, String gender) {

        Message m = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Users u = new Users();
        Company c;
        Role r;

        try {
            c = entity.find(Company.class, Integer.parseInt(companyid));

            r = entity.find(Role.class, Integer.parseInt(roleid));

            u.setRoleid(r);
            u.setCompanyid(c);
            u.setCellphone(cellphone);
            u.setCity(city);
            u.setRegion(region);
            u.setCountry(country);
            u.setEmail(email);
            u.setGender(gender.charAt(0));
            u.setNeigborhood(neigborhood);
            u.setPassword(password);
            u.setPhone(phone);
            u.setPhoto(photo);
            u.setState(state);
            u.setStreet(street);
            u.setStreetnumber(streetnumber);
            u.setUsername(username);
            u.setZipcode(zipcode);

            entity.persist(u);
            entity.flush();

            m.setCode(200);
            m.setMsg("El usuario se creó correctamente");
            m.setDetail(u.getUserid() + "");
        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato '" + username + "'.");
            m.setDetail(e.toString());
        } catch (EntityExistsException e) {
            m.setCode(400);
            m.setMsg("El usuario que intentas ingresar ya existe: '" + username + "'.");
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
            m.setMsg("El usuario introducido(" + username + ") no existe, no se puede actualizar.");
            m.setDetail(e.toString());
        }
        return gson.toJson(m);
    }

    public String updateUser(String username, String userid, String phone, String neigborhood, String zipcode,
            String city, String country, String state, String region, String street, String email,
            String streetnumber, String photo, String cellphone, String companyid, String roleid, String gender) {
        Message m = new Message();
        Company c;
        Role r;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        c = entity.find(Company.class, Integer.parseInt(companyid));

        r = entity.find(Role.class, Integer.parseInt(roleid));

        Query q = entity.createNamedQuery("Users.updateUser")
                .setParameter("username", username)
                .setParameter("phone", phone)
                .setParameter("neigborhood", neigborhood)
                .setParameter("zipcode", zipcode)
                .setParameter("city", city)
                .setParameter("country", country)
                .setParameter("state", state)
                .setParameter("region", region)
                .setParameter("email", email)
                .setParameter("street", street)
                .setParameter("streetnumber", streetnumber)
                .setParameter("photo", photo)
                .setParameter("cellphone", cellphone)
                .setParameter("gender", gender.charAt(0))
                .setParameter("userid", Integer.parseInt(userid))
                .setParameter("roleid", r)
                .setParameter("companyid", c);
        try {
            if (q.executeUpdate() == 1) {
                m.setCode(200);
                m.setMsg("El usuario se actualizó correctamente");
                m.setDetail("OK");
            } else {
                m.setCode(400);
                m.setMsg("No se realizo la actualización");
                m.setDetail("No existe el usuario especificado");
            }

            /*int roleidint=Integer.parseInt(roleid);
        r.setRoleid(roleidint);    
        r.setRolename(rolename);
        //El refresh es para actualizar una entidad
        entity.refresh(entity.merge(r)); // Si el id existe hace un update, sino guarda una nuevo. Persistencia manejada por le contenedor
        entity.flush();*/
        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato '" + userid + "'.");
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
            m.setMsg("El usuario introducido(" + userid + ") no existe, no se puede actualizar.");
            m.setDetail(e.toString());
        }
        return gson.toJson(m);

    }
    public String deleteUser(String userid) {
        Message m = new Message();
        Users user;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Query q = entity.createNamedQuery("Users.findByUserid").
                    setParameter("userid", Integer.parseInt(userid));
            //se asigna el reultado a category
            user = (Users) q.getSingleResult();
            //esta funcion busca por id en la clase
            //category = entity.find(category.class,Integer.parseInt(categoryid));
            entity.remove(user);
            m.setCode(200);
            m.setMsg("El usuario se elimino correctamente");
            m.setDetail("OK");
        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato");
            m.setDetail(e.toString());
        } catch (IllegalArgumentException e) {
            m.setCode(422);
            m.setMsg("Su instancia no es una entidad ");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(500);
            m.setMsg("no hay ninguna transacción para invocar gestor de la entidad ");
            m.setDetail(e.toString());
        }
        return gson.toJson(m);
    }

    public String getUserByEmail(String email) {
        Message m = new Message();
        Users user;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {

            Query q = entity.createNamedQuery("Users.findByEmail")
                    .setParameter("email", email);
            user = (Users) q.getSingleResult();
            user.getCompanyid().setUsersList(null);
            user.getRoleid().setUsersList(null);
            user.setSaleList(null);
            user.setPassword(null);
            m.setCode(200);
            m.setMsg(gson.toJson(user));
            m.setDetail("OK");
        } catch (NoResultException e) {
            m.setCode(404);
            m.setMsg("El usuario no se encontro.");
            m.setDetail(e.toString());
        } catch (PessimisticLockException e) {
            m.setCode(404);
            m.setMsg("Pessimistic locking fails and the transaction is rolled back.");
            m.setDetail(e.toString());
        } catch (LockTimeoutException e) {
            m.setCode(404);
            m.setMsg("Pessimistic locking fails and only the statement is rolled back.");
            m.setDetail(e.toString());
        } catch (PersistenceException e) {
            m.setCode(404);
            m.setMsg("The query execution exceeds the query timeout value set and the transaction is rolled back.");
            m.setDetail(e.toString());
        }

        return gson.toJson(m);
    }
    
    public String getLogin(String username, String password){
        Message msg = new Message();
        Users user;
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try{
            Query q = entity.createNamedQuery("Users.findByUsername")
                    .setParameter("username", username).setParameter("password", password);
            user = (Users) q.getSingleResult();
            user.getCompanyid().setUsersList(null);
            user.getRoleid().setUsersList(null);
            user.setSaleList(null);
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

    public String getUserByUsername(String username) {
        Message m = new Message();
        Users user;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        //try{

        Query q = entity.createNamedQuery("Users.findByUsername")
                .setParameter("username", username);
        user = (Users) q.getSingleResult();
        user.getCompanyid().setUsersList(null);
        user.getRoleid().setUsersList(null);
        user.setSaleList(null);
        user.setPassword(null);
        m.setCode(200);
        m.setMsg(gson.toJson(user));
        m.setDetail("OK");
        //}
        return gson.toJson(m);
    }

    public String getUsers() {

        List<Users> users;
        Message msg = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Query q = entity.createNamedQuery("Users.findAll");

            users = q.getResultList();
            for (Users p : users) {
                p.setSaleList(null);
                p.getCompanyid().setUsersList(null);
                p.getRoleid().setUsersList(null);
            }
            msg.setCode(200);
            msg.setMsg(gson.toJson(users));
            msg.setDetail("OK");
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

    public String getCompanies() {
        List<Company> companies;
        Message msg = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Query q = entity.createNamedQuery("Company.findAll");

            companies = q.getResultList();
            for (Company p : companies) {
                p.setUsersList(null);
                /*for(Users u: p.getUsersList()){
                    u.setCompanyid(null);
                    u.setSaleList(null);
                    u.getRoleid().setUsersList(null);
                }*/
            }
            msg.setCode(200);
            msg.setMsg(gson.toJson(companies));
            msg.setDetail("OK");
        } catch (IllegalArgumentException e) {
            msg.setCode(422);
            msg.setMsg("Error de entidad, la compañia no es una entidad.");
            msg.setDetail(e.toString());
        } catch (IllegalStateException e) {
            msg.setCode(422);
            msg.setMsg("Error de entidad, la compañia  no es una entidad o ha sido removido.");
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

    /**
     * ***********************************************CATEGORY******************************************
     */
    public String newCategory(String name) {
        Message m = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Category c = new Category();

            c.setCategoryname(name);

            entity.persist(c);
            entity.flush();

            m.setCode(200);
            m.setMsg("El registro se inserto correctamente");
            m.setDetail("Se insertó la categoría " + c.getCategoryname());

        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato");
            m.setDetail(e.toString());
        } catch (IllegalArgumentException e) {
            m.setCode(422);
            m.setMsg("Su instancia no es una entidad ");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(500);
            m.setMsg("no hay ninguna transacción para invocar gestor de la entidad ");
            m.setDetail(e.toString());
        }
        return gson.toJson(m);
    }

    public String getCategory() {
        List<Category> listCategory;
        Message m = new Message();

        GsonBuilder builder = new GsonBuilder();

        Gson gson = builder.create();
        Query q = entity.createNamedQuery("Category.findAll");
        listCategory = q.getResultList();
        // 14-10 ms
        String category = "[]";
        if(listCategory.size()>0){
        category = "[";
        for (Category listCategory2 : listCategory) {
            category = category + "{\"categoryid\":" + listCategory2.getCategoryid() + ",\"categoryname\":\"" + listCategory2.getCategoryname() + "\"},";
        }
        category = category.substring(0, category.length() - 1);
        category = category + "]";
        }
        //  222 ms
        m.setCode(200);
        m.setMsg(category);
        m.setDetail("OK");
        return gson.toJson(m);
        //return listRoles.toString();
    }

    public String DeleteCategory(String categoryid) {
        Message m = new Message();
        Category category;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Query q = entity.createNamedQuery("Category.findByCategoryid").
                    setParameter("categoryid", Integer.parseInt(categoryid));
            //se asigna el reultado a category
            category = (Category) q.getSingleResult();
            category.setProductList(null);
            //esta funcion busca por id en la clase
            //category = entity.find(category.class,Integer.parseInt(categoryid));
            entity.remove(category);
            m.setCode(200);
            m.setMsg("el category se elimino correctamente");
            m.setDetail("OK");
        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato");
            m.setDetail(e.toString());
        } catch (IllegalArgumentException e) {
            m.setCode(422);
            m.setMsg("Su instancia no es una entidad ");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(500);
            m.setMsg("no hay ninguna transacción para invocar gestor de la entidad ");
            m.setDetail(e.toString());
        }
        return gson.toJson(m);
    }

    public String updateCategory(String categoryid, String name) {
        Message m = new Message();
        Category r = new Category();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            r = entity.find(Category.class, Integer.parseInt(categoryid));
            if (r == null) {
                m.setCode(200);
                m.setMsg("La categoria NO se actualizó correctamente");
                m.setDetail("No se encontro el Registro");
            } else {
                r.setCategoryname(name);
                entity.merge(r);
                m.setCode(200);
                m.setMsg("La categoria se actualizó correctamente");
                m.setDetail("OK");
            }
        } catch (NumberFormatException e) {
            m.setCode(406);
            m.setMsg("Error de tipo de dato '" + categoryid + "'");
            m.setDetail(e.toString());
        } catch (IllegalArgumentException e) {
            m.setCode(422);
            m.setMsg("Su instancia no es una entidad '" + categoryid + "'");
            m.setDetail(e.toString());
        } catch (TransactionRequiredException e) {
            m.setCode(500);
            m.setMsg("no hay ninguna transacción para invocar gestor de la entidad '" + categoryid + "'");
            m.setDetail(e.toString());
        } catch (EntityNotFoundException e) {
            m.setCode(406);
            m.setMsg("No se encontro el categoryid para actualizar '" + categoryid + "'");
            m.setDetail(e.toString());
        }
        return gson.toJson(m);
    }

    /**
     * ***************************COMPAAANY*******************************************
     */
    public String newCompany(String CompanyName, String neighborhood, String zipcode, String city, String country, String state, String region, String street, String streetnumber, String phone, String rfc, String logo) {
        Message m = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Company p = new Company();

            p.setCompanyname(CompanyName);
            p.setNeighborhood(neighborhood);
            p.setZipcode(zipcode);
            p.setCity(city);
            p.setCountry(country);
            p.setState(state);
            p.setRegion(region);
            p.setStreet(street);
            p.setStreetnumber(streetnumber);
            p.setPhone(phone);
            p.setRfc(rfc);
            p.setLogo(logo);

            entity.persist(p);
            entity.flush();

            m.setCode(200);
            m.setMsg("Insertado correctamente");
            m.setDetail("OK, El id es:" + p.getCompanyid().toString());
            return gson.toJson(m);
        } catch (EntityExistsException e) {
            m.setCode(404);
            m.setMsg(e.getMessage());
            m.setDetail("ERROR");
            return gson.toJson(m);
        } catch (IllegalArgumentException e) {
            m.setCode(404);
            m.setMsg(e.getMessage());
            m.setDetail("ERROR");
            return gson.toJson(m);
        } catch (PersistenceException e) {
            m.setCode(404);
            m.setMsg(e.getMessage());
            m.setDetail("ERROR");
            return gson.toJson(m);
        }
    }

    public String getCompanyAll() {

        Message m = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Company> listCompany;
        String msg = ""; //LA lista de roles que vienen de la base de datos convertido en json
        try {
            Query q = entity.createNamedQuery("Company.findAll");
            listCompany = q.getResultList();
            //Este codigo es para convertir list de objetos en json
            if (!listCompany.isEmpty()) {
                for (int i = 0; i < listCompany.size(); i++) {
                    listCompany.get(i).setUsersList(null);
                }
            }
            msg = gson.toJson(listCompany);
            if (listCompany.isEmpty()) {
                m.setCode(404);
                m.setMsg("No se encontro company");
                m.setDetail("ERROR");
            } else {
                msg = gson.toJson(listCompany);
                m.setCode(200);
                m.setMsg(msg);
                m.setDetail("OK");
            }
        } catch (IllegalArgumentException e) {
            m.setCode(404);
            m.setMsg(e.getMessage());
            m.setDetail("OK");

        }
        return gson.toJson(m);
    }

    public String updateCompany(String companyid, String companyname, String city, String country, String neigh, String zipcode, String state, String region, String street, String streetnumber, String phone, String rfc, String logo) {
        Message m = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Company r = new Company();
            r = entity.find(Company.class, Integer.parseInt(companyid));
            if (r == null) {
                m.setCode(404);
                m.setMsg("No se encontro");
                m.setDetail("");
            } else {
                r.setCompanyname(companyname);
                r.setCity(city);
                r.setCountry(country);
                r.setNeighborhood(neigh);
                r.setZipcode(zipcode);
                r.setState(state);
                r.setRegion(region);
                r.setStreet(street);
                r.setStreetnumber(streetnumber);
                r.setPhone(phone);
                r.setRfc(rfc);
                r.setLogo(logo);
                entity.merge(r);
                m.setCode(200);
                m.setMsg("Se modifico correctamente");
                m.setDetail("OK");
            }
            return gson.toJson(m);
        } catch (IllegalArgumentException e) {
            m.setCode(404);
            m.setMsg(e.getMessage());
            m.setDetail("Error");
            return gson.toJson(m);
        } catch (TransactionRequiredException e) {
            m.setCode(404);
            m.setMsg(e.getMessage());
            m.setDetail("Error");
            return gson.toJson(m);
        }

    }

    public String deleteCompany(String CompanyId) {
        Message m = new Message();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Company r = new Company();
            Query q = entity.createNamedQuery("Company.findByCompanyid").setParameter("companyid", Integer.parseInt(CompanyId));
            r = (Company) q.getSingleResult();
            if (r == null) {
                m.setCode(404);
                m.setMsg("No se encontro");
                m.setDetail("");
            } else {
                entity.remove(r);
                m.setCode(200);
                m.setMsg("Se elimino correctamente");
                m.setDetail("OK");
            }
            return gson.toJson(m);

        } catch (IllegalArgumentException e) {
            m.setCode(404);
            m.setMsg(e.getMessage());
            m.setDetail("error");
            return gson.toJson(m);
        } catch (NoResultException e) {
            m.setCode(404);
            m.setMsg(e.getMessage());
            m.setDetail("error");
            return gson.toJson(m);
        } catch (IllegalStateException e) {
            m.setCode(404);
            m.setMsg(e.getMessage());
            m.setDetail("error");
            return gson.toJson(m);
        } catch (TransactionRequiredException e) {
            m.setCode(404);
            m.setMsg(e.getMessage());
            m.setDetail("error");
            return gson.toJson(m);
        }
    }
    
    public String newSale(String userid, String amount){
        Message m = new Message();
        Sale s = new Sale();
        Users u;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Query q;
        try {
            q = entity.createNamedQuery("Users.findByUserid").setParameter("userid", Integer.parseInt(userid));
            u =(Users) q.getSingleResult();
            s.setAmount(Double.parseDouble(amount));
            s.setDate(new Date());
            s.setUserid(u);
            entity.persist(s); // Si el id existe hace un update, sino guarda una nuevo. Persistencia manejada por le contenedor
            entity.flush();

            m.setCode(200);
            m.setMsg("El sale se creó correctamente");
            m.setDetail(s.getSaleid()+ "");
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
        return gson.toJson(m);
    }

}
