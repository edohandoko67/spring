package com.rs.lacak.tracking;

import com.rs.auth.MetaData;
import com.rs.lacak.tracking.history.HistoryStatus;
import com.rs.lacak.tracking.history.HistoryStatusInfo;
import com.rs.user.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    @Autowired
    TrackingRepository trackingRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @PostMapping("/create")
    public ResponseEntity<?> createTracking(@RequestBody @Valid Tracking trackingProduct) {
        try {
            Tracking tracking = trackingRepository.save(trackingProduct);
            URI newTrackingURI = URI.create("/tracking" + tracking.getIdTracking());
            String formattedTimestamp = tracking.getTimestamp().format(FORMATTER);
            List<HistoryStatus> histories = tracking.getHistories();
            MetaData metaData = new MetaData(201, "Success", "Berhasil menambahkan data");
            TrackingInfo responseTracking = new TrackingInfo(
                    tracking.getIdTracking(),
                    tracking.getStatus(),
                    tracking.getNoResi(),
                    LocalDateTime.parse(formattedTimestamp, FORMATTER),
                    tracking.getCheckingData(),
                    tracking.getCheckingDataAfter(),
                    histories
            );
            TrackingResponse apiResponse = new TrackingResponse(metaData, responseTracking);
            return ResponseEntity.created(newTrackingURI).body(apiResponse);
        } catch (Exception e) {
            MetaData metaData = new MetaData(400, "Error", "Bad Request");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<TrackingResponseList> listKurir() {
        List<Tracking> trackingResponseList = trackingRepository.findAll();
        List<TrackingInfo> trackingInfo = trackingResponseList.stream()
                .map(tracking -> {
                    List<HistoryStatus> histories = tracking.getHistories();

                    // Construct and return a TrackingInfo object
                    return new TrackingInfo(
                            tracking.getIdTracking(),
                            tracking.getStatus(),
                            tracking.getNoResi(),
                            tracking.getTimestamp(),
                            tracking.getCheckingData(),
                            tracking.getCheckingDataAfter(),
                            histories
                    );
                })
                .collect(Collectors.toList());
        MetaData metaData = new MetaData(
                HttpStatus.OK.value(),
                "Success",
                "Satuan retrieved successfully"
        );
        TrackingResponseList apiResponse = new TrackingResponseList(metaData, trackingInfo);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/noResi")
    public ResponseEntity<?> listNoResi(@RequestBody @Valid Tracking request) {
        try {
            Optional<Tracking> trackingOptional = trackingRepository.findByNoResi(request.getNoResi());

            if (trackingOptional.isPresent()) {
                Tracking tracking = trackingOptional.get();
                List<HistoryStatus> histories = trackingRepository.findHistoriesByIdTracking(tracking.getIdTracking());

                MetaData metaData = new MetaData(200, "Success", "Data found");
                TrackingInfo trackingInfo = new TrackingInfo(
                        tracking.getIdTracking(),
                        tracking.getStatus(),
                        tracking.getNoResi(),
                        tracking.getTimestamp(),
                        tracking.getCheckingData(),
                        tracking.getCheckingDataAfter(),
                        histories // Mengirimkan list HistoryStatus sebagai bagian dari TrackingInfo
                );
                TrackingResponseHistory apiResponse = new TrackingResponseHistory(metaData, trackingInfo);
                return ResponseEntity.ok(apiResponse);
            } else {
                MetaData metaData = new MetaData(404, "Not Found", "Data not found");
                ErrorResponse errorResponse = new ErrorResponse(metaData);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception e) {
            MetaData metaData = new MetaData(500, "Error", "Internal Server Error");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


}
