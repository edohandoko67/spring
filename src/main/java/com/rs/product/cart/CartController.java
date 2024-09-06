package com.rs.product.cart;

import com.rs.product.stok.detail.DetailStock;
import com.rs.product.stok.detail.DetailStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

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
            addToCartService(request.getStockId(), request.getQuantity());
            return ResponseEntity.ok("Item added to cart");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Transactional
    public void addToCartService(int stockId, int quantity) {
        // Ambil data stock berdasarkan ID
        DetailStock detailStock = detailStockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        if (detailStock.getJumlah_stock() < quantity) {
            throw new RuntimeException("Not enough stock available");
        }

        // Buat entitas Chart untuk keranjang
        Cart cart = new Cart();
        cart.setIdChart(detailStock.getProduct().getId_product());
        cart.setJumlah_stock(quantity);
        cart.setTotal(detailStock.getPrice() * quantity);

        // Simpan data ke tabel Chart
        cartRepository.save(cart);

        // Update jumlah stok
        detailStock.setJumlah_stock(detailStock.getJumlah_stock() - quantity);
        detailStockRepository.save(detailStock);
    }

}
