package com.hogar360.location_service.domain.repository;

import com.hogar360.location_service.domain.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByName(String name);
    Page<Location> findByCityContainingIgnoreCaseOrDepartmentContainingIgnoreCase(
            String cityFragment,
            String departmentFragment,
            Pageable pageable
    );
}
