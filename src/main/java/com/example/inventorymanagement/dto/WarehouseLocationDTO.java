package com.example.inventorymanagement.dto;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseLocationDTO {
    private UUID warehouseId;

    private UUID locationId;
}
