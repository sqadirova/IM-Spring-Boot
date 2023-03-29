package com.example.inventorymanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InventoryCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inventory_category_id")
    private UUID inventoryCategoryId;

    @Column(name = "inventory_category_name", unique = true, nullable = false)
    private String inventoryCategoryName;

}
