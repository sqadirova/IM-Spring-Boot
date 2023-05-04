package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.Inventory;
import com.example.inventorymanagement.expection.DataDuplicationEx;
import com.example.inventorymanagement.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {
    private final InventoryRepo inventoryRepo;

    @Autowired
    public InventoryService(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    public Inventory save(Inventory inventory) {
        System.out.println("---inventory for save:");
        System.out.println(inventory);
        //todo add save foreign keys like - inventory_category_id, logistic_center_id, warehosue_id, location_id

        //todo check for unique rfid
        Optional<Inventory> firstByInventoryName = inventoryRepo.findFirstByInventoryRFID(inventory.getInventoryRFID());
        if (firstByInventoryName.isPresent()) {
            throw new DataDuplicationEx("This rfid already exists.", "InventoryRfid field duplicate dev msg");
        }
        //todo add checks for logisticCenterId,warehouseId,locationId exists or not?


        //todo add checks for logistic center->warehouse->(location unique)
        Optional<Inventory> firstByLocation = inventoryRepo.findFirstByLocation_LocationId(inventory.getLocation().getLocationId());
        if (firstByLocation.isPresent()) {
            throw new DataDuplicationEx("Inventory in this location already exists", "Location field duplicate");
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
