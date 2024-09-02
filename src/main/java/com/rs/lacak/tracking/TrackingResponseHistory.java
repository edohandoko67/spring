package com.rs.lacak.tracking;

import com.rs.auth.MetaData;
import com.rs.lacak.tracking.history.HistoryStatus;
import com.rs.lacak.tracking.history.HistoryStatusInfo;

import java.util.List;

public class TrackingResponseHistory {
    private MetaData metaData;
    private TrackingInfo response;

    //private List<HistoryStatus> histories;

    public TrackingResponseHistory(MetaData metaData, TrackingInfo response) {
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

//    public List<HistoryStatus> getHistories() {
//        return histories;
//    }
//
//    public void setHistories(List<HistoryStatus> histories) {
//        this.histories = histories;
//    }
}
