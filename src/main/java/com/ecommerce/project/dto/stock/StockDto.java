package com.ecommerce.project.dto.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class StockDto {
    private Long productId;

    private Long quantity;

}
