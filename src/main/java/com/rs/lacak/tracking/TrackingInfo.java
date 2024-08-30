package com.rs.lacak.tracking;

import java.time.LocalDate;


public class TrackingInfo {
    private int id_tracking;
    private String nameProduct;

    private String name_sales;

    private String status;

    private String noResi;

    private LocalDate timestamp;

    public TrackingInfo(int id_tracking, String nameProduct, String name_sales, String status, String noResi, LocalDate timestamp) {
        this.id_tracking = id_tracking;
        this.nameProduct = nameProduct;
        this.name_sales = name_sales;
        this.status = status;
        this.noResi = noResi;
        this.timestamp = timestamp;
    }

    public int getId_tracking() {
        return id_tracking;
    }

    public void setId_tracking(int id_tracking) {
        this.id_tracking = id_tracking;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getName_sales() {
        return name_sales;
    }

    public void setName_sales(String name_sales) {
        this.name_sales = name_sales;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoResi() {
        return noResi;
    }

    public void setNoResi(String noResi) {
        this.noResi = noResi;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}
