package com.briancorp.user.Controllers;

import com.briancorp.user.Models.Product;
import com.briancorp.user.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    //Jawaban UTS no. 3
    @PostMapping("/create")
    public ResponseEntity<Product> createNewProduct(@RequestBody @Valid Product newProductData) {
        Product savedProduct = productRepository.save(newProductData);
        URI newProductURI = URI.create("/product/"+savedProduct.getId());
        return ResponseEntity.created(newProductURI).body(savedProduct);
    }

    //Jawaban UTS no. 2
    @GetMapping("/{id}")
    public ResponseEntity <Optional<Product>> idProduct(@PathVariable("id") Integer id) {
        if (productRepository.findById(id).isPresent()){
            return new ResponseEntity<>(productRepository.findById(id), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
