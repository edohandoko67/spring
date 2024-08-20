package com.rs.user.jadwalSales;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "Jadwal_Sales")
public class JadwalSales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jadwalsales_id;

    @Column(nullable = false, length = 50)
    @NotNull
    @Length(max = 50)
    private String nameSales;

    @NotNull
    private String kode;

    private String name_store;

    @Nullable
    private LocalDate created_at;
    //private Boolean status;
    private String status;

    public JadwalSales(){}

    public JadwalSales(int jadwalsales_id, String nameSales, String kode, String name_store, LocalDate created_at, String status) {
        this.jadwalsales_id = jadwalsales_id;
        this.nameSales = nameSales;
        this.kode = kode;
        this.name_store = name_store;
        this.created_at = created_at;
        this.status = status;
    }

    public int getJadwalsales_id() {
        return jadwalsales_id;
    }

    public void setJadwalsales_id(int jadwalsales_id) {
        this.jadwalsales_id = jadwalsales_id;
    }

    public String getNameSales() {
        return nameSales;
    }

    public void setNameSales(String nameSales) {
        this.nameSales = nameSales;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getName_store() {
        return name_store;
    }

    public void setName_store(String name_store) {
        this.name_store = name_store;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

//    public Boolean getStatus() {
//        return status;
//    }
//
//    public void setStatus(Boolean status) {
//        this.status = status;
//    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
