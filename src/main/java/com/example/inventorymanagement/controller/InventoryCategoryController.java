package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.entity.InventoryCategory;
import com.example.inventorymanagement.expection.DataNotFoundEx;
import com.example.inventorymanagement.service.InventoryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory-category")
public class InventoryCategoryController {
    private final InventoryCategoryService inventoryCategoryService;

    @Autowired
    public InventoryCategoryController(InventoryCategoryService inventoryCategoryService) {
        this.inventoryCategoryService = inventoryCategoryService;
    }

    @GetMapping
    public List<InventoryCategory> getAllInventoryCategories() {
        return inventoryCategoryService.getAll();
    }

    @GetMapping(value = "/{id}")
    public InventoryCategory getInventoryCategoryByID(@PathVariable("id") int id) {
        return inventoryCategoryService.getById(id)
                .orElseThrow(() -> new DataNotFoundEx("Inventory category with " + id + "is not found!"));
    }

    @PostMapping
    public InventoryCategory createInventoryCategory(@RequestBody InventoryCategory inventoryCategory) {
        return inventoryCategoryService.save(inventoryCategory);
    }
}
