package com.rs.product.cart;

import com.rs.product.stok.detail.DetailStock;

public class CartInfo {
    private int idChart;
    private DetailStock detailStock;
    private int jumlah_stock;
    private int total;

    public CartInfo(int idChart, DetailStock detailStock, int jumlah_stock, int total) {
        this.idChart = idChart;
        this.detailStock = detailStock;
        this.jumlah_stock = jumlah_stock;
        this.total = total;
    }

    public int getIdChart() {
        return idChart;
    }

    public void setIdChart(int idChart) {
        this.idChart = idChart;
    }

    public DetailStock getDetailStock() {
        return detailStock;
    }

    public void setDetailStock(DetailStock detailStock) {
        this.detailStock = detailStock;
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
