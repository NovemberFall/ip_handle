package com.ipaddress.iphandle.repository;

import com.ipaddress.iphandle.model.Geolocation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeolocationRepository extends JpaRepository<Geolocation, Integer> {

    Geolocation findByQuery(String query);
}
