package com.rs.user.toko;

import javax.persistence.*;

@Entity
@Table(name = "Toko")
public class JadwalTokoSales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jadwal_toko_id")
    private int jadwalToko_id;

    @Column(name = "name_toko")
    private String name_toko;

    @Column(name = "address")
    private String address;

    @Column(name = "nomer_so")
    private String nomer_so;

    @Column(name = "provinsi")
    private String provinsi;

    @Column(name = "kota")
    private String kota;

    @Column(name = "kecamatan")
    private String kecamatan;

    @Column(name = "desa")
    private String desa;

    @Column(name = "owner")
    private String namaOwner;

    @Column(name = "no_hp")
    private String number;

    @Lob
    @Column(name = "image")
    private String image; // Untuk menyimpan gambar dalam bentuk byte array

    @Lob
    @Column(name = "image_detail")
    private String imageDetail;

    public JadwalTokoSales() {}

    public JadwalTokoSales(int jadwalToko_id, String name_toko, String address, String nomer_so, String provinsi, String kota, String kecamatan, String desa, String namaOwner, String number, String image, String imageDetail) {
        this.jadwalToko_id = jadwalToko_id;
        this.name_toko = name_toko;
        this.address = address;
        this.nomer_so = nomer_so;
        this.provinsi = provinsi;
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.desa = desa;
        this.namaOwner = namaOwner;
        this.number = number;
        this.image = image;
        this.imageDetail = imageDetail;
    }

    public String getNomer_so() {
        return nomer_so;
    }

    public void setNomer_so(String nomer_so) {
        this.nomer_so = nomer_so;
    }

    public String getImageDetail() {
        return imageDetail;
    }

    public void setImageDetail(String imageDetail) {
        this.imageDetail = imageDetail;
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

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getNamaOwner() {
        return namaOwner;
    }

    public void setNamaOwner(String namaOwner) {
        this.namaOwner = namaOwner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
