package com.example.inventorymanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryCategory {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String inventoryCategoryName;

}
