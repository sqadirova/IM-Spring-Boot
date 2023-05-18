package com.example.inventorymanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

//todo need to add swagger to project

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Inventory {

    @Id
    @GeneratedValue
    @Column(name = "inventory_id")
    private UUID inventoryId;

    @Column(name = "inventory_name", nullable = false)
    private String inventoryName;

    @Column(name = "inventory_rfid", nullable = false, unique = true)
    private String inventoryRFID;

    @Column(name = "actual_qty", nullable = false)
    private String actualQTY;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inventory_category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private InventoryCategory inventoryCategory;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "logistic_center_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LogisticCenter logisticCenter;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Warehouse warehouse;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Location location;
}
