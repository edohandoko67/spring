package com.rs.product;


import com.rs.auth.MetaData;
import com.rs.user.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @GetMapping("/list")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    //public List<Product>
    public ResponseEntity<ProductResponse> listAll() {
        List<Product> products = productRepository.findAll();
        // Convert Product entities to ProductInfo DTOs
        List<ProductInfo> productInfos = products.stream()
                .map(p -> new ProductInfo(
                        p.getId_product(),
                        p.getName(),
                        p.getPrice(),
                        p.getPembuat(),
                        p.getQuantity(),
                        p.getDiscount(),
                        p.getAlasan()))
                .collect(Collectors.toList());
        MetaData metaData = new MetaData(
                HttpStatus.OK.value(),
                "Success",
                "Products retrieved successfully"
        );
        ProductResponse response = new ProductResponse(metaData, productInfos);
        return new ResponseEntity<>(response, HttpStatus.OK);
        //return productRepository.findAll();
    }

    @GetMapping("/list_date")
    @ResponseBody
    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    //public List<Product>
    public ResponseEntity<?> getDataByDate(
            @RequestParam(value = "nomer_so", required = false) String nomer_so,
            @RequestParam(value = "created_date", required = false) String createdDateStr) {
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            // Mengonversi string tanggal ke LocalDateTime
            LocalDate createdDate = LocalDate.parse(createdDateStr, formatter);

            List<Product> products = productRepository.findProductsByTokoNomerSo(nomer_so, createdDate);

            logger.debug("Products retrieved: {}", products);
            // Convert Product entities to ProductInfo DTOs
            List<ProductInfo> productInfos = products.stream()
                    .map(p -> new ProductInfo(
                            p.getId_product(),
                            p.getName(),
                            p.getPrice(),
                            p.getPembuat(),
                            p.getQuantity(),
                            p.getDiscount(),
                            p.getAlasan()))
                    .collect(Collectors.toList());
            MetaData metaData = new MetaData(
                    HttpStatus.OK.value(),
                    "Success",
                    "Products retrieved successfully"
            );
            ProductResponse response = new ProductResponse(metaData, productInfos);
            return new ResponseEntity<>(response, HttpStatus.OK);
            //return productRepository.findAll();
        } catch (Exception e) {
            MetaData metaData = new MetaData(400, "Error", "Bad Request");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

    }

    @PostMapping("/create")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> createNewProduct(@RequestBody @Valid Product newProductData)
    {
        try {
            Product savedProduct = productRepository.save(newProductData);
            URI newProductURI = URI.create("/product/"+savedProduct.getId_product());
            MetaData metaData = new MetaData(201, "Success", "Product created successfully");
            ProductInfo responseData = new ProductInfo(
                    savedProduct.getId_product(),
                    savedProduct.getName(),
                    savedProduct.getPrice(),
                    savedProduct.getPembuat(),
                    savedProduct.getQuantity(),
                    savedProduct.getDiscount(),
                    savedProduct.getAlasan()
            );
            //ProductResponse apiResponse = new ProductResponse(metaData, Collections.singletonList(responseData)); // Wrap ProductInfo in a List
            ProductResponseData apiResponse = new ProductResponseData(metaData, responseData); // Wrap ProductInfo in a List
            return ResponseEntity.created(newProductURI).body(apiResponse);
        } catch (Exception e) {
            MetaData metaData = new MetaData(500, "Gagal", "Internal server error");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product updatedProductData) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not exist with id: " +id));
        product.setName(updatedProductData.getName());
        product.setPrice(updatedProductData.getPrice());
        product.setPrice(updatedProductData.getPrice());
        Product savedProduct = productRepository.save(product);
        URI newProductURI = URI.create("/product/"+savedProduct.getId_product());
        return ResponseEntity.created(newProductURI).body(savedProduct);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Product deleteProduct) {
        try {
            Integer id = deleteProduct.getId_product();
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
                MetaData metaData = new MetaData(200, "Success", "Berhasil menghapus data");
                return ResponseEntity.ok(metaData);
            } else {
                MetaData metaData = new MetaData(404, "Not Found", "Data tidak ditemukan");
                ErrorResponse errorResponse = new ErrorResponse(metaData);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
           // return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            MetaData metaData = new MetaData(500, "Gagal", "Internal server error");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    //@DeleteMapping("/delete/{id_product}")
//    @DeleteMapping("/delete/{id_product}")
//    public ResponseEntity<?> delete(@PathVariable("id_product") Integer id) {
//        try {
//            if (productRepository.existsById(id)) {
//                productRepository.deleteById(id);
//                MetaData metaData = new MetaData(200, "Success", "Berhasil menghapus data");
//                return ResponseEntity.ok(metaData);
//            } else {
//                MetaData metaData = new MetaData(404, "Not Found", "Data tidak ditemukan");
//                ErrorResponse errorResponse = new ErrorResponse(metaData);
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//            }
//           // return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            MetaData metaData = new MetaData(500, "Gagal", "Internal server error");
//            ErrorResponse errorResponse = new ErrorResponse(metaData);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
//    }

    @GetMapping("{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    public ResponseEntity<Optional<Product>> Id(@PathVariable("id") Integer id) {
        if (productRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(productRepository.findById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    public ResponseEntity<Optional<Product>> Name(@PathVariable("name") String name) {
        if (productRepository.findByName(name).isPresent()) {
            return ResponseEntity.ok(productRepository.findByName(name));
        }
        return ResponseEntity.notFound().build();
    }

}
