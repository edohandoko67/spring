package com.rs.product.cart;

import com.rs.auth.MetaData;

import java.util.List;

public class CartResponse {
    private MetaData metaData;
    private List<CartInfo> response;

    public CartResponse(MetaData metaData, List<CartInfo> response) {
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
