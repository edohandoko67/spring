package com.rs.product;


import com.rs.auth.MetaData;
import com.rs.user.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
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

    @GetMapping("/list")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    //public List<Product>
    public ResponseEntity<ProductResponse> listAll() {
        List<Product> products = productRepository.findAll();

        // Convert Product entities to ProductInfo DTOs
        List<ProductInfo> productInfos = products.stream()
                .map(p -> new ProductInfo(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getPembuat(),
                        p.getQuantity(),
                        p.getDiscount(),
                        p.getAlasan(),
                        p.getSatuanProducts()))
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

    @PostMapping("/create")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> createNewProduct(@RequestBody @Valid Product newProductData)
    {
        try {
            Product savedProduct = productRepository.save(newProductData);
            URI newProductURI = URI.create("/product/"+savedProduct.getId());
            MetaData metaData = new MetaData(201, "Success", "Product created successfully");
            ProductInfo responseData = new ProductInfo(
                    savedProduct.getId(),
                    savedProduct.getName(),
                    savedProduct.getPrice(),
                    savedProduct.getPembuat(),
                    savedProduct.getQuantity(),
                    savedProduct.getDiscount(),
                    savedProduct.getAlasan(),
                    savedProduct.getSatuanProducts()
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
        URI newProductURI = URI.create("/product/"+savedProduct.getId());
        return ResponseEntity.created(newProductURI).body(savedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Integer id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
