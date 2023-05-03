package com.example.inventorymanagement.service;

import com.example.inventorymanagement.dto.request.WarehouseLocationRequestDTO;
import com.example.inventorymanagement.entity.Location;
import com.example.inventorymanagement.entity.Warehouse;
import com.example.inventorymanagement.expection.DataNotFoundEx;
import com.example.inventorymanagement.repository.LocationRepo;
import com.example.inventorymanagement.repository.WarehouseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WarehouseService {
    private final WarehouseRepo warehouseRepo;
    private final LocationRepo locationRepo;

    public WarehouseService(WarehouseRepo warehouseRepo,
                            LocationRepo locationRepo) {
        this.warehouseRepo = warehouseRepo;
        this.locationRepo = locationRepo;
    }

    public Warehouse save(Warehouse warehouse) {
        return warehouseRepo.save(warehouse);
    }

    public boolean createWarehouseLocationRelation(WarehouseLocationRequestDTO warehouseLocationRequestDto) {
        Warehouse warehouse = warehouseRepo.findById(warehouseLocationRequestDto.getWarehouseId())
                .orElseThrow(() -> new DataNotFoundEx("Warehouse don't found",""));
        Location location = locationRepo.findById(warehouseLocationRequestDto.getLocationId())
                .orElseThrow(() -> new DataNotFoundEx("Location don't found",""));

//        if (warehouse.isPresent() && location.isPresent()) {
//            warehouseRepo.createWarehouseLocationRelation(warehouse.get().getWarehouseId(), location.get().getLocationId());
        warehouseRepo.createWarehouseLocationRelation(warehouse.getWarehouseId(), location.getLocationId());

        return true;
//        } else {
//            return false;
//        }
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


}
