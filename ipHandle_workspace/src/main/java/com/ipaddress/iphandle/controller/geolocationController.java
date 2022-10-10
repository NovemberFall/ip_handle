package com.ipaddress.iphandle.controller;

import com.ipaddress.iphandle.model.Geolocation;
import com.ipaddress.iphandle.service.GeolocationService;
import com.ipaddress.iphandle.service.PostIpGetJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class geolocationController {

    @Autowired
    private GeolocationService geoService;

    @Autowired
    private PostIpGetJsonService postIpGetJsonService;





    // We can fetch data from database or cache
    @RequestMapping("/api/{query}")
    @Cacheable(value = "geo")
    public Geolocation findGeolocationByIp(@PathVariable String query) {
        if (geoService.checkCurrentGeoExists(query)) {
            Geolocation geo = geoService.getGeolocation(query);
            return geo;
        } else {
            return postIpGetJsonService.doPostGetJson(query);
        }
    }





    // We can immediately remove data from cache
    @RequestMapping(value="/api/cache")
    @CacheEvict(value="geo", allEntries=true)
    public String clearCache() {
        return "Cache is cleared successfully";
    }








//    @RequestMapping("/api/{query}")
//    public void findGeolocationByIp(@PathVariable String query) {
//        if (geoService.checkCurrentGeoExists(query)) {
//            Geolocation geo = geoService.getGeolocation(query);
//        } else {
//            postIpGetJsonService.doPostGetJson("");
//        }
////        RedirectView redirectView = new RedirectView();
////        redirectView.setUrl("http://ip-api.com/json/");
//    }






//    @RequestMapping("/api/address")
//    public ModelAndView index(HttpServletRequest request) {
//        ModelAndView model = new ModelAndView("index");
//        String clientIp = requestService.getClientIp(request);
//        model.addObject("clientIp", clientIp);
//        return model;
//    }
}
