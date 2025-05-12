package com.dawn.microservice.productservice.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto implements Serializable {
    @Serial
    private static final Long serialVersionUID = 1L;
    
    private Long productId;
    private String productTitle;
    private String sku;
    private Double priceUnit;
    private Integer quantity;

    private Long categoryId;
}
