package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.entity.LogisticCenter;
import com.example.inventorymanagement.entity.Warehouse;
import com.example.inventorymanagement.expection.DataNotFoundEx;
import com.example.inventorymanagement.repository.LogisticCenterRepo;
import com.example.inventorymanagement.service.LogisticCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/logistic-center")
public class LogisticCenterController {

    private final LogisticCenterService logisticCenterService;
    private final LogisticCenterRepo logisticCenterRepo;

    @Autowired
    public LogisticCenterController(LogisticCenterService logisticCenterService, LogisticCenterRepo logisticCenterRepo) {
        this.logisticCenterService = logisticCenterService;
        this.logisticCenterRepo = logisticCenterRepo;
    }

    @GetMapping
    public ResponseEntity<List<LogisticCenter>> getAllLogisticCenters() {
        return new ResponseEntity<>(
                logisticCenterService.getAll(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/by/logisticCenterId")
    public ResponseEntity<Set<Warehouse>> getAllWarehousesByLogCen(@PathVariable("logisticCenterId") UUID logisticCenterId) {
        return new ResponseEntity<>(
                logisticCenterService.getAllWarehousesByLogCenId(logisticCenterId),
                HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LogisticCenter> getLogisticCenterByID(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(
                logisticCenterService.getById(id).orElseThrow(() -> new DataNotFoundEx("Logistic center with " + id + " is not found!")),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<LogisticCenter> createLogisticCenter(@RequestBody LogisticCenter logisticCenter) {
        LogisticCenter createdLogisticCenter = logisticCenterService.save(logisticCenter);
        return new ResponseEntity<>(createdLogisticCenter, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LogisticCenter> updateLogisticCenter(@PathVariable("id") UUID id, @RequestBody LogisticCenter newLogisticCenter) {
        LogisticCenter logisticCenter = logisticCenterRepo.findById(id).orElseThrow(() -> new DataNotFoundEx("Logistic Center not found for update!"));

        logisticCenter.setLogisticCenterName(newLogisticCenter.getLogisticCenterName());

        LogisticCenter updatedLogisticCenter = logisticCenterService.save(logisticCenter);

        return new ResponseEntity<>(updatedLogisticCenter, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<LogisticCenter> deleteInventoryCategory(@PathVariable("id") UUID id) {
        LogisticCenter logisticCenter = logisticCenterRepo.findById(id).orElseThrow(() -> new DataNotFoundEx("Logistic Center not found for delete!"));

        logisticCenterService.deleteById(logisticCenter.getLogisticCenterId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
