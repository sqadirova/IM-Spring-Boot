package com.example.inventorymanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
public class LogisticCenter {
    @Id
    @GeneratedValue
    @Column(name = "logistic_center_id")
    private UUID logisticCenterId;

    @Column(name = "logistic_center_name", unique = true, nullable = false)
    private String logisticCenterName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "logisticCenter", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Warehouse> warehouses;

}
