package com.manu.inventoryservice.controller;

import com.manu.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService service;

    @GetMapping
    public ResponseEntity<Boolean> isInStock(@RequestParam String skuCode,@RequestParam Integer quantity) {
        return ResponseEntity.ok(service.isInStock(skuCode,quantity));
    }
}
