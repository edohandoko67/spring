package com.rs.user.absen;

import com.rs.auth.MetaData;
import com.rs.user.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.imageio.ImageIO;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/absen_toko")
public class AbsenTokoController {

    @Autowired
    AbsenTokoRepository absenTokoRepository;

    private static final String IMAGE_DIRECTORY_FILE = "src/main/resources/static/path/image_absen_toko/";

    @PostMapping("/create")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> createAbsen(
            @RequestParam("image_absen") @Nullable MultipartFile image,
            @RequestParam("latitude") @NotNull double latitude,
            @RequestParam("longitude") @NotNull double longitude,
            @RequestParam("keterangan") @NotNull String keterangan
    ) {
        try {
//            if (image.getSize() > 5_000_000) { // 5 MB limit
//                MetaData metaData = new MetaData(400, "error", "Image file size exceeds limit");
//                ErrorResponse errorResponse = new ErrorResponse(metaData);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//            }

            createDirectoryIfNotExists(IMAGE_DIRECTORY_FILE);

            String imageFileName = "image_absen_" + System.currentTimeMillis() + ".png";
            File imageFile = new File(IMAGE_DIRECTORY_FILE + imageFileName);
            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
            ImageIO.write(bufferedImage, "png", imageFile);

            // Generate URL for the saved image
            String imageUrl = "/path/image_absen_toko/" + imageFileName;

            // Create AbsenToko object
            AbsenToko absenToko = new AbsenToko();
            absenToko.setImage(imageUrl);
            absenToko.setLatitude(latitude);
            absenToko.setLongitude(longitude);
            absenToko.setKeterangan(keterangan);

            // Save AbsenToko to the database
            AbsenToko savedAbsenToko = absenTokoRepository.save(absenToko);
            URI newAbsenTokoURI = URI.create("/absen_toko/" + savedAbsenToko.getAbsen_toko_id());

            // Prepare the response
            MetaData metaData = new MetaData(201, "Success", "Successfully added data");
            AbsenToko response = new AbsenToko(
                    savedAbsenToko.getAbsen_toko_id(),
                    savedAbsenToko.getKeterangan(),
                    savedAbsenToko.getImage(),
                    savedAbsenToko.getLatitude(),
                    savedAbsenToko.getLongitude()
            );
            AbsenTokoResponse apiResponse = new AbsenTokoResponse(metaData, response);
            return ResponseEntity.created(newAbsenTokoURI).body(apiResponse);
        } catch (IOException e) {
            e.printStackTrace();
            MetaData metaData = new MetaData(400, "error", "Bad Request");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    private void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                throw new RuntimeException("Failed to create directory: " + directoryPath);
            }
        }
    }

}
