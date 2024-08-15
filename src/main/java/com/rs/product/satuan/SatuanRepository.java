package com.rs.product.satuan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SatuanRepository extends JpaRepository<SatuanProduct, Integer> {
   // Optional<SatuanProduct> findByName(String name);
}
