package com.rs.product;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_product;

    @NotNull
    @Length(min = 5, max = 16)
    private String name;

    private float price;

    @Length(min = 5, max = 50)
    private String pembuat;


    private int quantity;

    private int discount;

    @Length(min = 5, max = 50)
    private String alasan;

    //private Set<SatuanProduct> satuanProducts;

//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<SatuanProduct> satuanProducts;
//
//    public List<SatuanProduct> getSatuanProducts() {
//        return satuanProducts;
//    }
//
//    public void setSatuanProducts(List<SatuanProduct> satuanProducts) {
//        this.satuanProducts = satuanProducts;
//    }

    public ProductInfo() {}

    public ProductInfo(Integer id_product, String name, float price, String pembuat, int quantity, int discount, String alasan) {
        this.id_product = id_product;
        this.name = name;
        this.price = price;
        this.pembuat = pembuat;
        this.quantity = quantity;
        this.discount = discount;
        this.alasan = alasan;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPembuat() {
        return pembuat;
    }

    public void setPembuat(String pembuat) {
        this.pembuat = pembuat;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }
}
