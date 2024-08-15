package com.rs.product.satuan;

import com.rs.auth.MetaData;

public class SatuanResponseData {
    private MetaData metaData;
    private SatuanProductInfo response;

    public void SatuanResponseData() {}

    public SatuanResponseData(MetaData metaData, SatuanProductInfo response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public SatuanProductInfo getResponse() {
        return response;
    }

    public void setResponse(SatuanProductInfo response) {
        this.response = response;
    }
}
