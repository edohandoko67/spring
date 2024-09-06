package com.rs.product.stok.detail;

import com.rs.auth.MetaData;
import com.rs.user.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/detail_stock_image")
public class DetailStockController {

    @Autowired
    DetailStockRepository detailStockRepository;

    @GetMapping("/list_image")
    public ResponseEntity<DetailStockInfoResponse> listStockId() {
        List<DetailStock> detailStockList = detailStockRepository.findAll();

        List<DetailStockInfo> detailStockInfos = detailStockList.stream()
                .map(detailStock -> new DetailStockInfo(
                        detailStock.getIdDetailStock(),
                        detailStock.getProduct().getImage_product(),
                        detailStock.getJumlah_stock(),
                        detailStock.getPrice(),
                        detailStock.getImage(),
                        detailStock.getNameVarian()
                ))
                .collect(Collectors.toList());

        MetaData metaData = new MetaData(200, "Berhasil", "Data found");
        DetailStockInfoResponse response = new DetailStockInfoResponse(metaData, detailStockInfos);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    private static final String IMAGE_DIRECTORY = "src/main/resources/static/path/image_detail_stock/";

    @PostMapping("/create")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> createNewImageDetailStock(
            @Valid DetailStock newDetailStock,
            @RequestParam("image_detail_stock") MultipartFile imageFile
    ) {
        try {
            // Process the image file
            createDirectoryIfNotExists(IMAGE_DIRECTORY);
            String imageFileName = "image_detail_stock_" + System.currentTimeMillis() + ".png";
            File imageFileOnDisk = new File(IMAGE_DIRECTORY + imageFileName);
            BufferedImage bufferedImage = ImageIO.read(imageFile.getInputStream());
            ImageIO.write(bufferedImage, "png", imageFileOnDisk);

            // Update the image path in the DetailStock entity
            String imageDetailStockPath = "path/image_detail_stock/" + imageFileName;
            newDetailStock.setImage(imageDetailStockPath);

            // Save the DetailStock entity
            DetailStock savedDetail = detailStockRepository.save(newDetailStock);
            URI newDetailStockURI = URI.create("/detail_stock_image/" + savedDetail.getIdDetailStock());

            // Prepare the response data
            MetaData metaData = new MetaData(201, "Success", "Data successfully added");
            DetailStockInfo responseData = new DetailStockInfo(
                    savedDetail.getIdDetailStock(),
                    savedDetail.getProduct().getImage_product(),
                    savedDetail.getJumlah_stock(),
                    savedDetail.getPrice(),
                    savedDetail.getImage(),
                    savedDetail.getNameVarian()
            );
            DetailStockInfoResponseData apiResponse = new DetailStockInfoResponseData(metaData, responseData);

            return ResponseEntity.created(newDetailStockURI).body(apiResponse);
        } catch (Exception e) {
            MetaData metaData = new MetaData(400, "Bad Request", "Error adding data");
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
