package com.example.jpamanytoonenew.config;

import com.example.jpamanytoonenew.model.Kommune;
import com.example.jpamanytoonenew.model.Region;
import com.example.jpamanytoonenew.repositories.KommuneRepository;
import com.example.jpamanytoonenew.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    KommuneRepository kommuneRepository;

    @Override
    public void run(String... args) throws Exception
    {
            Region reg = new Region();
            reg.setKode("0001");
            reg.setNavn("Hovedstaden");
            reg.setHref("hoved.dk");
            regionRepository.save(reg);

            Kommune kom = new Kommune();
            kom.setKode("0002");
            kom.setNavn("Roskildex");
            kom.setHref("roskildex.dk");
            kom.setRegion(reg);
            kommuneRepository.save(kom);
    }
}
