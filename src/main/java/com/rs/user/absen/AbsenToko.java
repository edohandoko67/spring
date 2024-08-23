package com.rs.user.absen;

import javax.persistence.*;

@Entity
@Table(name = "absen_toko")
public class AbsenToko {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "absen_toko_id")
    private int absen_toko_id;

    @Lob
    @Column(name = "image_absen")
    private String image;

    @Column(name = "keterangan")
    private String keterangan;


    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    public AbsenToko () {}

    public AbsenToko(int absen_toko_id, String keterangan, String image, double latitude, double longitude) {
        this.absen_toko_id = absen_toko_id;
        this.keterangan = keterangan;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getAbsen_toko_id() {
        return absen_toko_id;
    }

    public void setAbsen_toko_id(int absen_toko_id) {
        this.absen_toko_id = absen_toko_id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
