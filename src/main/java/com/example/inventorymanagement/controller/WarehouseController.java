package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.dto.request.WarehouseLocationRequestDTO;
import com.example.inventorymanagement.entity.Warehouse;
import com.example.inventorymanagement.expection.DataNotFoundEx;
import com.example.inventorymanagement.repository.WarehouseRepo;
import com.example.inventorymanagement.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseController.class);

    private final WarehouseService warehouseService;
    private final WarehouseRepo warehouseRepo;

    public WarehouseController(WarehouseService warehouseService, WarehouseRepo warehouseRepo) {
        this.warehouseService = warehouseService;
        this.warehouseRepo = warehouseRepo;
    }


    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return new ResponseEntity<>(warehouseService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/myLocations")
//    @ResponseBody
    public ResponseEntity<?> getLocationsOfWarehouse(@PathVariable("warehouseId") UUID warehouseId) {
//        Optional<Location> allLocationsByWarehouseId = warehouseRepo.findAllLocationsByWarehouseId(warehouseId);
        Optional<Warehouse> warehouse = warehouseRepo.findById(warehouseId);
        System.out.println(warehouse.get().getLocations());

        LOGGER.info("---locations: ");
        System.out.println(warehouse.get().getLocations());

        return new ResponseEntity<>(warehouse.get().getLocations(), HttpStatus.OK);
    }

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


    @PostMapping(value = "/add_location")
    public ResponseEntity<String> createWarehouseLocation(@RequestBody WarehouseLocationRequestDTO warehouseLocationRequestDto) {
        boolean isCreated = warehouseService.createWarehouseLocationRelation(warehouseLocationRequestDto);

        //todo add error handling and response format
        if (!isCreated) {
            return new ResponseEntity<>("Warehouse-Location relation can not created", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Warehouse-Location relation was created", HttpStatus.CREATED);
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

        LOGGER.info("---warehouse: ");
        System.out.println(warehouse);

        LOGGER.info("---delete id: ");
        System.out.println(warehouse.getWarehouseId());
//todo fix problem
        warehouseService.deleteById(warehouse.getWarehouseId());

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
