package com.dawn.microservice.productservice.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryDto implements Serializable {
    @Serial
    private static final Long serialVersionUID = 1L;

    private Long categoryId;
    private String categoryName;

    @JsonInclude(value = Include.NON_NULL)
    private Set<ProductDto> productDtos;
}
