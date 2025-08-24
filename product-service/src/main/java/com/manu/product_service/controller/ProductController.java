package com.manu.product_service.controller;

import com.manu.product_service.dto.OnCreate;
import com.manu.product_service.dto.OnUpdate;
import com.manu.product_service.dto.ProductDTO;
import com.manu.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Validated(OnCreate.class) ProductDTO productDTO) {
        ProductDTO productCreated = productService.createProduct(productDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // → /resource
                .path("/{id}")        // → /resource/{id}
                .buildAndExpand(productCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(productCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> partialUpdate(@RequestBody @Validated(OnUpdate.class) ProductDTO productDTO, @PathVariable String id) {
        return ResponseEntity.ok(productService.partialUpdate(productDTO, id));
    }
}
