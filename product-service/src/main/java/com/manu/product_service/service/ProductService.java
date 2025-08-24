package com.manu.product_service.service;

import com.manu.product_service.config.mapper.ProductMapper;
import com.manu.product_service.dto.ProductDTO;
import com.manu.product_service.exception.ProductNotFoundException;
import com.manu.product_service.model.Product;
import com.manu.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product productSaved = productRepository.save(productMapper.toProduct(productDTO));
        return productMapper.toProductDTO(productSaved);
    }

    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toProductDTO(product);
    }

    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream().map(productMapper::toProductDTO).toList();
    }

    @Transactional
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
    }

    @Transactional
    public ProductDTO partialUpdate(ProductDTO productDTO, String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productMapper.partialUpdate(productDTO, product);
        Product productUpdated = productRepository.save(product);
        return productMapper.toProductDTO(productUpdated);
    }
}
