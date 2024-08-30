package com.rs.lacak.tracking;

import java.time.LocalDate;


public class TrackingInfo {
    private int id_tracking;
    private String nameProduct;

    private String status;

    private String no_resi;

    private LocalDate timestamp;

    public TrackingInfo(int id_tracking, String nameProduct, String status, String no_resi, LocalDate timestamp) {
        this.id_tracking = id_tracking;
        this.nameProduct = nameProduct;
        this.status = status;
        this.no_resi = no_resi;
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

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNo_resi() {
        return no_resi;
    }

    public void setNo_resi(String no_resi) {
        this.no_resi = no_resi;
    }
}
