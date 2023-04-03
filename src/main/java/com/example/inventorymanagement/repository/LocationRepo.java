package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationRepo extends JpaRepository<Location, UUID> {
}
