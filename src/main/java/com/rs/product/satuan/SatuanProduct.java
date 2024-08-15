package com.rs.product.satuan;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "satuan_product")
public class SatuanProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_satuan;

    @Column(nullable = false, length = 50)
    @NotNull
    @Length(max = 50)
    private String satuan_product;

    @Column(nullable = false, length = 50)
    @NotNull
    @Length(max = 50)
    private String kode_product;

    public SatuanProduct() {}

    public SatuanProduct(Integer id_satuan, String satuan_product, String kode_product) {
        this.id_satuan = id_satuan;
        this.satuan_product = satuan_product;
        this.kode_product = kode_product;
    }

    public Integer getId_satuan() {
        return id_satuan;
    }

    public void setId_satuan(Integer id_satuan) {
        this.id_satuan = id_satuan;
    }

    public String getSatuan_product() {
        return satuan_product;
    }

    public void setSatuan_product(String satuan_product) {
        this.satuan_product = satuan_product;
    }

    public String getKode_product() {
        return kode_product;
    }

    public void setKode_product(String kode_product) {
        this.kode_product = kode_product;
    }
}
