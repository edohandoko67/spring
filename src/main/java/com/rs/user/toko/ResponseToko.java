package com.rs.user.toko;

import com.rs.auth.MetaData;

import java.util.List;

public class ResponseToko {
    private MetaData metaData;
    private List<JadwalTokoSalesInfo> responseToko;

    public ResponseToko(MetaData metaData, List<JadwalTokoSalesInfo> responseToko) {
        this.metaData = metaData;
        this.responseToko = responseToko;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<JadwalTokoSalesInfo> getResponseToko() {
        return responseToko;
    }

    public void setResponseToko(List<JadwalTokoSalesInfo> responseToko) {
        this.responseToko = responseToko;
    }
}
