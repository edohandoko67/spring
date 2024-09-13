package com.rs.user.message;

import javax.persistence.*;

@Entity
@Table(name = "PesanUser")
public class PesanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPesan")
    private Integer idPesan;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    public Integer getIdPesan() {
        return idPesan;
    }

    public void setIdPesan(Integer idPesan) {
        this.idPesan = idPesan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
