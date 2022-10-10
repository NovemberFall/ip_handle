package com.ipaddress.iphandle.service;

import com.ipaddress.iphandle.model.Geolocation;
import com.ipaddress.iphandle.repository.GeolocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@Service
public class PostIpGetJsonService {

    @Autowired
    private GeolocationRepository geolocationRepository;

    @Autowired
    private GeolocationService geolocationService;

    static RestTemplate restTemplate = new RestTemplate();

    public Geolocation doPostGetJson(String query) {
        String GET_GEOLOCATION_API = "http://ip-api.com/json/{query}";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<Geolocation> result =  restTemplate.exchange(GET_GEOLOCATION_API, HttpMethod.GET, entity, Geolocation.class, query);
        Geolocation geo = new Geolocation();

        // if the IP address exists && it is a valid ip address, then store this data into our database
        if (result.getBody().getQuery() != null && result.getBody().getCity() != null) {
                 geo.setQuery(result.getBody().getQuery())
                    .setCountry(result.getBody().getCountry())
                    .setCountryCode(result.getBody().getCountryCode())
                    .setRegion(result.getBody().getRegion())
                    .setRegionName(result.getBody().getRegionName())
                    .setCity(result.getBody().getCity())
                    .setZip(result.getBody().getZip())
                    .setLat(result.getBody().getLat())
                    .setLon(result.getBody().getLon())
                    .setTimezone(result.getBody().getTimezone());

            geolocationRepository.save(geo);
        }
        return geo;
    }
}
