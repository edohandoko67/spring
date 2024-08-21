package com.rs.user.toko;

import com.rs.auth.MetaData;

import java.util.List;

public class ResponseToko {
    private MetaData metaData;
    private List<JadwalTokoSalesInfo> response;

    public ResponseToko(MetaData metaData, List<JadwalTokoSalesInfo> response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<JadwalTokoSalesInfo> getResponse() {
        return response;
    }

    public void setResponse(List<JadwalTokoSalesInfo> response) {
        this.response = response;
    }
}
