package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.entity.Warehouse;
import com.example.inventorymanagement.expection.DataNotFoundEx;
import com.example.inventorymanagement.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }


    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return new ResponseEntity<>(
                warehouseService.getAll(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Warehouse> getWarehouseByID(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(
                warehouseService.getById(id).orElseThrow(() -> new DataNotFoundEx("Warehouse with " + id + " is not found!")),
                HttpStatus.OK
        );
    }



}
