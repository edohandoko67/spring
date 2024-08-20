package com.rs.jadwal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jadwal")
public class JadwalController
{
    @Autowired
    private JadwalRepository jadwalRepository;

    @GetMapping("/list")
   // @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    public List<JadwalResponse> listAll() {
        return jadwalRepository.findAll();
    }

    @PostMapping("/create")
    //@RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<JadwalResponse> createNewJadwal(@RequestBody @Valid JadwalResponse newJadwalDataResponse)
    {
        JadwalResponse savedJadwalResponse = jadwalRepository.save(newJadwalDataResponse);
        URI newJadwalURI = URI.create("/jadwal/"+ savedJadwalResponse.getId());
        return ResponseEntity.created(newJadwalURI).body(savedJadwalResponse);
    }

    @GetMapping("/name/{name}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    public ResponseEntity<Optional<JadwalResponse>> Name(@PathVariable("name") String name)
    {
        if (jadwalRepository.findByName(name).isPresent())
        {
            return ResponseEntity.ok(jadwalRepository.findByName(name));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    //@RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<JadwalResponse> updateJadwal(@PathVariable Integer id, @RequestBody JadwalResponse updatedJadwalDataResponse)
    {
        JadwalResponse jadwalResponse = jadwalRepository.findById(id).orElseThrow(() -> new JadwalNotFoundException("Jadwal not exist with id: " +id));
        jadwalResponse.setName(updatedJadwalDataResponse.getName());
        jadwalResponse.setHari_praktik(updatedJadwalDataResponse.getHari_praktik());
        jadwalResponse.setLokasi_praktik(updatedJadwalDataResponse.getLokasi_praktik());
        JadwalResponse savedJadwalResponse = jadwalRepository.save(jadwalResponse);
        URI newJadwalURI = URI.create("/jadwal/"+ savedJadwalResponse.getId());
        return ResponseEntity.created(newJadwalURI).body(savedJadwalResponse);
    }
}
