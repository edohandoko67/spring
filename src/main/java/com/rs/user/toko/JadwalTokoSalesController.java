package com.rs.user.toko;

import com.rs.auth.MetaData;
import com.rs.user.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Toko")
public class JadwalTokoSalesController {

    @Autowired
    JadwalTokoSalesRepository jadwalTokoSalesRepository;

    @PostMapping("/create")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> create(
            @RequestParam("name_toko") @NotNull String nameToko,
            @RequestParam("address") @NotNull String address,
            @RequestParam("image") @NotNull MultipartFile image
            ) {
        try {
            // Validasi ukuran file jika diperlukan
            if (image.getSize() > 5_000_000) { // 5 MB limit
                MetaData metaData = new MetaData(400, "error", "File size exceeds limit");
                ErrorResponse errorResponse = new ErrorResponse(metaData);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            byte[] imageBytes = image.getBytes();
            JadwalTokoSales jadwalTokoSales = new JadwalTokoSales();
            jadwalTokoSales.setName_toko(nameToko);
            jadwalTokoSales.setAddress(address);
            jadwalTokoSales.setImage(Arrays.toString(imageBytes)); //convert to array
            JadwalTokoSales savedJadwalToko = jadwalTokoSalesRepository.save(jadwalTokoSales);
            URI newJadwalTokoURI = URI.create("/Toko/"+savedJadwalToko.getJadwalToko_id());
            MetaData metaData = new MetaData(201, "Berhasil", "Berhasil menambah data");
            Base64.getEncoder().encodeToString(imageBytes); //convert to base64
            JadwalTokoSales response = new JadwalTokoSales(
                    savedJadwalToko.getJadwalToko_id(),
                    savedJadwalToko.getName_toko(),
                    savedJadwalToko.getAddress(),
                    Arrays.toString(imageBytes)
            );
            JadwalTokoSalesResponse apiResponse = new JadwalTokoSalesResponse(metaData, response);
            return ResponseEntity.created(newJadwalTokoURI).body(apiResponse);
        } catch (Exception e) {
            MetaData metaData = new MetaData(500, "error", "Internal server error");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseToko> getListToko() {
        List<JadwalTokoSales> jadwalTokoSalesList = jadwalTokoSalesRepository.findAll();
        List<JadwalTokoSalesInfo> jadwalTokoSalesInfo = jadwalTokoSalesList.stream()
                .map(p -> {
                    byte[] imageBytes = p.getImage().getBytes();
                    String base64 = Base64.getEncoder().encodeToString(imageBytes);
                    return new JadwalTokoSalesInfo(
                            p.getJadwalToko_id(),
                            p.getName_toko(),
                            p.getAddress(),
                            Arrays.toString(imageBytes)
                    );
                })
                .collect(Collectors.toList());
        MetaData metaData = new MetaData(
                HttpStatus.OK.value(), "Success", "Mendapatkan data"
        );
        ResponseToko apiRespinse = new ResponseToko(metaData, jadwalTokoSalesInfo);
        return new ResponseEntity<>(apiRespinse, HttpStatus.OK);
    }
}
