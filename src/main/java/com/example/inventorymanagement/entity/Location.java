package com.example.inventorymanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Location {
    @Id
    @GeneratedValue
    @Column(name = "location_id", nullable = false)
    private UUID locationId;

    @Column(name = "location_name", nullable = false)
    private String locationName;


    @ToString.Exclude
    @JsonBackReference
    @ManyToMany(mappedBy = "locations", cascade = CascadeType.ALL)
//    @JsonIgnore
    private Set<Warehouse> warehouses = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;


}
