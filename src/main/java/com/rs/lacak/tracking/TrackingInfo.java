package com.rs.lacak.tracking;

import com.rs.lacak.tracking.history.HistoryStatus;

import java.time.LocalDateTime;
import java.util.List;


public class TrackingInfo {
    private int idTracking;
    private String status;
    private String noResi;
    private LocalDateTime timestamp;
    private Boolean checkingData;
    private Boolean checkingDataAfter;
    private List<HistoryStatus> histories;

    public TrackingInfo(int idTracking, String status, String noResi, LocalDateTime timestamp, Boolean checkingData, Boolean checkingDataAfter, List<HistoryStatus> histories) {
        this.idTracking = idTracking;
        this.status = status;
        this.noResi = noResi;
        this.timestamp = timestamp;
        this.checkingData = checkingData;
        this.checkingDataAfter = checkingDataAfter;
        this.histories = histories;
    }

    public int getIdTracking() {
        return idTracking;
    }

    public void setIdTracking(int idTracking) {
        this.idTracking = idTracking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoResi() {
        return noResi;
    }

    public void setNoResi(String noResi) {
        this.noResi = noResi;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getCheckingData() {
        return checkingData;
    }

    public void setCheckingData(Boolean checkingData) {
        this.checkingData = checkingData;
    }

    public Boolean getCheckingDataAfter() {
        return checkingDataAfter;
    }

    public void setCheckingDataAfter(Boolean checkingDataAfter) {
        this.checkingDataAfter = checkingDataAfter;
    }

    public List<HistoryStatus> getHistories() {
        return histories;
    }

    public void setHistories(List<HistoryStatus> histories) {
        this.histories = histories;
    }
}
