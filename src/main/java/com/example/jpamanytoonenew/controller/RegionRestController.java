package com.example.jpamanytoonenew.controller;

import com.example.jpamanytoonenew.model.Kommune;
import com.example.jpamanytoonenew.model.Region;
import com.example.jpamanytoonenew.repositories.RegionRepository;
import com.example.jpamanytoonenew.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RegionRestController
{
    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;

    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/getRegionerFromApi")
        public List<Region> getRegionerFromApi()
        {
            List<Region> lstRegioner = apiServiceGetRegioner.getRegioner();
            return lstRegioner;
        }

    @GetMapping("/getRegionerFromRepository")
    public List<Region> getRegioner()
    {
        return regionRepository.findAll();
    }

    @GetMapping("/getRegionByKode/{kode}")
    public ResponseEntity<Region> getRegionByKode(@PathVariable String kode)
    {
        Optional<Region> regionOptional = regionRepository.findById(kode);
        if ( regionOptional.isPresent() )
        {
            return new ResponseEntity<>(regionOptional.get(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getRegionByName/{navn}")
    public ResponseEntity<Region> getRegionByName(@PathVariable String navn)
    {
        Optional<Region> regionOptional = regionRepository.findByNavn(navn);
        if ( regionOptional.isPresent() )
        {
            return new ResponseEntity<>(regionOptional.get(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getKommuneNavneByRegionKode/{kode}")
    public ResponseEntity<List<String>> getKommuneNavneByRegionKode(@PathVariable String kode) {
        Optional<Region> regionOptional = regionRepository.findById(kode);
        if (regionOptional.isPresent()) {
            Region region = regionOptional.get();
            List<String> kommuneNavne = region.getKommuneNavne();
            // eller: List<String> kommuneNavne = regionOptional.get().getKommuneNavne();
            return new ResponseEntity<>(kommuneNavne, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /* Hvis jeg vil hente en liste af kommuneobjekter istedet for bare navne.
    @GetMapping("/getKommuneByRegionKode/{kode}")
    public ResponseEntity<List<Kommune>> getKommuneNavneByRegionKode(@PathVariable String kode) {
        Optional<Region> regionOptional = regionRepository.findById(kode);
        if (regionOptional.isPresent()) {
            Region region = regionOptional.get();
            List<Kommune> kommuneNavne = region.getKommuneNavne();
            return new ResponseEntity<>(kommuneNavne, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
     */

    @PostMapping("/createRegion")
    @ResponseStatus(HttpStatus.CREATED)
    public Region createRegion (@RequestBody Region region)
    {
        System.out.println(region);
        return regionRepository.save(region);
    }

    @PutMapping("/editRegionByKode/{kode}")
    public ResponseEntity<Region> putRegionByKode(@PathVariable String kode, @RequestBody Region region)
    {
        Optional<Region> regionOptional = regionRepository.findById(kode);
        if ( regionOptional.isPresent() )
        {
            region.setKode(kode);  // Set the ID on the incoming student object
            regionRepository.save(region);  // Save it, effectively updating the existing record
            return new ResponseEntity<>(region, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteRegionByKode/{kode}")
    public ResponseEntity<String> deleteRegionByKode(@PathVariable String kode)
    {
        Optional<Region> regionOptional = regionRepository.findById(kode);
        if (regionOptional.isPresent())
        {
            regionRepository.deleteById(kode);
            return ResponseEntity.ok("Region deleted");
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
