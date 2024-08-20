package com.rs.user.jadwalSales;

import com.rs.auth.MetaData;

public class JadwalSalesResponse {
    private MetaData metaData;
    private JadwalSales response;

    public JadwalSalesResponse(MetaData metaData, JadwalSales response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public JadwalSales getResponse() {
        return response;
    }

    public void setResponse(JadwalSales response) {
        this.response = response;
    }
}
