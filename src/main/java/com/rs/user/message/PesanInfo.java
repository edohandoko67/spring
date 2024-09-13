package com.rs.user.message;

public class PesanInfo {
    private Integer idPesan;
    private String title;
    private String message;

    public PesanInfo() {}

    public PesanInfo(Integer idPesan, String title, String message) {
        this.idPesan = idPesan;
        this.title = title;
        this.message = message;
    }

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
