package com.rs.product.stok;

import com.rs.auth.MetaData;

import java.util.List;

public class StockResponse {
    private MetaData metaData;

    private List<StockInfo> response;

    public StockResponse(MetaData metaData, List<StockInfo> response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<StockInfo> getResponse() {
        return response;
    }

    public void setResponse(List<StockInfo> response) {
        this.response = response;
    }
}
