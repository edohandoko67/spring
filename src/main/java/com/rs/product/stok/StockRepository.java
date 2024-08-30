package com.rs.product.stok;

import com.rs.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {
//    List<Stock> findByProduct_Id(int productId); // Menggunakan id dari Product
//    Optional<Stock> findByProduct_Name(String productName); // Untuk mencari stok berdasarkan nama produk dalam entitas Product


    @Query("SELECT s FROM Stock s WHERE s.product.name = :productName AND s.product.image_product = :imageProduct")
    List<Stock> findByProductName(@Param("productName") String productName);
}
