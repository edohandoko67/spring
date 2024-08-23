package com.rs.product;

import com.rs.user.toko.JadwalTokoSales;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    @Column(name = "createdAt")
    @CreatedDate
    private LocalDate createdDate;

    @ManyToOne
    private JadwalTokoSales jadwalTokoSales;

    public JadwalTokoSales getJadwalTokoSales() {
        return jadwalTokoSales;
    }

    public void setJadwalTokoSales(JadwalTokoSales jadwalTokoSales) {
        this.jadwalTokoSales = jadwalTokoSales;
    }

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

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDate.now();
    }

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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
