package com.rs.lacak.tracking.history;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rs.lacak.tracking.Tracking;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_status")
public class HistoryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history")
    private int idHistory;

    @ManyToOne
    @JoinColumn(name = "id_tracking", nullable = false)
    @JsonBackReference
    private Tracking tracking;

    @Column(name = "status_history")
    private String status;

    @Column(name = "check_history")
    private Boolean checkHistory;

    @Column(name = "timestamp")
    private LocalDateTime localDateTime;

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public Tracking getTracking() {
        return tracking;
    }

    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getCheckHistory() {
        return checkHistory;
    }

    public void setCheckHistory(Boolean checkHistory) {
        this.checkHistory = checkHistory;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
