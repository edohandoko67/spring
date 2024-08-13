package com.rs.kelola;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/kelola")
public class KelolaController {
    @Autowired
    private KelolaRepository kelolaRepository;

    @GetMapping("/list")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN", "ROLE_UNIT", "ROLE_PIMPINAN"})
    public List<Kelola> listAll () {
        return kelolaRepository.findAll();
    }

    @PostMapping("/create")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Kelola> kelolaNewData(@RequestBody @Valid Kelola kelolaNewProduct){
        Kelola kelola = kelolaRepository.save(kelolaNewProduct);
        URI newKelolaURI = URI.create("/kelola/"+kelola.getId());
        return ResponseEntity.created(newKelolaURI).body(kelola);
    }

    @PutMapping("/update/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER", "ROLE_UNIT"})
    public ResponseEntity<Kelola> updateKelola(@PathVariable Integer id, @RequestBody Kelola updateKelolaData){
        Kelola kelola = kelolaRepository.findById(id).orElseThrow(() -> new KelolaNotFoundException("Product not exist with id: " +id));
        System.out.print(updateKelolaData.getNama());
        kelola.setNama(updateKelolaData.getNama());
        kelola.setKategori(updateKelolaData.getKategori());
        kelola.setStok(updateKelolaData.getStok());
        kelola.setHarga(updateKelolaData.getHarga());
        Kelola savedKelola = kelolaRepository.save(kelola);
        URI newKelolaURI = URI.create("/kelola/"+savedKelola.getId());
        return ResponseEntity.created(newKelolaURI).body(savedKelola);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Kelola> delete(@PathVariable("id") Integer id){
        kelolaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/name/{name}")
//    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN", "ROLE_UNIT", "ROLE_PIMPINAN"})
//    public ResponseEntity<Optional<Kelola>> Names(@PathVariable("pelapor") String pelapor) {
//        if (kelolaRepository.findByName(pelapor).isPresent()){
//            return ResponseEntity.ok(kelolaRepository.findByName(pelapor));
//        }
//        return ResponseEntity.notFound().build();
//    }

}
