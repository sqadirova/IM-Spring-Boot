package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.entity.InventoryCategory;
import com.example.inventorymanagement.expection.DataNotFoundEx;
import com.example.inventorymanagement.repository.InventoryCategoryRepo;
import com.example.inventorymanagement.service.InventoryCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory-category")
public class InventoryCategoryController {
    private final InventoryCategoryService inventoryCategoryService;
    private final InventoryCategoryRepo inventoryCategoryRepo;

    //    @Autowired
    public InventoryCategoryController(InventoryCategoryService inventoryCategoryService, InventoryCategoryRepo inventoryCategoryRepo) {
        this.inventoryCategoryService = inventoryCategoryService;
        this.inventoryCategoryRepo = inventoryCategoryRepo;
    }

    @GetMapping
    public ResponseEntity<List<InventoryCategory>> getAllInventoryCategories() {
        return new ResponseEntity<>(inventoryCategoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<InventoryCategory> getInventoryCategoryByID(@PathVariable("id") UUID id) {
        InventoryCategory inventoryCategory = inventoryCategoryService.getById(id).orElseThrow(() -> new DataNotFoundEx("Inventory category with " + id + " is not found!"));

        return new ResponseEntity<>(inventoryCategory, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InventoryCategory> createInventoryCategory(@RequestBody InventoryCategory inventoryCategory) {
        InventoryCategory createdInventoryCategory = inventoryCategoryService.save(inventoryCategory);
        return new ResponseEntity<>(createdInventoryCategory, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<InventoryCategory> updateInventoryCategory(@PathVariable("id") UUID id, @RequestBody InventoryCategory newInvCategory) {
        InventoryCategory inventoryCategory = inventoryCategoryRepo.findById(id).orElseThrow(() -> new DataNotFoundEx("Inventory category not found for update!"));

        inventoryCategory.setInventoryCategoryName(newInvCategory.getInventoryCategoryName());

        InventoryCategory updatedInventoryCategory = inventoryCategoryService.save(inventoryCategory);

        return new ResponseEntity<>(updatedInventoryCategory, HttpStatus.OK);
    }


}
