package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.LogisticCenter;
import com.example.inventorymanagement.repository.LogisticCenterRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LogisticCenterService {
    private final LogisticCenterRepo logisticCenterRepo;

    public LogisticCenterService(LogisticCenterRepo logisticCenterRepo) {
        this.logisticCenterRepo = logisticCenterRepo;
    }

    public LogisticCenter save(LogisticCenter logisticCenter) {
        return logisticCenterRepo.save(logisticCenter);
    }

    public List<LogisticCenter> getAll() {
        return logisticCenterRepo.findAll();
    }

    public Optional<LogisticCenter> getById(UUID id) {
        return logisticCenterRepo.findById(id);
    }

    public void deleteById(UUID id) {
        logisticCenterRepo.deleteById(id);
    }

}
