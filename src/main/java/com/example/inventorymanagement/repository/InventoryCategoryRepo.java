package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.entity.InventoryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryCategoryRepo extends JpaRepository<InventoryCategory,Integer> {
}
