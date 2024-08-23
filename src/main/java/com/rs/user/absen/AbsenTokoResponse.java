package com.rs.user.absen;

import com.rs.auth.MetaData;

public class AbsenTokoResponse {
    private MetaData metaData;
    private AbsenToko response;

    public AbsenTokoResponse(MetaData metaData, AbsenToko response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public AbsenToko getResponse() {
        return response;
    }

    public void setResponse(AbsenToko response) {
        this.response = response;
    }
}
