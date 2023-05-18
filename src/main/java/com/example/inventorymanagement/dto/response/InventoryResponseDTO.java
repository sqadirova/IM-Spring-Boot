package com.example.inventorymanagement.dto.response;

import com.example.inventorymanagement.entity.InventoryCategory;
import com.example.inventorymanagement.entity.Location;
import com.example.inventorymanagement.entity.LogisticCenter;
import com.example.inventorymanagement.entity.Warehouse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDTO {
    private UUID inventoryId;

    private String inventoryName;

    private String inventoryRFID;

    private String actualQTY;

    private Date createdAt;

    private Date updatedAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private InventoryCategory inventoryCategory;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LogisticCenter logisticCenter;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Warehouse warehouse;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Location location;
}
