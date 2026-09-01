package com.ecommerce.project.controller;

import com.ecommerce.project.service.OrderService;
import com.ecommerce.project.dto.Order.OrderDto;
import com.ecommerce.project.dto.Order.OrderResponseDto;
import com.ecommerce.project.dto.Order.OrderUpdateDto;
import com.ecommerce.project.dto.base.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v10/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping
    public ResponseEntity<Response> placeOrder(@Valid @RequestBody OrderDto payload){
         orderService.createdOrder(payload);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("201","success","successfully placed order"));
    }
    @GetMapping
    public ResponseEntity<Response> listOrder(){
        List<OrderResponseDto> orders = orderService.listOrder();
        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.success("200","success","successfully retrieved order",orders));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Response> updateOrderStatus(@Valid @PathVariable ("id") Long orderId, @RequestBody OrderUpdateDto payload){
             orderService.updateOrderStatus(orderId,payload);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Response.success("200","success","successfully updated order"));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteOrder(@PathVariable ("id") Long orderId){
         orderService.deleteOrder(orderId);
         return ResponseEntity.status(HttpStatus.OK)
                 .body(Response.success("200","success","successfully deletes order"));
    }
}
