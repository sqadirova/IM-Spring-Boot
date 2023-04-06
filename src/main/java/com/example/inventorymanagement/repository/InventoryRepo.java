package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, UUID> {
    List<Inventory> findAllByOrderByInventoryCategory();
}
