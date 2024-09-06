package com.rs.product.stok.detail;

import com.rs.auth.MetaData;

import java.util.List;

public class DetailStockInfoResponse {
    private MetaData metaData;
    private List<DetailStockInfo> response;

    public DetailStockInfoResponse(MetaData metaData, List<DetailStockInfo> response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<DetailStockInfo> getResponse() {
        return response;
    }

    public void setResponse(List<DetailStockInfo> response) {
        this.response = response;
    }
}
