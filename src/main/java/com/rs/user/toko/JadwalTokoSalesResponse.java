package com.rs.user.toko;

import com.rs.auth.MetaData;

public class JadwalTokoSalesResponse {
    private MetaData metaData;
    private JadwalTokoSales responseToko;

    public JadwalTokoSalesResponse(MetaData metaData, JadwalTokoSales responseToko) {
        this.metaData = metaData;
        this.responseToko = responseToko;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public JadwalTokoSales getResponseToko() {
        return responseToko;
    }

    public void setResponseToko(JadwalTokoSales responseToko) {
        this.responseToko = responseToko;
    }
}
