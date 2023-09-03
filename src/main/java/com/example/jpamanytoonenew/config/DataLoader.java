package com.example.jpamanytoonenew.config;

import com.example.jpamanytoonenew.service.ApiServiceGetKommuner;
import com.example.jpamanytoonenew.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private ApiServiceGetRegioner apiServiceGetRegioner;

    @Autowired
    private ApiServiceGetKommuner apiServiceGetKommuner;

    @Override
    public void run(ApplicationArguments args) {
        apiServiceGetRegioner.getRegioner();
        apiServiceGetKommuner.getKommuner();
    }
}
