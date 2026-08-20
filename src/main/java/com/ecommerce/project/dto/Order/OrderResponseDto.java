package com.ecommerce.project.dto.Order;

import com.ecommerce.project.Entity.OrderItem;
import com.ecommerce.project.Repository.OrderItemRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@JsonPropertyOrder({"items","status","total","id","created_at","updated_at"})
@Data
public class OrderResponseDto {
    @JsonProperty("order_id")
    private Long id;

    private String status;

    private Double total;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("items")
    private List<OrderItemResponseDto> items;
}
