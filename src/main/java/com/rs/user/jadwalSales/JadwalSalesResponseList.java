package com.rs.user.jadwalSales;

import com.rs.auth.MetaData;

import java.util.List;

public class JadwalSalesResponseList {
    private MetaData metaData;
    private List<JadwalSales> response;

    public JadwalSalesResponseList(MetaData metaData, List<JadwalSales> response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<JadwalSales> getResponse() {
        return response;
    }

    public void setResponse(List<JadwalSales> response) {
        this.response = response;
    }
}
