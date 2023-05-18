package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, UUID> {
    @Transactional
    @Modifying
    @Query(value = "insert into warehouse_location (warehouse_id, location_id) values (:warehouseId,:locationId);", nativeQuery = true)
    void createWarehouseLocationRelation(@Param("warehouseId") UUID warehouseId,
                                         @Param("locationId") UUID locationId);
}
