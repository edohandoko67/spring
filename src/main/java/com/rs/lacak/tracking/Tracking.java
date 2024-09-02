package com.rs.lacak.tracking;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rs.lacak.tracking.history.HistoryStatus;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "Tracking")
public class Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tracking")
    private int idTracking;

    private String status;

    @Column(name = "no_resi")
    @Length(max = 255)
    private String noResi;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Column(name = "check_column")
    private Boolean checkingData;

    @Column(name = "check_column_after")
    private Boolean checkingDataAfter;

    @OneToMany(mappedBy = "tracking", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<HistoryStatus> histories;

    public Tracking() {}

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getNoResi() {
        return noResi;
    }

    public void setNoResi(String noResi) {
        this.noResi = noResi;
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
