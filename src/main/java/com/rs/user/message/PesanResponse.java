package com.rs.user.message;

import com.rs.auth.MetaData;

import java.util.List;

public class PesanResponse {
    private MetaData metaData;
    List<PesanInfo> response;

    public PesanResponse(MetaData metaData, List<PesanInfo> response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<PesanInfo> getResponse() {
        return response;
    }

    public void setResponse(List<PesanInfo> response) {
        this.response = response;
    }
}
