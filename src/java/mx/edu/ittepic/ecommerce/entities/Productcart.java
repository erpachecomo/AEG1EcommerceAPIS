/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ernesto
 */
@Entity
@Table(name = "productcart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productcart.findAll", query = "SELECT p FROM Productcart p"),
    @NamedQuery(name = "Productcart.findByProductname", query = "SELECT p FROM Productcart p WHERE p.productname = :productname"),
    @NamedQuery(name = "Productcart.findByProductprice", query = "SELECT p FROM Productcart p WHERE p.productprice = :productprice"),
    @NamedQuery(name = "Productcart.findByProductimage", query = "SELECT p FROM Productcart p WHERE p.productimage = :productimage"),
    @NamedQuery(name = "Productcart.findByProductcode", query = "SELECT p FROM Productcart p WHERE p.productcode = :productcode"),
    @NamedQuery(name = "Productcart.findByUserid", query = "SELECT p FROM Productcart p WHERE p.userid = :userid"),
    @NamedQuery(name = "Productcart.findByQuantity", query = "SELECT p FROM Productcart p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Productcart.findByProductcartid", query = "SELECT p FROM Productcart p WHERE p.productcartid = :productcartid"),
    @NamedQuery(name = "Productcart.findByProductid", query = "SELECT p FROM Productcart p WHERE p.productid = :productid")})
public class Productcart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "productname")
    private String productname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "productprice")
    private Double productprice;
    @Size(max = 255)
    @Column(name = "productimage")
    private String productimage;
    @Size(max = 255)
    @Column(name = "productcode")
    private String productcode;
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "quantity")
    private Integer quantity;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "productcartid")
    private Integer productcartid;
    @Column(name = "productid")
    private Integer productid;

    public Productcart() {
    }

    public Productcart(Integer productcartid) {
        this.productcartid = productcartid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Double getProductprice() {
        return productprice;
    }

    public void setProductprice(Double productprice) {
        this.productprice = productprice;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductcartid() {
        return productcartid;
    }

    public void setProductcartid(Integer productcartid) {
        this.productcartid = productcartid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productcartid != null ? productcartid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productcart)) {
            return false;
        }
        Productcart other = (Productcart) object;
        if ((this.productcartid == null && other.productcartid != null) || (this.productcartid != null && !this.productcartid.equals(other.productcartid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.ecommerce.entities.Productcart[ productcartid=" + productcartid + " ]";
    }
    
}
