package com.rs.lacak.tracking.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryRespository extends JpaRepository<HistoryStatus, Integer> {
    List<HistoryStatus> findAll();
    @Query("SELECT h FROM HistoryStatus h WHERE h.tracking.idTracking = :idTracking")
    List<HistoryStatus> findHistoriesByIdTracking(int idTracking);
}
