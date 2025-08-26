package com.manu.order_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.manu.order_service.model.Order}
 */
@Value
public class OrderDTO implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    String orderNumber;
    String skuCode;
    BigDecimal price;
    Integer quantity;
}