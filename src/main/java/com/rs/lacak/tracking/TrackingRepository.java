package com.rs.lacak.tracking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrackingRepository extends JpaRepository<Tracking, Integer> {
    @Query("SELECT t FROM Tracking t WHERE t.product.name = :productName")
    List<Tracking> findTracking();
}
