package com.ecommerce.project.dto.Order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderItemResponseDto {
    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("purchase_amount")
    private Long purchasesAmount;

    @JsonProperty("unit_price")
    private Double unitPrice;
}
