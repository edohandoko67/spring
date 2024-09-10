package com.rs.product.cart;

import com.rs.auth.MetaData;
import com.rs.product.stok.detail.DetailStock;
import com.rs.product.stok.detail.DetailStockRepository;
import com.rs.user.UserInfo;
import com.rs.user.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    DetailStockRepository detailStockRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @PostMapping("/create")
    public ResponseEntity<?> addToChart(@RequestBody CartRequest request) {
        try {
            addToCartService(request.getUserId(), request.getId_stock(), request.getQuantity());
            MetaData metaData = new MetaData(201, "Berhasil", "Menambahkan data");
            CartRequest cartRequest = new CartRequest(
                    request.getId_stock(),
                    request.getUserId(),
                    request.getQuantity()
            );
            CartResponseData apiResponse = new CartResponseData(metaData, cartRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Transactional
    public void addToCartService(int id_user, int id_stock, int quantity) {
        // Ambil data stock berdasarkan ID
        DetailStock detailStock = detailStockRepository.findById(id_stock)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        System.out.println("Received id_stock: " + id_stock);
        System.out.println("Received quantity: " + quantity);

        // Ambil data user berdasarkan ID
        UserInfo userInfo = userInfoRepository.findById(id_user)
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (detailStock.getJumlah_stock() < quantity) {
            throw new RuntimeException("Not enough stock available");
        }

        Cart existing = cartRepository.findByUserInfoAndDetailStock(userInfo, detailStock);

        if (existing != null) {
            existing.setJumlah_stock(existing.getJumlah_stock() + quantity);
            existing.setTotal(existing.getJumlah_stock() * detailStock.getPrice());

            // Simpan perubahan ke database
            cartRepository.save(existing);
        } else {
            // Buat entitas Chart untuk keranjang
            Cart cart = new Cart();
            cart.setUserInfo(userInfo);
            cart.setDetailStock(detailStock);
            cart.setJumlah_stock(quantity);
            cart.setTotal(detailStock.getPrice() * quantity);
            //MetaData metaData = new MetaData(201, "Berhasil", "Menambahkan data");

            // Simpan data ke tabel Chart
            cartRepository.save(cart);
        }
        // Update jumlah stok
        detailStock.setJumlah_stock(detailStock.getJumlah_stock() - quantity);
        detailStockRepository.save(detailStock);
    }

    @GetMapping("/list")
    public ResponseEntity<CartResponse> listData() {
        List<Cart> list = cartRepository.findAll();
        List<CartInfo> cartInfo = list.stream()
                .map(cart -> new CartInfo(
                        cart.getIdChart(),
                        cart.getDetailStock().getNameVarian(),
                        cart.getDetailStock().getImage(),
                        cart.getUserInfo().getName(),
                        cart.getJumlah_stock(),
                        cart.getTotal()
                )).toList();
        MetaData metaData = new MetaData(200, "Berhasil", "Mendapatkan data");
        CartResponse responseData = new CartResponse(metaData, cartInfo);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<CartResponse> listDataById(@RequestBody @Valid CartRequest request) {
        int userId = request.getUserId(); // Ambil userId dari objek request

        List<Cart> carts = cartRepository.findByUserInfoId(userId);

        if (carts.isEmpty()) {
            throw new RuntimeException("Cart not found with userId: " + userId);
        }

        // list berdasarkan id
        List<CartInfo> cartInfos = carts.stream()
                .map(cart -> new CartInfo(
                        cart.getIdChart(),
                        cart.getDetailStock().getNameVarian(),
                        cart.getDetailStock().getImage(),
                        cart.getUserInfo().getName(),
                        cart.getJumlah_stock(),
                        cart.getTotal()
                ))
                .collect(Collectors.toList());

        MetaData metaData = new MetaData(200, "Berhasil", "Mendapatkan data");
        CartResponse responseData = new CartResponse(metaData, cartInfos);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
