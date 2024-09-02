package com.rs.lacak.tracking.history;

import java.time.LocalDateTime;

public class HistoryStatusInfo {
    private int idHistory;
    private String status;
    private LocalDateTime localDateTime;

    public HistoryStatusInfo(int idHistory, String status, LocalDateTime localDateTime) {
        this.idHistory = idHistory;
        this.status = status;
        this.localDateTime = localDateTime;
    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
