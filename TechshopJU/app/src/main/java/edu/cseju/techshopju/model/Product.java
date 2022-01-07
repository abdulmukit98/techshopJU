package edu.cseju.techshopju.model;

import com.google.firebase.database.annotations.NotNull;

import java.io.Serializable;

public class Product implements Serializable {
    @NotNull
    private String productId;
    private String productName;
    private int productPrice;
    private String productImageLink;
    private String productDescription;

    public Product()
    {

    }

    public Product(String productId, String productName, int productPrice, String productImageLink, String productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImageLink = productImageLink;
        this.productDescription = productDescription;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImageLink() {
        return productImageLink;
    }

    public void setProductImageLink(String productImageLink) {
        this.productImageLink = productImageLink;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productImageLink='" + productImageLink + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
