package com.example.inventorymanagement.dto.response;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseLocationResponseDTO {
    @Id
    @Column(name = "warehouse_id")
    private UUID warehouseId;

    @Id
    @Column(name = "location_id")
    private UUID locationId;
}
