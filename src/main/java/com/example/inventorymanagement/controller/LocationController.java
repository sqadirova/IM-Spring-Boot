package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.dto.WarehouseLocationDTO;
import com.example.inventorymanagement.entity.Location;
import com.example.inventorymanagement.expection.DataNotFoundEx;
import com.example.inventorymanagement.repository.LocationRepo;
import com.example.inventorymanagement.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    private final LocationService locationService;
    private final LocationRepo locationRepo;

    public LocationController(LocationService locationService,
                              LocationRepo locationRepo) {
        this.locationService = locationService;
        this.locationRepo = locationRepo;
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        return new ResponseEntity<>(locationService.getAll(), HttpStatus.OK);
    }

//    @GetMapping(value = "/location/by/warehouseId")
//    public ResponseEntity<Optional<Location>> getAllLocationsByLocation(@PathVariable("warehouseId") UUID warehouseId) {
//        return new ResponseEntity<>(locationService.getAllLocationsByLocation(warehouseId), HttpStatus.OK);
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Location> getLocationByID(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(locationService.getById(id).orElseThrow(() -> new DataNotFoundEx("Location with " + id + " is not found!")), HttpStatus.OK);
    }

    //   todo  need to be test
    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location createdLocation = locationService.save(location);
        //   todo  need to be add mapper to dto
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }

    @PostMapping(value = "/add_to_warehouse")
    public ResponseEntity<Location> addLocationToWarehouse(@RequestBody WarehouseLocationDTO warehouseLocationDto) {
        //   todo  need to be add mapper to dto


        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable("id") UUID id, @RequestBody Location newLocation) {
        Location location = locationRepo.findById(id).orElseThrow(() -> new DataNotFoundEx("Location not found for update!"));

        location.setLocationName(newLocation.getLocationName());
        //todo add warehouse_location relation to table

        Location updatedLocation = locationService.save(location);

        return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Location> deleteLocation(@PathVariable("id") UUID id) {
        Location location = locationRepo.findById(id).orElseThrow(() -> new DataNotFoundEx("Location not found for delete!"));

        locationService.deleteById(location.getLocationId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
