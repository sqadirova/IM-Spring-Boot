package com.example.inventorymanagement.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InventoryCategory {
    @Id
    @GeneratedValue
    @Column(name = "inventory_category_id")
    private UUID inventoryCategoryId;

    @Column(name = "inventory_category_name", unique = true, nullable = false)
    private String inventoryCategoryName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

}
