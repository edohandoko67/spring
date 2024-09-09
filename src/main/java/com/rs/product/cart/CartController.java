package com.rs.product.cart;

import com.rs.auth.MetaData;
import com.rs.product.stok.detail.DetailStock;
import com.rs.product.stok.detail.DetailStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    DetailStockRepository detailStockRepository;

    @PostMapping("/create")
    public ResponseEntity<String> addToChart(@RequestBody CartRequest request) {
        try {
            addToCartService(request.getId_stock(), request.getQuantity());
            return ResponseEntity.ok("Item added to cart");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Transactional
    public void addToCartService(int id_stock, int quantity) {
        // Ambil data stock berdasarkan ID
        DetailStock detailStock = detailStockRepository.findById(id_stock)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        System.out.println("Received id_stock: " + id_stock);
        System.out.println("Received quantity: " + quantity);

        if (detailStock.getJumlah_stock() < quantity) {
            throw new RuntimeException("Not enough stock available");
        }

        // Buat entitas Chart untuk keranjang
        Cart cart = new Cart();
        cart.setDetailStock(detailStock);
        cart.setJumlah_stock(quantity);
        cart.setTotal(detailStock.getPrice() * quantity);

        // Simpan data ke tabel Chart
        cartRepository.save(cart);

        // Update jumlah stok
        detailStock.setJumlah_stock(detailStock.getJumlah_stock() - quantity);
        detailStockRepository.save(detailStock);
    }

    @GetMapping("/list")
    public ResponseEntity<CartResponseData> listData() {
        List<Cart> list = cartRepository.findAll();
        List<CartInfo> cartInfo = list.stream()
                .map(cart -> new CartInfo(
                        cart.getIdChart(),
                        cart.getDetailStock().getNameVarian(),
                        cart.getJumlah_stock(),
                        cart.getTotal()
                )).toList();
        MetaData metaData = new MetaData(200, "Berhasil", "Mendapatkan data");
        CartResponseData responseData = new CartResponseData(metaData, cartInfo);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
