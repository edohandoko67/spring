package com.rs.product.stok;

import com.rs.product.Product;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

public class StockInfo {

    private int id_stock;
    private String productName;
    @Lob
    private String image_product;

    @NotNull
    @Length(max = 99999)
    private int jumlah_stock;


    public StockInfo(int id_stock, String productName, String image_product, int jumlah_stock) {
        this.id_stock = id_stock;
        this.productName = productName;
        this.image_product = image_product;
        this.jumlah_stock = jumlah_stock;
    }

    public String getImage_product() {
        return image_product;
    }

    public void setImage_product(String image_product) {
        this.image_product = image_product;
    }

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getJumlah_stock() {
        return jumlah_stock;
    }

    public void setJumlah_stock(int jumlah_stock) {
        this.jumlah_stock = jumlah_stock;
    }
}
