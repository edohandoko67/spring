package com.rs.product.cart;

import com.rs.product.stok.detail.DetailStock;

import javax.persistence.Column;

public class CartInfo {
    private int idChart;
    private String name;
    private String image;
    @Column(name = "harga_awal")
    private int harga_awal;
    private int jumlah_awal;
    private String userName;
    private int jumlah_stock;
    private int total;

    public CartInfo(int idChart, String name, String image, int harga_awal, int jumlah_awal, String userName, int jumlah_stock, int total) {
        this.idChart = idChart;
        this.name = name;
        this.image = image;
        this.harga_awal = harga_awal;
        this.jumlah_awal = jumlah_awal;
        this.userName = userName;
        this.jumlah_stock = jumlah_stock;
        this.total = total;
    }

    public int getIdChart() {
        return idChart;
    }

    public void setIdChart(int idChart) {
        this.idChart = idChart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getJumlah_stock() {
        return jumlah_stock;
    }

    public void setJumlah_stock(int jumlah_stock) {
        this.jumlah_stock = jumlah_stock;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHarga_awal() {
        return harga_awal;
    }

    public void setHarga_awal(int harga_awal) {
        this.harga_awal = harga_awal;
    }

    public int getJumlah_awal() {
        return jumlah_awal;
    }

    public void setJumlah_awal(int jumlah_awal) {
        this.jumlah_awal = jumlah_awal;
    }
}
