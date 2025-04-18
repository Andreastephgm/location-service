package com.hogar360.location_service.infrastructure.controller;

import com.hogar360.location_service.application.service.LocationService;
import com.hogar360.location_service.domain.model.Location;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        if (locationService == null) {
            throw new IllegalArgumentException("LocationService cannot be null");
        }
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody @Valid Location location) {
        try {
            Location savedLocation = locationService.createLocation(location);
            return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
}
