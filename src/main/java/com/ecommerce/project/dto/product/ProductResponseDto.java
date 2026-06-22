package com.ecommerce.project.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@JsonPropertyOrder({"product_id","product_name","price","description","color","size"})
@Data
public class ProductResponseDto {
    @JsonProperty("product_id")
    private Long id;
    @JsonProperty("product_name")
    private String productName;

    private Double price;
    private String description;

    private String size;
    private String color;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
