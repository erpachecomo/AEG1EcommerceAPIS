/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ecommerce.entities;

/**
 *
 * @author miguel
 */
public class ShoppingProduct {
    private String productname;
    private String productcode;
    private int productquantity;
    private double productprice;
    private String productimage;

    public int getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(int productquantity) {
        this.productquantity = productquantity;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public void setProductprice(double productprice) {
        this.productprice = productprice;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getProductname() {
        return productname;
    }

    public String getProductcode() {
        return productcode;
    }

    public double getProductprice() {
        return productprice;
    }

    public String getProductimage() {
        return productimage;
    }
    
}
