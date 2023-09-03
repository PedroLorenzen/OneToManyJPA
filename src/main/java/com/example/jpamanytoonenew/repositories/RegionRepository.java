package com.example.jpamanytoonenew.repositories;

import com.example.jpamanytoonenew.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, String>
{
    Optional<Region> findByNavn(String navn);
}
