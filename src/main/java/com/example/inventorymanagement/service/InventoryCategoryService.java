package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.InventoryCategory;
import com.example.inventorymanagement.repository.InventoryCategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryCategoryService {
    private final InventoryCategoryRepo inventoryCategoryRepo;

    //    @Autowired
    public InventoryCategoryService(InventoryCategoryRepo repo) {
        this.inventoryCategoryRepo = repo;
    }

    public InventoryCategory save(InventoryCategory invCat) {
        return inventoryCategoryRepo.save(invCat);
    }

    public List<InventoryCategory> getAll() {
        return inventoryCategoryRepo.findAll();
    }

    public Optional<InventoryCategory> getById(UUID id) {
        return inventoryCategoryRepo.findById(id);
    }


}
