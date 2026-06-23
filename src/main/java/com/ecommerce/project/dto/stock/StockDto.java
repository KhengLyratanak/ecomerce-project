package com.ecommerce.project.dto.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class StockDto {
    private Long productId;
    @NotNull(message = "quantity is required")
    private Long quantity;

}
