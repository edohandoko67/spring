package com.rs.product;

import com.rs.auth.MetaData;

import java.util.List;

public class ProductResponse {
    private MetaData metaData;
    private List<ProductInfo> response;

    public void ProductResponse() {}

    public ProductResponse(MetaData metaData, List<ProductInfo> response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<ProductInfo> getResponse() {
        return response;
    }

    public void setResponse(List<ProductInfo> response) {
        this.response = response;
    }

}
