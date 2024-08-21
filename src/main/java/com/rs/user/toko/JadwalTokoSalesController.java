package com.rs.user.toko;

import com.rs.auth.MetaData;
import com.rs.product.Product;
import com.rs.product.ProductNotFoundException;
import com.rs.user.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.imageio.ImageIO;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/Toko")
public class JadwalTokoSalesController {

    @Autowired
    JadwalTokoSalesRepository jadwalTokoSalesRepository;
    String imageDirectory = "src/main/resources/static/path/image/";

    @PostMapping("/create")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> create(
            @RequestParam("name_toko") @NotNull String nameToko,
            @RequestParam("address") @NotNull String address,
            @RequestParam("image") @NotNull MultipartFile image,
            @RequestParam("nomor_so") @NotNull String nomer_so,
            @RequestParam("provinsi") @NotNull String provinsi,
            @RequestParam("kota") @NotNull String kota,
            @RequestParam("kecamatan") @NotNull String kecamatan,
            @RequestParam("desa") @NotNull String desa,
            @RequestParam("owner") @NotNull String owner,
            @RequestParam("no_hp") @NotNull String no_hp
            ) {
        try {
            // Validasi ukuran file jika diperlukan
            if (image.getSize() > 5_000_000) { // 5 MB limit
                MetaData metaData = new MetaData(400, "error", "File size exceeds limit");
                ErrorResponse errorResponse = new ErrorResponse(metaData);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            //memeriksa apakah folder sudah ada, jika belum mkdir akan create
            File directory = new File(imageDirectory);
            if (!directory.exists()) {
                boolean created = directory.mkdirs(); // Create directories if they don't exist
                if (!created) {
                    throw new RuntimeException("Failed to create directory: " + imageDirectory);
                }
            }
            // Convert MultipartFile to BufferedImage
            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());

            // Define the file path and name
            String fileName = "image_" + System.currentTimeMillis() + ".png";
            File file = new File(imageDirectory + fileName);

            // Write the BufferedImage to file in PNG format
            ImageIO.write(bufferedImage, "png", file);

            // Generate a URL or path to access the image
            String imageUrl = "path/image/" + fileName; // Adjust URL according to your setup
            JadwalTokoSales jadwalTokoSales = new JadwalTokoSales();
            jadwalTokoSales.setName_toko(nameToko);
            jadwalTokoSales.setAddress(address);
            jadwalTokoSales.setImage(imageUrl);//convert to array
            jadwalTokoSales.setProvinsi(provinsi);
            jadwalTokoSales.setNomer_so(nomer_so);
            jadwalTokoSales.setKota(kota);
            jadwalTokoSales.setKecamatan(kecamatan);
            jadwalTokoSales.setDesa(desa);
            jadwalTokoSales.setNamaOwner(owner);
            jadwalTokoSales.setNumber(no_hp);

            JadwalTokoSales savedJadwalToko = jadwalTokoSalesRepository.save(jadwalTokoSales);
            URI newJadwalTokoURI = URI.create("/Toko/"+savedJadwalToko.getJadwalToko_id());
            MetaData metaData = new MetaData(201, "Berhasil", "Berhasil menambah data");

            JadwalTokoSales response = new JadwalTokoSales(
                    savedJadwalToko.getJadwalToko_id(),
                    savedJadwalToko.getName_toko(),
                    savedJadwalToko.getAddress(),
                    savedJadwalToko.getNomer_so(),
                    savedJadwalToko.getProvinsi(),
                    savedJadwalToko.getKota(),
                    savedJadwalToko.getKecamatan(),
                    savedJadwalToko.getDesa(),
                    savedJadwalToko.getNamaOwner(),
                    savedJadwalToko.getNumber(),
                    savedJadwalToko.getImage()
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
                    // Define the file path and name
                    String fileName = "image_" + System.currentTimeMillis() + ".png";

                    // Generate a URL or path to access the image
                    String imageUrl = "path/image/" + fileName; // Adjust URL according to your setup
                    return new JadwalTokoSalesInfo(
                            p.getJadwalToko_id(),
                            p.getName_toko(),
                            p.getAddress(),
                            p.getNomer_so(),
                            p.getProvinsi(),
                            p.getKota(),
                            p.getKecamatan(),
                            p.getDesa(),
                            p.getNamaOwner(),
                            p.getNumber(),
                            p.getImage()
                    );
                })
                .collect(Collectors.toList());
        MetaData metaData = new MetaData(
                HttpStatus.OK.value(), "Success", "Mendapatkan data"
        );
        ResponseToko apiRespinse = new ResponseToko(metaData, jadwalTokoSalesInfo);
        return new ResponseEntity<>(apiRespinse, HttpStatus.OK);
    }

    @GetMapping("/{jadwal_toko_id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    public ResponseEntity<?> getTokoById(@PathVariable("jadwal_toko_id") Integer id) {
        Optional<JadwalTokoSales> tokoOptional = jadwalTokoSalesRepository.findById(id);
        if (tokoOptional.isPresent()) {
            return ResponseEntity.ok(tokoOptional.get());
        }
        MetaData metaData = new MetaData(404, "error", "Data tidak ditemukan");
        ErrorResponse errorResponse = new ErrorResponse(metaData);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
