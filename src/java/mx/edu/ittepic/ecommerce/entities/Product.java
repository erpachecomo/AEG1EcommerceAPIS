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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ernesto
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductid", query = "SELECT p FROM Product p WHERE p.productid = :productid"),
    @NamedQuery(name = "Product.findByCode", query = "SELECT p FROM Product p WHERE p.code = :code"),
    @NamedQuery(name = "Product.findByProductname", query = "SELECT p FROM Product p WHERE p.productname = :productname"),
    @NamedQuery(name = "Product.findByBrand", query = "SELECT p FROM Product p WHERE p.brand = :brand"),
    @NamedQuery(name = "Product.findByStock", query = "SELECT p FROM Product p WHERE p.stock = :stock"),
    @NamedQuery(name = "Product.findByReorderpoint", query = "SELECT p FROM Product p WHERE p.reorderpoint = :reorderpoint"),
    @NamedQuery(name = "Product.findByCurrency", query = "SELECT p FROM Product p WHERE p.currency = :currency"),
    @NamedQuery(name = "Product.findByPurchprice", query = "SELECT p FROM Product p WHERE p.purchprice = :purchprice"),
    @NamedQuery(name = "Product.findBySalepricemay", query = "SELECT p FROM Product p WHERE p.salepricemay = :salepricemay"),
    @NamedQuery(name = "Product.findBySalepricemin", query = "SELECT p FROM Product p WHERE p.salepricemin = :salepricemin"),
    @NamedQuery(name = "Product.findByImage", query = "SELECT p FROM Product p WHERE p.image = :image")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "productid")
    private Integer productid;
    @Size(max = 20)
    @Column(name = "code")
    private String code;
    @Size(max = 40)
    @Column(name = "productname")
    private String productname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "brand")
    private String brand;
    @Column(name = "stock")
    private Integer stock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reorderpoint")
    private int reorderpoint;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "currency")
    private String currency;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "purchprice")
    private Double purchprice;
    @Column(name = "salepricemay")
    private Double salepricemay;
    @Column(name = "salepricemin")
    private Double salepricemin;
    @Size(max = 256)
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "categoryid", referencedColumnName = "categoryid")
    @ManyToOne(optional = false)
    private Category categoryid;

    public Product() {
    }

    public Product(Integer productid) {
        this.productid = productid;
    }

    public Product(Integer productid, String brand, int reorderpoint, String currency) {
        this.productid = productid;
        this.brand = brand;
        this.reorderpoint = reorderpoint;
        this.currency = currency;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public int getReorderpoint() {
        return reorderpoint;
    }

    public void setReorderpoint(int reorderpoint) {
        this.reorderpoint = reorderpoint;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPurchprice() {
        return purchprice;
    }

    public void setPurchprice(Double purchprice) {
        this.purchprice = purchprice;
    }

    public Double getSalepricemay() {
        return salepricemay;
    }

    public void setSalepricemay(Double salepricemay) {
        this.salepricemay = salepricemay;
    }

    public Double getSalepricemin() {
        return salepricemin;
    }

    public void setSalepricemin(Double salepricemin) {
        this.salepricemin = salepricemin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Category categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productid != null ? productid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productid == null && other.productid != null) || (this.productid != null && !this.productid.equals(other.productid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.ecommerce.entities.Product[ productid=" + productid + " ]";
    }
    
}
