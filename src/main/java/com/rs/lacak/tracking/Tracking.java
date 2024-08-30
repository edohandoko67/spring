package com.rs.lacak.tracking;


import com.rs.product.Product;
import com.rs.user.jadwalSales.JadwalSales;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "Tracking")
public class Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tracking")
    private int id_tracking;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "jadwalsales_id")
    private JadwalSales jadwalSales;

    private String status;

    @Column(name = "no_resi")
    @Length(max = 255)
    private String noResi;
    @Column(name = "timestamp")
    private LocalDate timestamp;

    public Tracking() {}

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDate.now();
    }

//    public Tracking(int id_tracking, Product product, String status, String no_resi, LocalDateTime timestamp) {
//        this.id_tracking = id_tracking;
//        this.product = product;
//        this.status = status;
//        this.no_resi = no_resi;
//        this.timestamp = timestamp;
//    }

    public int getId_tracking() {
        return id_tracking;
    }

    public void setId_tracking(int id_tracking) {
        this.id_tracking = id_tracking;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public JadwalSales getJadwalSales() {
        return jadwalSales;
    }

    public void setJadwalSales(JadwalSales jadwalSales) {
        this.jadwalSales = jadwalSales;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getNoResi() {
        return noResi;
    }

    public void setNoResi(String noResi) {
        this.noResi = noResi;
    }
}
