package com.rs.lacak.tracking;

import com.rs.lacak.tracking.history.HistoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TrackingRepository extends JpaRepository<Tracking, Integer> {
//    @Query("SELECT t FROM Tracking t WHERE t.product.name = :productName")
//    List<Tracking> findTracking();

//    @Query("SELECT t FROM Tracking t " +
//            "JOIN t.jadwalSales j " +
//            "WHERE t.noResi = :noResi AND j.nameSales = :nameKurir")
//    Optional<Tracking> findByNoResiKurir(String noResi, String nameSales);

    @Query("SELECT t FROM Tracking t WHERE t.noResi = :noResi")
    Optional<Tracking> findByNoResi(String noResi);

    @Query("SELECT h FROM HistoryStatus h WHERE h.tracking.idTracking = :idTracking")
    List<HistoryStatus> findHistoriesByIdTracking(int idTracking);
}
