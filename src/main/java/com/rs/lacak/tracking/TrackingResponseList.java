package com.rs.lacak.tracking;

import com.rs.auth.MetaData;

import java.util.List;

public class TrackingResponseList {
    private MetaData metaData;
    private List<TrackingInfo> response;

    public TrackingResponseList(MetaData metaData, List<TrackingInfo> response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<TrackingInfo> getResponse() {
        return response;
    }

    public void setResponse(List<TrackingInfo> response) {
        this.response = response;
    }
}
