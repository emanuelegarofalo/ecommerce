package com.manu.product_service.config.mapper;

import com.manu.product_service.dto.ProductDTO;
import com.manu.product_service.model.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(ProductDTO productDTO, @MappingTarget Product product);

    Product toProduct(ProductDTO productDTO);
}
