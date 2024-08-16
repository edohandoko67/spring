package com.rs.product;

import com.rs.product.satuan.SatuanProduct;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "product")
public class Product
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_product;

    private Integer products_satuan;

    @Column(nullable = false, length = 50)
    @NotNull
    @Length(max = 50)
    private String name;

    private float price;

    @NotNull
    @Length(max = 50)
    private String pembuat;

    private int quantity;

    private int discount;

    @NotNull
    @Length(min = 5, max = 50)
    private String alasan;


    public Product()
    {
    }

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SatuanProduct> satuanProducts;

    public List<SatuanProduct> getSatuanProducts() {
        return satuanProducts;
    }

    public void setSatuanProducts(List<SatuanProduct> satuanProducts) {
        this.satuanProducts = satuanProducts;
    }


    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public Integer getProducts_satuan() {
        return products_satuan;
    }

    public void setProducts_satuan(Integer products_satuan) {
        this.products_satuan = products_satuan;
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
