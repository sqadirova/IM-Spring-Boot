package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.Inventory;
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
