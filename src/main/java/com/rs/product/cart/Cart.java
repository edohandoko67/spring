package com.rs.product.cart;

import com.rs.product.stok.detail.DetailStock;

import javax.persistence.*;

@Entity
@Table(name = "chart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idChart")
    private int idChart;

    @ManyToOne
    @JoinColumn(name = "id_stock")
    private DetailStock detailStock;

    @Column(name = "jumlah_stock")
    private int jumlah_stock;

    @Column(name = "total")
    private int total;

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
