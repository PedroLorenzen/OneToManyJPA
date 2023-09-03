package com.example.jpamanytoonenew.service;

import com.example.jpamanytoonenew.model.Region;
import com.example.jpamanytoonenew.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceGetRegionerImpl implements ApiServiceGetRegioner {

    @Autowired
    RegionRepository regionRepository;

    private final RestTemplate restTemplate;

    public ApiServiceGetRegionerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String regionUrl = "https://api.dataforsyningen.dk/regioner";

    private void saveRegioner(List<Region> regioner) {
        try {
            regioner.forEach(regionRepository::save);
            System.out.println("Data saved to the database.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An exception occurred while saving data to the database.");
        }
    }

    @Override
    public List<Region> getRegioner() {
        List<Region> lst = new ArrayList<>();
        ResponseEntity<List<Region>> regionResponse =
                restTemplate.exchange(regionUrl,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Region>>() {});

        // Validate Status Code
        if (regionResponse.getStatusCode() == HttpStatus.OK) {
            List<Region> regioner = regionResponse.getBody();

            // Validate if the body is null
            if (regioner != null) {
                System.out.println("Fetched data from API: " + regioner);

                // Save to the database
                saveRegioner(regioner);

                return regioner;
            } else {
                System.out.println("The API response body is null.");
            }
        } else {
            System.out.println("API request failed with status code: " + regionResponse.getStatusCode());
        }

        return lst;
    }
}
