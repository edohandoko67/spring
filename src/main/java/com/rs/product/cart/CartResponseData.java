package com.rs.product.cart;

import com.rs.auth.MetaData;

import java.util.List;

public class CartResponseData {
    private MetaData metaData;
    List<CartInfo> response;

    public CartResponseData(MetaData metaData, List<CartInfo> response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<CartInfo> getResponse() {
        return response;
    }

    public void setResponse(List<CartInfo> response) {
        this.response = response;
    }
}
