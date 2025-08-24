package com.manu.product_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.manu.product_service.model.Product}
 */
@Value
public class ProductDTO implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String id;
    @NotBlank(message = "Product name cannot be blank", groups = OnCreate.class)
    String name;
    @NotBlank(message = "Product description cannot be blank", groups = OnCreate.class)
    String description;
    @Positive(message = "Product price must be positive", groups = {OnCreate.class, OnUpdate.class})
    BigDecimal price;
}