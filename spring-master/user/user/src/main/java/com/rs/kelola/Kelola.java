package com.rs.kelola;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "keluhan")
public class Kelola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @Length(max = 50)
    private String nama;

    @Column(nullable = false, length = 50)
    @Length(min = 1, max = 50)
    private String kategori;

    @Length(max = 50)
    private String stok;
    private String harga;

    public Kelola(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
