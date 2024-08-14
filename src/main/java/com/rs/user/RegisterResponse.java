package com.rs.user;

import com.rs.auth.MetaData;

public class RegisterResponse {
    private MetaData metaData;
    private RegistrationInfo response;

    public void RegistrationInfo() {}

    public RegisterResponse(MetaData metaData, RegistrationInfo response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public RegistrationInfo getResponse() {
        return response;
    }

    public void setResponse(RegistrationInfo response) {
        this.response = response;
    }
}
