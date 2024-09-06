package com.rs.product.stok.detail;

import com.rs.product.Product;
import com.rs.product.cart.Cart;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "detail_stock_image")
public class DetailStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetailStock;

    @ManyToOne
    @JoinColumn(name = "id_product")
    @Nullable
    private Product product;

    @Column(name = "jumlah_stok")
    private int jumlah_stock;

    @Column(name = "harga")
    private int price;

    @Lob
    @Column(name = "image")
    private String image;

    @Column(name = "name_varian")
    private String nameVarian;

    @OneToMany(mappedBy = "detailStock")
    private Set<Cart> cart;

    public DetailStock() {}

    public int getIdDetailStock() {
        return idDetailStock;
    }

    public void setIdDetailStock(int idDetailStock) {
        this.idDetailStock = idDetailStock;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameVarian() {
        return nameVarian;
    }

    public void setNameVarian(String nameVarian) {
        this.nameVarian = nameVarian;
    }

    public Set<Cart> getChart() {
        return cart;
    }

    public void setChart(Set<Cart> cart) {
        this.cart = cart;
    }
}
