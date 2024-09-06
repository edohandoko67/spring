package com.rs.product.stok.detail;

import com.rs.auth.MetaData;

import java.util.List;

public class DetailStockInfoResponseData {
    private MetaData metaData;
    private DetailStockInfo response;

    public DetailStockInfoResponseData(MetaData metaData, DetailStockInfo response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public DetailStockInfo getResponse() {
        return response;
    }

    public void setResponse(DetailStockInfo response) {
        this.response = response;
    }
}
