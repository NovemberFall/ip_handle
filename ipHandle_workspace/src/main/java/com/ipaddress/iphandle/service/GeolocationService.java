package com.ipaddress.iphandle.service;

import com.ipaddress.iphandle.model.Geolocation;
import com.ipaddress.iphandle.repository.GeolocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeolocationService {
    @Autowired
    private GeolocationRepository geolocationRepository;

    public Geolocation saveGeolocation(Geolocation geolocation) {
        return geolocationRepository.save(geolocation);
    }

    public List<Geolocation> saveGeolocations(List<Geolocation> geolocations) {
        return geolocationRepository.saveAll(geolocations);
    }

    public List<Geolocation> getGeolocations() {
        return geolocationRepository.findAll();
    }

    // fetch a data from our database, and testing for Cache
    @Cacheable(value = "geo")
    public Geolocation getGeolocation(String query) {
        System.out.println("Fetching Data From DataBase ... # testing for cache");
        Geolocation geo =  geolocationRepository.findByQuery(query);
        return geo;
    }


    // check the current data is in our database, if so return True
    public boolean checkCurrentGeoExists(String query) {
        Geolocation found = getGeolocation(query);
        if (found != null) {
            return true;
        }
        return false;
    }
}
