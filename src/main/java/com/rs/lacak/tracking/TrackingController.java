package com.rs.lacak.tracking;

import com.rs.auth.MetaData;
import com.rs.product.satuan.SatuanProduct;
import com.rs.user.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    @Autowired
    TrackingRepository trackingRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createTracking(@RequestBody @Valid Tracking trackingProduct) {
        try {
            Tracking tracking = trackingRepository.save(trackingProduct);
            URI newTrackingURI = URI.create("/tracking"+tracking.getId_tracking());
            MetaData metaData = new MetaData(201, "Success", "Berhasil menambahkan data");
            TrackingInfo responseTracking = new TrackingInfo(
                    tracking.getId_tracking(),
                    tracking.getProduct().getName(),
                    tracking.getStatus(),
                    tracking.getNo_resi(),
                    tracking.getTimestamp()
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
                .map(p -> new TrackingInfo(
                        p.getId_tracking(),
                        p.getProduct().getName(),
                        p.getStatus(),
                        p.getNo_resi(),
                        p.getTimestamp()))
                .collect(Collectors.toList());
        MetaData metaData = new MetaData(
                HttpStatus.OK.value(),
                "Success",
                "Satuan retrieved successfully"
        );
        TrackingResponseList apiResponse = new TrackingResponseList(metaData, trackingInfo);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
