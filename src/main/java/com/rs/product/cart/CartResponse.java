package com.rs.product.cart;

import com.rs.auth.MetaData;

import java.util.List;

public class CartResponse {
    private MetaData metaData;
    private List<Cart> response;

    public CartResponse(MetaData metaData, List<Cart> response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<Cart> getResponse() {
        return response;
    }

    public void setResponse(List<Cart> response) {
        this.response = response;
    }
}
