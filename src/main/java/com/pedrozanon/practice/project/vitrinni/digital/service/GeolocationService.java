package com.pedrozanon.practice.project.vitrinni.digital.service;

import com.pedrozanon.practice.project.vitrinni.digital.domain.dto.GeolocResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "geolocationApi", url = "https://maps.googleapis.com/maps/api/geocode/")
public interface GeolocationService {

    @RequestMapping(method = RequestMethod.GET, value = "/json", produces = "application/json")
    GeolocResponse getGeoloc(@RequestParam String address, @RequestParam String key);
}
