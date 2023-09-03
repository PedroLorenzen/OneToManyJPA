package com.example.jpamanytoonenew.controller;

import com.example.jpamanytoonenew.model.Kommune;
import com.example.jpamanytoonenew.repositories.KommuneRepository;
import com.example.jpamanytoonenew.service.ApiServiceGetKommuner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class KommuneRestController
{
    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;

    @Autowired
    KommuneRepository kommuneRepository;

    @GetMapping("/getKommunerFromApi")
    public List<Kommune> getKommunerFromApi()
    {
        List<Kommune> lstKommuner = apiServiceGetKommuner.getKommuner();
        return lstKommuner;
    }

    @GetMapping("/getKommunerFromRepository")
    public List<Kommune> getKommuner()
    {
        return kommuneRepository.findAll();
    }

    @GetMapping("/getKommuneByKode/{kode}")
    public ResponseEntity<Kommune> getKommuneByKode(@PathVariable String kode)
    {
        Optional<Kommune> kommuneOptional = kommuneRepository.findById(kode);
        return kommuneOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getKommuneByName/{navn}")
    public ResponseEntity<Kommune> getKommuneByName(@PathVariable String navn)
    {
        Optional<Kommune> kommuneOptional = kommuneRepository.findByNavn(navn);
        return kommuneOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/createKommune")
    @ResponseStatus(HttpStatus.CREATED)
    public Kommune createKommune (@RequestBody Kommune kommune)
    {
        return kommuneRepository.save(kommune);
    }

    @PutMapping("/editKommuneByKode/{kode}")
    public ResponseEntity<Kommune> putKommune(@PathVariable String kode, @RequestBody Kommune kommune)
    {
        Optional<Kommune> kommuneOptional = kommuneRepository.findById(kode);
        if ( kommuneOptional.isPresent() )
        {
            kommune.setKode(kode);
            kommuneRepository.save(kommune);
            return new ResponseEntity<>(kommune, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteKommuneByKode/{kode}")
    public ResponseEntity<String> deleteKommune(@PathVariable String kode)
    {
        Optional<Kommune> kommuneOptional = kommuneRepository.findById(kode);
        if (kommuneOptional.isPresent())
        {
            kommuneRepository.deleteById(kode);
            return ResponseEntity.ok("Kommune deleted");
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
