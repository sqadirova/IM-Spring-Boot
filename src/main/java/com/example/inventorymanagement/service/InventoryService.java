package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.*;
import com.example.inventorymanagement.expection.DataDuplicationEx;
import com.example.inventorymanagement.expection.DataNotFoundEx;
import com.example.inventorymanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class InventoryService {
    private final InventoryRepo inventoryRepo;
    private final LogisticCenterRepo logisticCenterRepo;
    private final WarehouseRepo warehouseRepo;
    private final LocationRepo locationRepo;
    private final InventoryCategoryRepo inventoryCategoryRepo;

    @Autowired
    public InventoryService(InventoryRepo inventoryRepo,
                            LogisticCenterRepo logisticCenterRepo,
                            WarehouseRepo warehouseRepo,
                            LocationRepo locationRepo,
                            InventoryCategoryRepo inventoryCategoryRepo) {
        this.inventoryRepo = inventoryRepo;
        this.logisticCenterRepo = logisticCenterRepo;
        this.warehouseRepo = warehouseRepo;
        this.locationRepo = locationRepo;
        this.inventoryCategoryRepo = inventoryCategoryRepo;
    }

    public Inventory save(Inventory inventory) {
        //check for unique rfid
        Optional<Inventory> firstByInventoryName = inventoryRepo.findFirstByInventoryRFID(inventory.getInventoryRFID());
        if (firstByInventoryName.isPresent()) {
            throw new DataDuplicationEx("This rfid already exists.", "InventoryRfid field duplicate dev msg");
        }

        //checks for location unique or not
        Optional<Inventory> inventoryByLocation = inventoryRepo.findFirstByLocation_LocationId(inventory.getLocation().getLocationId());
        if (inventoryByLocation.isPresent()) {
            throw new DataDuplicationEx("Inventory in this location already exists", "Location field duplicate");
        }

        //checks for logisticCenterId,warehouseId,locationId,inventory_category_id exists or not
        Optional<InventoryCategory> inventoryCategory = inventoryCategoryRepo.findById(inventory.getInventoryCategory().getInventoryCategoryId());
        if (inventoryCategory.isEmpty()) {
            throw new DataDuplicationEx("Inventory Category not found!", "Inventory Category not found!");
        }

        Optional<LogisticCenter> logisticCenter = logisticCenterRepo.findById(inventory.getLogisticCenter().getLogisticCenterId());
        if (logisticCenter.isEmpty()) {
            throw new DataNotFoundEx("Logistic Center not found", "Logistic Center not found");
        }

        Optional<Warehouse> warehouse = warehouseRepo.findById(inventory.getWarehouse().getWarehouseId());
        if (warehouse.isEmpty()) {
            throw new DataNotFoundEx("Warehouse not found!", "Warehouse not found!");
        }

        Optional<Location> location = locationRepo.findById(inventory.getLocation().getLocationId());
        if (location.isEmpty()) {
            throw new DataNotFoundEx("Location not found!", "Location not found!");
        }

        //add checks for logistic center->warehouse->location relations
        if (!warehouse.get().getLogisticCenter().getLogisticCenterId().equals(logisticCenter.get().getLogisticCenterId())
        ) {
            throw new DataNotFoundEx("Can not found this warehouse in this logistic center",
                    "Please check the relation between logisticCenter and warehouse.");
        }

        Set<Location> locationsOfWarehouse = warehouse.get().getLocations();
        if (locationsOfWarehouse.stream().noneMatch(l -> l.getLocationId().equals(location.get().getLocationId()))) {
            throw new DataNotFoundEx("Can not find this location in the warehouse!", "Please check the warehouse-location table!");
        }

        return inventoryRepo.save(inventory);
    }

    public List<Inventory> getAll() {
        return inventoryRepo.findAllByOrderByInventoryCategory();
    }

    public Optional<Inventory> getById(UUID id) {
        return inventoryRepo.findById(id);
    }

}
