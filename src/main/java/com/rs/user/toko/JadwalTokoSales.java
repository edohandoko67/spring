package com.rs.user.toko;

import javax.persistence.*;

@Entity
@Table(name = "Toko")
public class JadwalTokoSales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jadwalToko_id;

    @Column(name = "name_toko")
    private String name_toko;

    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "image")
    private String image; // Untuk menyimpan gambar dalam bentuk byte array

    public JadwalTokoSales() {}

    public JadwalTokoSales(int jadwalToko_id, String name_toko, String address, String image) {
        this.jadwalToko_id = jadwalToko_id;
        this.name_toko = name_toko;
        this.address = address;
        this.image = image;
    }

    public int getJadwalToko_id() {
        return jadwalToko_id;
    }

    public void setJadwalToko_id(int jadwalToko_id) {
        this.jadwalToko_id = jadwalToko_id;
    }

    public String getName_toko() {
        return name_toko;
    }

    public void setName_toko(String name_toko) {
        this.name_toko = name_toko;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    @Override
//    public String toString() {
//        return "JadwalTokoSales{" +
//                "jadwalTokoId=" + jadwalToko_id +
//                ", nameToko='" + name_toko + '\'' +
//                ", address='" + address + '\'' +
//                ", image=" + Arrays.toString(image) +
//                '}';
//    }
}
