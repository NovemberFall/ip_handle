package com.ipaddress.iphandle.controller;

import com.ipaddress.iphandle.model.Geolocation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 this class is to test if we can get the user's ip address
*/
public class RestClient {
    private static final String GET_GEOLOCATION_API = "http://ip-api.com/json/24.48.0.1";

    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        callGetIpAPI();
    }

    private static void callGetIpAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> result =  restTemplate.exchange(GET_GEOLOCATION_API, HttpMethod.GET, entity, String.class);
        System.out.println(result.getBody());
    }

    private static void callGetGeolocationByIpAPI() {
        Map<String, String> param = new HashMap<>();
        param.put("ip", "98.37.50.165");
        Geolocation geo = restTemplate.getForObject(GET_GEOLOCATION_API, Geolocation.class, param);
        System.out.println(geo.getQuery());
        System.out.println(geo.getCountry());
        System.out.println(geo.getCountryCode());
        System.out.println(geo.getRegion());
        System.out.println(geo.getRegionName());
        System.out.println(geo.getCity());
        System.out.println(geo.getZip());
        System.out.println(geo.getLat());
        System.out.println(geo.getLon());
        System.out.println(geo.getTimezone());
    }
}
