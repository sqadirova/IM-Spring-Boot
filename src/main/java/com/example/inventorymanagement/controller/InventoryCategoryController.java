package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.entity.InventoryCategory;
import com.example.inventorymanagement.expection.DataNotFoundEx;
import com.example.inventorymanagement.repository.InventoryCategoryRepo;
import com.example.inventorymanagement.service.InventoryCategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory-category")
public class InventoryCategoryController {
    private final InventoryCategoryService inventoryCategoryService;
    private final InventoryCategoryRepo inventoryCategoryRepo;

    //    @Autowired
    public InventoryCategoryController(InventoryCategoryService inventoryCategoryService,
                                       InventoryCategoryRepo inventoryCategoryRepo) {
        this.inventoryCategoryService = inventoryCategoryService;
        this.inventoryCategoryRepo = inventoryCategoryRepo;
    }

    @GetMapping
    public List<InventoryCategory> getAllInventoryCategories() {
        return inventoryCategoryService.getAll();
    }

    @GetMapping(value = "/{id}")
    public InventoryCategory getInventoryCategoryByID(@PathVariable("id") UUID id) {
        return inventoryCategoryService.getById(id)
                .orElseThrow(() -> new DataNotFoundEx("Inventory category with " + id + " is not found!"));
    }

    @PostMapping
    public InventoryCategory createInventoryCategory(@RequestBody InventoryCategory inventoryCategory) {
        return inventoryCategoryService.save(inventoryCategory);
    }

    @PutMapping(value = "/{id}")
    public InventoryCategory updateInventoryCategory(@PathVariable("id") UUID id, @RequestBody InventoryCategory newInvCategory) {
        InventoryCategory inventoryCategory = inventoryCategoryRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundEx("Inventory category not found for update!"));

        inventoryCategory.setInventoryCategoryName(newInvCategory.getInventoryCategoryName());

        return inventoryCategoryService.save(inventoryCategory);
    }
}
