package com.manu.inventoryservice.service;

import com.manu.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository repo;

    public boolean isInStock(String skuCode, Integer quantity) {
        return repo.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }
}
