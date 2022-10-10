package com.ipaddress.iphandle.controller;

import com.ipaddress.iphandle.model.Geolocation;
import com.ipaddress.iphandle.service.GeolocationService;
import com.ipaddress.iphandle.service.PostIpGetJsonService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class geolocationControllerTest {

    PostIpGetJsonService postIpGetJsonService;
    GeolocationService geoService;

    @Test
    void findGeolocationByIp() {
//        String query = "24.48.0.1";
//        boolean exists = geoService.checkCurrentGeoExists(query);
//        assertTrue(exists);
//
//        Geolocation geolocation = geoService.getGeolocation(query);
//        assertNotNull(geolocation);
//        assertNotNull(postIpGetJsonService.doPostGetJson(query));
    }
}