package com.rs.product.satuan;

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
@RequestMapping("/satuan_product")
public class SatuanController {
    @Autowired
    private SatuanRepository satuanRepository;

    @GetMapping("/list")
    //@RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    public ResponseEntity<SatuanResponse> listSatuan() {
        List<SatuanProduct> satuanProductList = satuanRepository.findAll();
        List<SatuanProductInfo> satuanProductsInfo = satuanProductList.stream()
                .map(p -> new SatuanProductInfo(
                        p.getId_satuan(),
                        p.getSatuan_product(),
                        p.getKode_product()))
                .collect(Collectors.toList());
        MetaData metaData = new MetaData(
                HttpStatus.OK.value(),
                "Success",
                "Satuan retrieved successfully"
        );
        SatuanResponse response = new SatuanResponse(metaData,satuanProductsInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> createProduct(@RequestBody @Valid SatuanProduct newSatuanProduct) {
        try {
            SatuanProduct savedSatuanProductKode = satuanRepository.save(newSatuanProduct);
            URI newSatuanURI = URI.create("/satuan_product"+savedSatuanProductKode.getId_satuan());
            MetaData metaData = new MetaData(201, "Success", "Berhasil menambah data");
            SatuanProductInfo responseProduct = new SatuanProductInfo(
                    savedSatuanProductKode.getId_satuan(),
                    savedSatuanProductKode.getSatuan_product(),
                    savedSatuanProductKode.getKode_product()
            );
            SatuanResponseData apiResponse = new SatuanResponseData(metaData, responseProduct);
            return ResponseEntity.created(newSatuanURI).body(apiResponse);
        } catch (Exception e) {
            MetaData metaData = new MetaData(500, "Gagal", "Internal server error");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}


