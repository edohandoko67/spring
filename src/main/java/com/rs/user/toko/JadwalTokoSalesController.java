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
import javax.validation.Valid;
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
    private static final String IMAGE_DIRECTORY = "src/main/resources/static/path/image/";
    private static final String IMAGE_DETAIL_DIRECTORY = "src/main/resources/static/path/image_detail/";

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
            @RequestParam("no_hp") @NotNull String no_hp,
            @RequestParam("image_detail") @NotNull MultipartFile image_detail
            ) {
        try {
            // Validate file sizes
            if (image.getSize() > 5_000_000) { // 5 MB limit
                MetaData metaData = new MetaData(400, "error", "Image file size exceeds limit");
                ErrorResponse errorResponse = new ErrorResponse(metaData);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            if (image_detail.getSize() > 5_000_000) { // 5 MB limit
                MetaData metaData = new MetaData(400, "error", "Detail image file size exceeds limit");
                ErrorResponse errorResponse = new ErrorResponse(metaData);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            // Ensure directories exist
            createDirectoryIfNotExists(IMAGE_DIRECTORY);
            createDirectoryIfNotExists(IMAGE_DETAIL_DIRECTORY);

            // Handle image upload
            String imageFileName = "image_" + System.currentTimeMillis() + ".png";
            File imageFile = new File(IMAGE_DIRECTORY + imageFileName);
            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
            ImageIO.write(bufferedImage, "png", imageFile);

            // Handle detail image upload
            String imageDetailFileName = "detail_" + System.currentTimeMillis() + ".png";
            File imageDetailFile = new File(IMAGE_DETAIL_DIRECTORY + imageDetailFileName);
            BufferedImage bufferedImageDetail = ImageIO.read(image_detail.getInputStream());
            ImageIO.write(bufferedImageDetail, "png", imageDetailFile);

            // Generate URLs or paths to access the images
            String imageUrl = "path/image/" + imageFileName;
            String imageDetailUrl = "path/image_detail/" + imageDetailFileName;

            // Create and save the Toko entity
            JadwalTokoSales jadwalTokoSales = new JadwalTokoSales();
            jadwalTokoSales.setName_toko(nameToko);
            jadwalTokoSales.setAddress(address);
            jadwalTokoSales.setImage(imageUrl);
            jadwalTokoSales.setProvinsi(provinsi);
            jadwalTokoSales.setNomer_so(nomer_so);
            jadwalTokoSales.setKota(kota);
            jadwalTokoSales.setKecamatan(kecamatan);
            jadwalTokoSales.setDesa(desa);
            jadwalTokoSales.setNamaOwner(owner);
            jadwalTokoSales.setNumber(no_hp);
            jadwalTokoSales.setImageDetail(imageDetailUrl);

            JadwalTokoSales savedJadwalToko = jadwalTokoSalesRepository.save(jadwalTokoSales);
            URI newJadwalTokoURI = URI.create("/Toko/" + savedJadwalToko.getJadwalToko_id());

            MetaData metaData = new MetaData(201, "success", "Successfully added data");
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
                    savedJadwalToko.getImage(),
                    savedJadwalToko.getImageDetail()
            );
            JadwalTokoSalesResponse apiResponse = new JadwalTokoSalesResponse(metaData, response);
            return ResponseEntity.created(newJadwalTokoURI).body(apiResponse);
        } catch (IOException e) {
            MetaData metaData = new MetaData(500, "error", "Internal server error");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
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
                            p.getImage(),
                            p.getImageDetail(),
                            p.getUserInfo().getName()
                    );
                })
                .collect(Collectors.toList());
        MetaData metaData = new MetaData(
                HttpStatus.OK.value(), "Success", "Mendapatkan data"
        );
        ResponseToko apiRespinse = new ResponseToko(metaData, jadwalTokoSalesInfo);
        return new ResponseEntity<>(apiRespinse, HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<ResponseToko> listTokoSalesById(@RequestBody @Valid TokoRequest tokoRequest) {
        int userId = tokoRequest.getUserId();

        List<JadwalTokoSales> jadwalTokoSales = jadwalTokoSalesRepository.findByUserInfoId(userId);

        if (jadwalTokoSales.isEmpty()) {
            throw new RuntimeException("Toko not found with userId: " + userId);
        }

        List<JadwalTokoSalesInfo> jadwalTokoSalesInfo = jadwalTokoSales.stream()
                .map(sales -> new JadwalTokoSalesInfo(
                        sales.getJadwalToko_id(),
                        sales.getName_toko(),
                        sales.getAddress(),
                        sales.getNomer_so(),
                        sales.getProvinsi(),
                        sales.getKota(),
                        sales.getKecamatan(),
                        sales.getDesa(),
                        sales.getNamaOwner(),
                        sales.getNumber(),
                        sales.getImage(),
                        sales.getImageDetail(),
                        sales.getUserInfo().getName()
                )).collect(Collectors.toList());
        MetaData metaData = new MetaData(200, "Berhasil", "Mendapatkan data");
        ResponseToko apiResponse = new ResponseToko(metaData, jadwalTokoSalesInfo);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
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

    @PutMapping("/update/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    public ResponseEntity<?> updateTokoById(@PathVariable Integer id, @RequestBody JadwalTokoSales updated) {
        try{
            JadwalTokoSales jadwalTokoSales = jadwalTokoSalesRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not exist with id: " +id));
            jadwalTokoSales.setAddress(updated.getAddress());
            jadwalTokoSales.setNumber(updated.getNumber());
            jadwalTokoSales.setKota(updated.getKota());
            jadwalTokoSales.setKecamatan(updated.getKecamatan());
            jadwalTokoSales.setNomer_so(updated.getNomer_so());
            jadwalTokoSales.setDesa(updated.getDesa());
            jadwalTokoSales.setName_toko(updated.getName_toko());
            JadwalTokoSales savedJadwalTokoSales = jadwalTokoSalesRepository.save(jadwalTokoSales);
            URI newJadwalTokoSalesURI = URI.create("/Toko/"+savedJadwalTokoSales.getJadwalToko_id());
            return ResponseEntity.created(newJadwalTokoSalesURI).body(savedJadwalTokoSales);
        } catch (Exception e) {
            MetaData metaData = new MetaData(500, "error", "Internal server error");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

    }

    private void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs(); // Create directories if they don't exist
            if (!created) {
                throw new RuntimeException("Failed to create directory: " + directoryPath);
            }
        }
    }
}
