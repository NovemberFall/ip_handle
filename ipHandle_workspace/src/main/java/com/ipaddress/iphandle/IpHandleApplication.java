package com.ipaddress.iphandle;

import com.ipaddress.iphandle.model.Geolocation;
import com.ipaddress.iphandle.repository.GeolocationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

/**
 * My main class can read data from cache
 * , and initialize a data and store in my Database
 */
@SpringBootApplication
//@EnableCaching
public class IpHandleApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpHandleApplication.class, args);
    }

    // initialize a data, and insert into our database
    @Bean
    CommandLineRunner init(GeolocationRepository geolocationRepository) {
        return arg -> {
            Geolocation initGeo = new Geolocation()
                    .setQuery("127.0.0.1")
                    .setCountry("China")
                    .setCountryCode("CN")
                    .setRegion("SH")
                    .setRegionName("Jiang Su")
                    .setCity("Su Zhou")
                    .setZip("350005")
                    .setLat(70.7777)
                    .setLon(-145.999)
                    .setTimezone("China/Jiang_Su");

            initGeo = !Optional.ofNullable(geolocationRepository.findByQuery("127.0.0.1")).isPresent() ? geolocationRepository.save(initGeo) : geolocationRepository.findByQuery("127.0.0.1");

        };
    }
}
