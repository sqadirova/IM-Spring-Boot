package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.Location;
import com.example.inventorymanagement.entity.Warehouse;
import com.example.inventorymanagement.repository.WarehouseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WarehouseService {
    private final WarehouseRepo warehouseRepo;

    public WarehouseService(WarehouseRepo warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }

    public Warehouse save(Warehouse warehouse) {
        return warehouseRepo.save(warehouse);
    }

    public List<Warehouse> getAll() {
        return warehouseRepo.findAll();
    }

    public Optional<Warehouse> getById(UUID id) {
        return warehouseRepo.findById(id);
    }

    public void deleteById(UUID id) {
        warehouseRepo.deleteById(id);
    }

    public Optional<Location> getAllLocationsByWarehouse(UUID warehouseId) {
        return warehouseRepo.findAllLocationsByWarehouseId(warehouseId);
    }


}
