package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.entity.Location;
import com.example.inventorymanagement.entity.Warehouse;
import com.example.inventorymanagement.expection.DataNotFoundEx;
import com.example.inventorymanagement.repository.WarehouseRepo;
import com.example.inventorymanagement.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final WarehouseRepo warehouseRepo;

    public WarehouseController(WarehouseService warehouseService, WarehouseRepo warehouseRepo) {
        this.warehouseService = warehouseService;
        this.warehouseRepo = warehouseRepo;
    }


    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        System.out.println("---Warehouses: ");
        System.out.println(warehouseService.getAll());
        return new ResponseEntity<>(warehouseService.getAll(), HttpStatus.OK);
    }

//    @GetMapping(value = "/location/by/warehouseId")
//    public ResponseEntity<Optional<Location>> getAllLocationsByWarehouse(@PathVariable("warehouseId") UUID warehouseId) {
//        return new ResponseEntity<>(warehouseService.getAllLocationsByWarehouse(warehouseId), HttpStatus.OK);
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Warehouse> getWarehouseByID(@PathVariable("id") UUID id) {
        Warehouse warehouse = warehouseService.getById(id)
                .orElseThrow(() -> new DataNotFoundEx("Warehouse with " + id + " is not found!"));
//        System.out.println(warehouse.getLogisticCenter());
//        System.out.println(warehouse.getLocations());
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    //   todo  need to be add mapper to dto
    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        //logistic_center_id ???
        Warehouse createdWarehouse = warehouseService.save(warehouse);
        return new ResponseEntity<>(createdWarehouse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable("id") UUID id, @RequestBody Warehouse newWarehouse) {
        Warehouse warehouse = warehouseRepo.findById(id).orElseThrow(() -> new DataNotFoundEx("Warehouse not found for update!"));

        //todo need to be testing
        warehouse.setWarehouseName(newWarehouse.getWarehouseName());
        warehouse.setLogisticCenter(newWarehouse.getLogisticCenter());

        Warehouse updatedWarehouse = warehouseService.save(warehouse);

        return new ResponseEntity<>(updatedWarehouse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Warehouse> deleteWarehouse(@PathVariable("id") UUID id) {
        Warehouse warehouse = warehouseRepo.findById(id).orElseThrow(() -> new DataNotFoundEx("Warehouse not found for delete!"));

        warehouseService.deleteById(warehouse.getWarehouseId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
