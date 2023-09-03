package com.example.jpamanytoonenew.service;

import com.example.jpamanytoonenew.model.Kommune;
import com.example.jpamanytoonenew.repositories.KommuneRepository;
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
public class ApiServiceGetKommunerImpl implements ApiServiceGetKommuner {

    @Autowired
    KommuneRepository kommuneRepository;

    private final RestTemplate restTemplate;

    public ApiServiceGetKommunerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String kommuneUrl = "https://api.dataforsyningen.dk/kommuner";

    private void saveKommuner(List<Kommune> kommuner) {  // changed from private to public
        try {
            kommuner.forEach(kommuneRepository::save);
            System.out.println("Data saved to the database.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An exception occurred while saving data to the database.");
        }
    }

    @Override
    public List<Kommune> getKommuner() {
        List<Kommune> lst = new ArrayList<>();
        ResponseEntity<List<Kommune>> kommuneResponse =
                restTemplate.exchange(kommuneUrl,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Kommune>>() {});

        if (kommuneResponse.getStatusCode() == HttpStatus.OK) {
            List<Kommune> kommuner = kommuneResponse.getBody();
            if (kommuner != null) {
                saveKommuner(kommuner);  // call the public method
                return kommuner;
            } else {
                System.out.println("The API response body is null.");
            }
        } else {
            System.out.println("API request failed with status code: " + kommuneResponse.getStatusCode());
        }

        return lst;
    }
}
