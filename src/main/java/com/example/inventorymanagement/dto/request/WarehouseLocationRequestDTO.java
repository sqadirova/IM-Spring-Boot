package com.example.inventorymanagement.dto.request;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseLocationRequestDTO {
    private UUID warehouseId;

    private UUID locationId;
}
