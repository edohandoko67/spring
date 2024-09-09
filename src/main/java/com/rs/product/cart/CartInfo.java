package com.rs.product.cart;

import com.rs.product.stok.detail.DetailStock;

public class CartInfo {
    private int idChart;
    private String name;
    private String userName;
    private int jumlah_stock;
    private int total;

    public CartInfo(int idChart, String name, String userName, int jumlah_stock, int total) {
        this.idChart = idChart;
        this.name = name;
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
}
