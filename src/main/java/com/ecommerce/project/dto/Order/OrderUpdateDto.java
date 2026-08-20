package com.ecommerce.project.dto.Order;

import com.ecommerce.project.common.annotation.ValidEnum;
import com.ecommerce.project.common.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderUpdateDto {
    @JsonProperty("status")
    @ValidEnum(enumClass = OrderStatus.class,message = "value must be only SUCCESS,FAIL,PENDING")
    private String status;
}
