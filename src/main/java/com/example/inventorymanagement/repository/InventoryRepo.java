package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.entity.Inventory;
import com.example.inventorymanagement.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, UUID> {
    // todo you can do with query too
//    @Query(value = "SELECT * FROM admin WHERE username = :username AND status = 'Y'", nativeQuery = true)
    List<Inventory> findAllByOrderByInventoryCategory();

    Optional<Inventory> findFirstByInventoryRFID(String rfid);

    Optional<Inventory> findFirstByLocation(Location location);
}
