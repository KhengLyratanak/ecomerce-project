package com.ecommerce.project.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotBlank(message = "Product name is required")
    private  String name;
    @NotNull(message = "Product price is required")
    @Positive(message = "Price must be positive" )
    private Double price;
    private String description;
    @NotNull(message = "size must be select")
    private String size;
    @NotNull(message = "color must be select")
    private String color;
}
