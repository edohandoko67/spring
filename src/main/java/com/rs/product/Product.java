package com.rs.product;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rs.product.satuan.SatuanProduct;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "obat")
public class Product
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @ManyToMany
    @JoinTable(
            name = "product_satuan", // Nama tabel penghubung
            joinColumns = @JoinColumn(name = "product_id"), // Kolom foreign key untuk Product
            inverseJoinColumns = @JoinColumn(name = "satuan_id")  // Kolom foreign key untuk SatuanProduct
    )
    private Set<SatuanProduct> satuanProducts;

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
