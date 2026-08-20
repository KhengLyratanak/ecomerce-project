package com.ecommerce.project.Mapper;

import com.ecommerce.project.Entity.Order;
import com.ecommerce.project.Entity.OrderItem;
import com.ecommerce.project.dto.Order.OrderDto;
import com.ecommerce.project.dto.Order.OrderItemResponseDto;
import com.ecommerce.project.dto.Order.OrderResponseDto;
import com.ecommerce.project.dto.Order.OrderUpdateDto;
import com.ecommerce.project.dto.stock.UpdateStockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    @Autowired
    private OrderItemMapper mapper;

    public Order toEntity(OrderDto dto){
        Order entity = new Order();

        List<OrderItem> orderItemEntities = dto.getOrderItems()
                .stream()
                .map(orderItemDto-> {
                    OrderItem orderItem = mapper.toEntity(orderItemDto);
                    orderItem.setOrder(entity);
                    return orderItem;
                })
                .toList();
        entity.setItems(orderItemEntities);
        return entity;
    }
    public OrderResponseDto orderResponseDto(Order entity){
        if (entity == null){
            return null;
        }

        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setStatus(entity.getStatus());

        if (entity.getItems() !=null && !entity.getItems().isEmpty()){
            List<OrderItemResponseDto> orderItemDtos =mapper.toResponseDtoList(entity.getItems());

            dto.setItems(orderItemDtos);

            //map for total price;
            Double total = orderItemDtos.stream()
                    .mapToDouble(orderItemResponseDto ->  {
                        return  orderItemResponseDto.getPurchasesAmount() * orderItemResponseDto.getUnitPrice();
                    })
                    .sum();

                    dto.setTotal(total);
        }
                return dto;
    }
        public List<OrderResponseDto> toResponseDtoList(List<Order> entities){
                return entities.stream()
                        .map(order -> this.orderResponseDto(order))
                        .toList();
        }
        public void updateEntityDto(Order entity, OrderUpdateDto dto){
            if (entity == null || dto == null)
                return;
            entity.setStatus(dto.getStatus());
        }
}
