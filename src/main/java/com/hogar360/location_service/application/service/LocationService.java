package com.hogar360.location_service.application.service;

import com.hogar360.location_service.domain.exceptions.LocationAlreadyExistsException;
import com.hogar360.location_service.domain.model.Location;
import com.hogar360.location_service.domain.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location createLocation(Location location) {
        Optional<Location> existingLocation = locationRepository.findByName(location.getName());
        if (existingLocation.isPresent()) {
            throw new LocationAlreadyExistsException("Location with name " + location.getName() + " already exists");
        }
        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}

