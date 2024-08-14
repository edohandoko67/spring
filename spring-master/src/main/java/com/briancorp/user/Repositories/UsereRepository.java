package com.briancorp.user.Repositories;

import com.briancorp.user.Models.Usere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsereRepository extends JpaRepository<Usere, Integer> {
    Optional<Usere> findByUsername(String username); //sebuah class di java untuk menanyakan sesuatu yang belum ada isinya
}
