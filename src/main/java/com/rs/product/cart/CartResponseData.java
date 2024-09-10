package com.rs.product.cart;

import com.rs.auth.MetaData;

import java.util.List;

public class CartResponseData {
    private MetaData metaData;
    CartRequest response;

    public CartResponseData(MetaData metaData, CartRequest response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public CartRequest getResponse() {
        return response;
    }

    public void setResponse(CartRequest response) {
        this.response = response;
    }
}
