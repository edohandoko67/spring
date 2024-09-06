package com.rs.product.stok.detail;

import com.rs.product.Product;
import org.springframework.lang.Nullable;

import javax.persistence.Lob;

public class DetailStockInfo {
    private int idDetailStock;
    @Nullable
    @Lob
    private String image_detail;
    private int jumlah_stock;
    private int price;

    @Lob
    private String image;

    private String nameVarian;

    public DetailStockInfo(int idDetailStock, @Nullable String image_detail, int jumlah_stock, int price, String image, String nameVarian) {
        this.idDetailStock = idDetailStock;
        this.image_detail = image_detail;
        this.jumlah_stock = jumlah_stock;
        this.price = price;
        this.image = image;
        this.nameVarian = nameVarian;
    }

    public int getIdDetailStock() {
        return idDetailStock;
    }

    public void setIdDetailStock(int idDetailStock) {
        this.idDetailStock = idDetailStock;
    }

    public String getImage_detail() {
        return image_detail;
    }

    public void setImage_detail(String image_detail) {
        this.image_detail = image_detail;
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
}
