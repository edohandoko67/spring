package com.rs.lacak.tracking;

import com.rs.auth.MetaData;

public class TrackingResponse {
    private MetaData metaData;
    private TrackingInfo response;

    public TrackingResponse(MetaData metaData, TrackingInfo response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public TrackingInfo getResponse() {
        return response;
    }

    public void setResponse(TrackingInfo response) {
        this.response = response;
    }
}
