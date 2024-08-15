package com.rs.product;

import com.rs.auth.MetaData;

import java.util.List;

public class ProductResponseData {
    private MetaData metaData;
    private ProductInfo response;

    public void ProductResponse() {}

    public ProductResponseData(MetaData metaData, ProductInfo response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public ProductInfo getResponse() {
        return response;
    }

    public void setResponse(ProductInfo response) {
        this.response = response;
    }

}
