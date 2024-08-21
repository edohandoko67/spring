package com.rs.user.toko;

import com.rs.auth.MetaData;

public class JadwalTokoSalesResponse {
    private MetaData metaData;
    private JadwalTokoSales response;

    public JadwalTokoSalesResponse(MetaData metaData, JadwalTokoSales response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public JadwalTokoSales getResponse() {
        return response;
    }

    public void setResponse(JadwalTokoSales response) {
        this.response = response;
    }
}
