package com.rs.product.cart;

import com.rs.product.stok.detail.DetailStock;
import com.rs.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    //Jika Anda menggunakan query atau metode pencarian di CartRepository
    // yang memerlukan objek UserInfo, pastikan bahwa Anda mengirimkan
    // objek UserInfo, bukan hanya ID-nya.
    // Metode untuk mencari Cart berdasarkan userInfo dan detailStock
    Cart findByUserInfoAndDetailStock(UserInfo userInfo, DetailStock detailStock);

}
