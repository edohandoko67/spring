package com.rs.product;

import com.rs.product.satuan.SatuanProduct;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    private Set<SatuanProduct> satuanProducts;
    public ProductInfo() {}

    public ProductInfo(Integer id, String name, float price, String pembuat, @Nullable int quantity, @Nullable int discount, @Nullable String alasan, Set<SatuanProduct> satuanProducts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pembuat = pembuat;
        this.quantity = quantity;
        this.discount = discount;
        this.alasan = alasan;
        this.satuanProducts = satuanProducts;
    }

    public Set<SatuanProduct> getSatuanProducts() {
        return satuanProducts;
    }

    public void setSatuanProducts(Set<SatuanProduct> satuanProducts) {
        this.satuanProducts = satuanProducts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
