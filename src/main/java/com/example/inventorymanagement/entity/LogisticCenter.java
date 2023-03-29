package com.example.inventorymanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class LogisticCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "logistic_center_id")
    private UUID logisticCenterId;

    @Column(name = "logistic_center_name", unique = true, nullable = false)
    private String logisticCenterName;

//    @JsonManagedReference
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    @JoinTable(name = "logistic_center_warehouse",
//            joinColumns = {@JoinColumn(name = "logistic_center_id", referencedColumnName = "logistic_center_id")},
//            inverseJoinColumns = {@JoinColumn(name = "warehouse_id", referencedColumnName = "warehouse_id")})
//    private Set<Warehouse> warehouses;


}
