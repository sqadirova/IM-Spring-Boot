package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.entity.LogisticCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LogisticCenterRepo extends JpaRepository<LogisticCenter, UUID> {
//    LogisticCenter findFirstByWarehouses(UUID warehouseId);
}
