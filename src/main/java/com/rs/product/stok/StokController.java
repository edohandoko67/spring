package com.rs.product.stok;

import com.rs.auth.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock")
public class StokController {

    @Autowired
    private StockRepository stockRepository;

    @GetMapping("/list")
    //@RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    //public ResponseEntity<StockResponse> listAll(@RequestParam(value = "name", required = false) String productName) {
    public ResponseEntity<StockResponse> listAll() {
        //List<Stock> stocks = stockRepository.findByProductId(id);
        //List<Stock> stockList = stockRepository.findByProductName(productName);
        List<Stock> stockList = stockRepository.findAll();
        List<StockInfo> stockInfos = stockList.stream()
                .map(stock ->
                        new StockInfo(
                        stock.getId_stok(),
                        stock.getProduct().getName(),
                        stock.getProduct().getImage_product(),
                        stock.getJumlah_stock(),
                                stock.getPrice()
                ))
                .collect(Collectors.toList());
        MetaData metaData = new MetaData(200, "Berhasil", "Data ditemukan");
        StockResponse response = new StockResponse(metaData, stockInfos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
