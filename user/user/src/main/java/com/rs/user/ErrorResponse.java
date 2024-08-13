package com.rs.user;

import com.rs.auth.MetaData;

import java.util.Collections;
import java.util.List;

public class ErrorResponse {
    private MetaData metaData;
    private List<String> response;

    public ErrorResponse() {}

    public ErrorResponse(MetaData metaData) {
        this.metaData = metaData;
        this.response = Collections.emptyList();
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }
}
