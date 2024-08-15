package com.rs.product.satuan;

import com.rs.auth.MetaData;
import java.util.List;

public class SatuanResponse {
    private MetaData metaData;
    private List<SatuanProductInfo> response;

    public void SatuanResponse() {}

    public SatuanResponse(MetaData metaData, List<SatuanProductInfo> response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<SatuanProductInfo> getResponse() {
        return response;
    }

    public void setResponse(List<SatuanProductInfo> response) {
        this.response = response;
    }
}
