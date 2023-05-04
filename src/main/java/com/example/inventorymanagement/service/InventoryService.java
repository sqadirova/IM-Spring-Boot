package com.example.inventorymanagement.service;

import com.example.inventorymanagement.dto.response.WarehouseLocationResponseDTO;
import com.example.inventorymanagement.entity.Inventory;
import com.example.inventorymanagement.entity.Location;
import com.example.inventorymanagement.entity.LogisticCenter;
import com.example.inventorymanagement.entity.Warehouse;
import com.example.inventorymanagement.expection.DataDuplicationEx;
import com.example.inventorymanagement.expection.DataNotFoundEx;
import com.example.inventorymanagement.repository.InventoryRepo;
import com.example.inventorymanagement.repository.LocationRepo;
import com.example.inventorymanagement.repository.LogisticCenterRepo;
import com.example.inventorymanagement.repository.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {
    private final InventoryRepo inventoryRepo;
    private final LogisticCenterRepo logisticCenterRepo;
    private final WarehouseRepo warehouseRepo;
    private final LocationRepo locationRepo;

    @Autowired
    public InventoryService(InventoryRepo inventoryRepo,
                            LogisticCenterRepo logisticCenterRepo,
                            WarehouseRepo warehouseRepo,
                            LocationRepo locationRepo) {
        this.inventoryRepo = inventoryRepo;
        this.logisticCenterRepo = logisticCenterRepo;
        this.warehouseRepo = warehouseRepo;
        this.locationRepo = locationRepo;
    }

    public Inventory save(Inventory inventory) {
        System.out.println("---inventory for save:");
        System.out.println(inventory);
        //todo add save foreign keys like - inventory_category_id, logistic_center_id, warehosue_id, location_id

        //check for unique rfid
        Optional<Inventory> firstByInventoryName = inventoryRepo.findFirstByInventoryRFID(inventory.getInventoryRFID());
        if (firstByInventoryName.isPresent()) {
            throw new DataDuplicationEx("This rfid already exists.", "InventoryRfid field duplicate dev msg");
        }

        //checks for location unique or not
        Optional<Inventory> firstByLocation = inventoryRepo.findFirstByLocation_LocationId(inventory.getLocation().getLocationId());
        if (firstByLocation.isPresent()) {
            throw new DataDuplicationEx("Inventory in this location already exists", "Location field duplicate");
        }

        //checks for logisticCenterId,warehouseId,locationId exists or not?
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

        //todo add checks for logistic center->warehouse->location relations
        if (!warehouse.get().getLogisticCenter().getLogisticCenterId().equals(logisticCenter.get().getLogisticCenterId())
        ) {
            throw new DataNotFoundEx("Can not found this warehouse in this logistic center",
                    "Please check the relation between logisticCenter and warehouse.");
        }

        //todo fix that
        Optional<WarehouseLocationResponseDTO> warehouseLocationRelation = warehouseRepo.getWarehouseLocationRelationByLocationId(location.get().getLocationId());
        if (warehouseLocationRelation.isEmpty()) {
            throw new DataNotFoundEx("Can not find this location in the warehouse!", "Please check the warehouse-location table!");
        }

        return inventoryRepo.save(inventory);
    }

    public List<Inventory> getAll() {
//        System.out.println("---- Find all and order by category: ");
//        System.out.println(inventoryRepo.findAllByOrderByInventoryCategory());

        //inventoryRepo.findAll();
        return inventoryRepo.findAllByOrderByInventoryCategory();
    }

    public Optional<Inventory> getById(UUID id) {
        return inventoryRepo.findById(id);
    }

}
