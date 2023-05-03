package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.entity.Inventory;
import com.example.inventorymanagement.expection.DataNotFoundEx;
import com.example.inventorymanagement.repository.InventoryRepo;
import com.example.inventorymanagement.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/inventory")
public class InventoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);

    private final InventoryService inventoryService;
    private final InventoryRepo inventoryRepo;

    @Autowired
    public InventoryController(InventoryService inventoryService, InventoryRepo inventoryRepo) {
        this.inventoryService = inventoryService;
        this.inventoryRepo = inventoryRepo;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        return new ResponseEntity<>(inventoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Inventory> getInventoryByID(@PathVariable("id") UUID id) {
        Inventory inventory = inventoryService.getById(id).orElseThrow(() -> new DataNotFoundEx("Inventory with is not found!","Inventory with " + id + " is not found!"));

        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory createdInventory = inventoryService.save(inventory);

        return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable("id") UUID id, @RequestBody Inventory newInventory) {
        Inventory inventory = inventoryRepo.findById(id).orElseThrow(() -> new DataNotFoundEx("Inventory category not found for update!",""));

        inventory.setInventoryName(newInventory.getInventoryName());
        inventory.setInventoryRFID(newInventory.getInventoryRFID());
        inventory.setActualQTY(newInventory.getActualQTY());
//        todo add set foreign keys
//        inventory.setInventoryCategory(newInventory.getInventoryCategory());
//        inventory.setLogisticCenter(newInventory.getLogisticCenter());
//        inventory.setWarehouse(newInventory.getWarehouse());
//        inventory.setLocation(newInventory.getLocation());


        Inventory updatedInventory = inventoryService.save(inventory);

        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable("id") UUID id) {
        Inventory inventoryForDelete = inventoryRepo.findById(id).orElseThrow(() -> new DataNotFoundEx("Inventory is not found!","Inventory with " + id + " is not found!"));

        LOGGER.info("Contact Administrator to delete inventory.");
        return new ResponseEntity<>("Contact Administrator to delete inventory with this id: " + inventoryForDelete.getInventoryId(), HttpStatus.BAD_REQUEST);
    }
}
