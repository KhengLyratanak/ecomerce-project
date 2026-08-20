package com.ecommerce.project.Controller;

import com.ecommerce.project.Entity.Order;
import com.ecommerce.project.Model.BaseResponseModel;
import com.ecommerce.project.Model.BaseResponseModelWithData;
import com.ecommerce.project.Service.OrderService;
import com.ecommerce.project.dto.Order.OrderDto;
import com.ecommerce.project.dto.Order.OrderResponseDto;
import com.ecommerce.project.dto.Order.OrderUpdateDto;
import com.ecommerce.project.dto.base.Response;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v10/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping
    public ResponseEntity<BaseResponseModel> placeOrder(@Valid @RequestBody OrderDto payload){
        return orderService.createdOrder(payload);
    }
    @GetMapping
    public ResponseEntity<Response> listOrder(){
        List<OrderResponseDto> orders = orderService.listOrder();
        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.success("200","success","successfully retrieved order"));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponseModel> updateOrderStatus(@Valid @PathVariable ("id") Long orderId, @RequestBody OrderUpdateDto payload){
     return    orderService.updateOrderStatus(orderId,payload);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseModel> deleteOrder(@PathVariable ("id") Long orderId){
        return orderService.deleteOrder(orderId);
    }
}
