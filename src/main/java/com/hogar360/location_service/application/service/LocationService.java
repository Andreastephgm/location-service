package com.hogar360.location_service.application.service;

import com.hogar360.location_service.domain.exceptions.LocationAlreadyExistsException;
import com.hogar360.location_service.domain.model.Location;
import com.hogar360.location_service.domain.repository.LocationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createLocation(Location location) {
        locationRepository.findByName(location.getName())
                .ifPresent(l -> { throw new LocationAlreadyExistsException("Location with name " + location.getName() + " already exists"); });
        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Page<Location> searchLocations(String term, Pageable pageable) {
        if (term != null && !term.isBlank()) {
            return locationRepository
                    .findByCityContainingIgnoreCaseOrDepartmentContainingIgnoreCase(term, term, pageable);
        }
        return locationRepository.findAll(pageable);
    }
}


