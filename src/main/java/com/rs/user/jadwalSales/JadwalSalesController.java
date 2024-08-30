package com.rs.user.jadwalSales;

import com.rs.auth.MetaData;
import com.rs.user.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jadwal_sales")
public class JadwalSalesController {

    @Autowired
    private JadwalSalesRepository jadwalSalesRepository;

    @PostMapping("/create")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> createJadwalSales(@RequestBody @Valid JadwalSales jadwalSales) {
        try {
            JadwalSales savedJadwal = jadwalSalesRepository.save(jadwalSales);
            URI newJadwalSalesURI = URI.create("/jadwal_sales/"+savedJadwal.getJadwalsales_id());
            MetaData metaData = new MetaData(201, "Berhasil", "Berhasil menambahkan data");

            // Cek kondisi dan ubah status jika perlu
            String status = savedJadwal.getStatus();
            if ("1".equals(status)) {
                status = "sudah";
            } else if ("0".equals(status)){
                status = "belum";
            } else {
                status = "";
            }

            JadwalSales responseData = new JadwalSales(
                    savedJadwal.getJadwalsales_id(),
                    savedJadwal.getNameSales(),
                    savedJadwal.getKode(),
                    savedJadwal.getName_store(),
                    savedJadwal.getCreated_at(),
                    status
            );
            JadwalSalesResponse apiResponse = new JadwalSalesResponse(metaData, responseData);
            return ResponseEntity.created(newJadwalSalesURI).body(apiResponse);
        } catch (Exception e) {
            MetaData metaData = new MetaData(500, "error", "Internal server error");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listJadwalSales() {
        try {
            List<JadwalSales> jadwalSalesList = jadwalSalesRepository.findAll();
            List<JadwalSales> jadwalSalesInfo = jadwalSalesList.stream().collect(Collectors.toList());
            MetaData metaData = new MetaData(200, "Ok", "Berhasil mendapatkan data");
            JadwalSalesResponseList response = new JadwalSalesResponseList(metaData, jadwalSalesInfo);
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            MetaData metaData = new MetaData(404, "error", "Not found");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

    }
}
