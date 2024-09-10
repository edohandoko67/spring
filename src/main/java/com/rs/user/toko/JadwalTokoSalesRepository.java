package com.rs.user.toko;

import com.rs.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JadwalTokoSalesRepository extends JpaRepository<JadwalTokoSales, Integer> {
    List<JadwalTokoSales> findByUserInfoId(Integer userId);
}
