package com.rs.user.message;

import com.rs.auth.MetaData;
import com.rs.user.pesan.PushNotificationOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pesan")
public class PesanController {

    @Autowired
    PesanRepository repository;

    @GetMapping("/list")
    public ResponseEntity<PesanResponse> listPesan() {
        List<PesanModel> pesanModelList = repository.findAll();
        List<PesanInfo> infos = pesanModelList.stream()
                .map(pesanModel -> new PesanInfo(
                        pesanModel.getIdPesan(),
                        pesanModel.getTitle(),
                        pesanModel.getMessage()
                )).collect(Collectors.toList());
        MetaData metaData = new MetaData(200, "Berhasil", "Mendapatkan data pesan");
        PesanResponse apiResponse = new PesanResponse(metaData, infos);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<PesanResponse> savePesan(@RequestBody PesanModel pesanModel) {
        // Simpan pesan ke database
        PesanModel savedPesan = repository.save(pesanModel);

        // Kirim notifikasi
        PushNotificationOptions.sendMessageToAllUsers(savedPesan.getTitle(), savedPesan.getMessage());

        MetaData metaData = new MetaData(200, "Berhasil", "Pesan berhasil disimpan dan notifikasi dikirim.");
        PesanResponse apiResponse = new PesanResponse(metaData, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
