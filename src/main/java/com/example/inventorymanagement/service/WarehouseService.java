package com.example.inventorymanagement.service;

import com.example.inventorymanagement.dto.request.WarehouseLocationRequestDTO;
import com.example.inventorymanagement.entity.Location;
import com.example.inventorymanagement.entity.LogisticCenter;
import com.example.inventorymanagement.entity.Warehouse;
import com.example.inventorymanagement.expection.DataNotFoundEx;
import com.example.inventorymanagement.repository.LocationRepo;
import com.example.inventorymanagement.repository.LogisticCenterRepo;
import com.example.inventorymanagement.repository.WarehouseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WarehouseService {
    private final WarehouseRepo warehouseRepo;
    private final LocationRepo locationRepo;
    private final LogisticCenterRepo logisticCenterRepo;

    public WarehouseService(WarehouseRepo warehouseRepo,
                            LocationRepo locationRepo,
                            LogisticCenterRepo logisticCenterRepo) {
        this.warehouseRepo = warehouseRepo;
        this.locationRepo = locationRepo;
        this.logisticCenterRepo = logisticCenterRepo;
    }

    public Warehouse save(Warehouse warehouse) {
        //todo checks for logisticCenterId exists or not?

        Optional<LogisticCenter> logisticCenter = logisticCenterRepo.findById(warehouse.getLogisticCenter().getLogisticCenterId());
        if (logisticCenter.isEmpty()){
            throw new DataNotFoundEx("Can not find logistic center.","Can not find logistic center.");
        }

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
