package com.example.inventorymanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Warehouse {
    @Id
    @GeneratedValue
    @Column(name = "warehouse_id")
    private UUID warehouseId;

    @Column(name = "warehouse_name", unique = true, nullable = false)
    private String warehouseName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "logictic_center_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LogisticCenter logisticCenter;

    @ToString.Exclude
    @JsonManagedReference
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "warehouse_location",
            joinColumns = {@JoinColumn(name = "warehouse_id", referencedColumnName = "warehouse_id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id", referencedColumnName = "location_id")})
    private Set<Location> locations;


    public void addLocation(Location loc) {
        this.locations.add(loc);
        loc.getWarehouses().add(this);
    }

    public void removeLocation(UUID locationId) {
        Location loc = this.locations.stream().filter(l -> l.getLocationId() == locationId).findFirst().orElse(null);
        if (loc != null) {
            this.locations.remove(loc);
            loc.getWarehouses().remove(this);
        }
    }

}
