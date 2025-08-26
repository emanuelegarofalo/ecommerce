package com.manu.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory", url = "http://localhost:8082/api/inventory")
public interface InventoryClient {
    @GetMapping
    boolean isInStock(@RequestParam String skuCode,@RequestParam Integer quantity);
}
