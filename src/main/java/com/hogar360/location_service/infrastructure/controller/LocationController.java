package com.hogar360.location_service.infrastructure.controller;

import com.hogar360.location_service.application.service.LocationService;
import com.hogar360.location_service.domain.model.Location;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Object> getLocations(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page",   defaultValue = "0") int page,
            @RequestParam(value = "size",   defaultValue = "10") int size,
            @RequestParam(value = "sort",   defaultValue = "id,asc") String sort
    ) {
        if (search != null && !search.isEmpty()) {
            String[] sortParams = sort.split(",");
            Pageable pageable = PageRequest.of(
                    page, size,
                    Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0])
            );
            Page<Location> result = locationService.searchLocations(search, pageable);
            return ResponseEntity.ok(result);
        } else {
            List<Location> locations = locationService.getAllLocations();
            return new ResponseEntity<>(locations, HttpStatus.OK);
        }
    }
}
