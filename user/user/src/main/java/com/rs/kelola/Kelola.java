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
    @NotNull
    @Length(min = 5, max = 50)
    private String IdentitasPelapor;

    @NotNull
    @Length(min = 5, max = 50)
    private String deskripsi;

    public Kelola(){

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentitasPelapor() {
        return IdentitasPelapor;
    }

    public void setIdentitasPelapor(String identitasPelapor) {
        IdentitasPelapor = identitasPelapor;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
