package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, UUID> {
//    List<Warehouse> findAllByLogisticCenter(UUID logisticCenterId);
//
//    Warehouse findFirstByWarehouseId(UUID warehouseId);
}
