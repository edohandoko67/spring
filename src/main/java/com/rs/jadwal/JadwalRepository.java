package com.rs.jadwal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JadwalRepository extends JpaRepository<JadwalResponse, Integer>
{
    Optional<JadwalResponse> findByName(String name);
}
