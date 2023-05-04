package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.dto.response.WarehouseLocationResponseDTO;
import com.example.inventorymanagement.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, UUID> {
//    List<Warehouse> findAllByLogisticCenter(UUID logisticCenterId);
//
//    Warehouse findFirstByWarehouseId(UUID warehouseId);

//    @Query(value = "SELECT * FROM admin WHERE username = :username AND status = 'Y'", nativeQuery = true)
//    Optional<Warehouse> findAllLocationsByWarehouseId(UUID warehouseId);

    @Transactional
    @Modifying
    @Query(value = "insert into warehouse_location (warehouse_id, location_id) values (:warehouseId,:locationId);", nativeQuery = true)
    void createWarehouseLocationRelation(@Param("warehouseId") UUID warehouseId,
                                         @Param("locationId") UUID locationId);

//    @Transactional
//    @Modifying
    @Query(value = "select wl.warehouse_id as warehouse_id,wl.location_id as location_id from warehouse_location wl where wl.location_id=:locationId", nativeQuery = true)
    Optional<WarehouseLocationResponseDTO> getWarehouseLocationRelationByLocationId(@Param("locationId") UUID locationId);


}
