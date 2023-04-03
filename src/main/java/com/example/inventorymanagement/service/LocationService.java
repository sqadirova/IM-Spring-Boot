package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.Location;
import com.example.inventorymanagement.repository.LocationRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {

    private final LocationRepo locationRepo;

    public LocationService(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }


    public Location save(Location location) {
        return locationRepo.save(location);
    }

    public List<Location> getAll() {
        return locationRepo.findAll();
    }

    public Optional<Location> getById(UUID id) {
        return locationRepo.findById(id);
    }

    public void deleteById(UUID id) {
        locationRepo.deleteById(id);
    }


}
