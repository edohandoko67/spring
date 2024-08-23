package com.rs.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
   @Query("SELECT p FROM Product p JOIN p.jadwalTokoSales t WHERE t.nomer_so = :nomer_so AND p.createdDate >= :createdDate")
   //@Query("SELECT p FROM Product p WHERE p.nomerSo = :nomerSo AND p.createdDate >= :createdDate")
   List<Product> findProductsByTokoNomerSo(@Param("nomer_so") String nomerSo, @Param("createdDate") LocalDate createdDate);
    Optional<Product> findByName(String name);
}
