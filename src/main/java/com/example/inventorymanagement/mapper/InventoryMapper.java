package com.example.inventorymanagement.mapper;

import com.example.inventorymanagement.dto.response.InventoryResponseDTO;
import com.example.inventorymanagement.entity.Inventory;

public class InventoryMapper {
    // Convert Inventory JPA Entity into InventoryResponseDto
    public static InventoryResponseDTO mapToInventoryResponseDto(Inventory inventory) {
        return new InventoryResponseDTO(
                inventory.getInventoryId(),
                inventory.getInventoryName(),
                inventory.getInventoryRFID(),
                inventory.getActualQTY(),
                inventory.getCreatedAt(),
                inventory.getUpdatedAt(),
                inventory.getInventoryCategory(),
                inventory.getLogisticCenter(),
                inventory.getWarehouse(),
                inventory.getLocation()
        );
    }

    // Convert InventoryResponseDto into Inventory JPA Entity
    public static Inventory mapToInventory(InventoryResponseDTO inventoryDto) {
        return new Inventory(
                inventoryDto.getInventoryId(),
                inventoryDto.getInventoryName(),
                inventoryDto.getInventoryRFID(),
                inventoryDto.getActualQTY(),
                inventoryDto.getCreatedAt(),
                inventoryDto.getUpdatedAt(),
                inventoryDto.getInventoryCategory(),
                inventoryDto.getLogisticCenter(),
                inventoryDto.getWarehouse(),
                inventoryDto.getLocation()
        );
    }
}
