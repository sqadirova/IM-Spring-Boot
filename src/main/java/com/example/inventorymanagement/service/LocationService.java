package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.Location;
import com.example.inventorymanagement.repository.LocationRepo;
import com.example.inventorymanagement.repository.WarehouseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {

    private final LocationRepo locationRepo;
    private final WarehouseRepo warehouseRepo;

    public LocationService(LocationRepo locationRepo,
                           WarehouseRepo warehouseRepo) {
        this.locationRepo = locationRepo;
        this.warehouseRepo = warehouseRepo;
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

    public Optional<Location> getAllLocationsByWarehouse(UUID warehouseId) {
        return warehouseRepo.findAllLocationsByWarehouseId(warehouseId);
    }


}
