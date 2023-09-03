package com.example.jpamanytoonenew.repositories;

import com.example.jpamanytoonenew.model.Kommune;
import com.example.jpamanytoonenew.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KommuneRepository extends JpaRepository<Kommune, String>
{
    Optional<Kommune> findByNavn(String navn);

}
