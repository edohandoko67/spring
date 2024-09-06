package com.rs.product.stok;

import com.rs.product.Product;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock")
    private int id_stok;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "jumlah_stok")
    private int jumlah_stock;

    @Column(name = "harga")
    private int price;

    public Stock() {}


    public int getId_stok() {
        return id_stok;
    }

    public void setId_stok(int id_stok) {
        this.id_stok = id_stok;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getJumlah_stock() {
        return jumlah_stock;
    }

    public void setJumlah_stock(int jumlah_stock) {
        this.jumlah_stock = jumlah_stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
