package com.ecommerce.project.Service;

import com.ecommerce.project.Entity.Order;
import com.ecommerce.project.Entity.Stock;
import com.ecommerce.project.Mapper.OrderMapper;
import com.ecommerce.project.Model.BaseResponseModel;
import com.ecommerce.project.Model.BaseResponseModelWithData;
import com.ecommerce.project.Repository.OrderRepository;
import com.ecommerce.project.Repository.StockRepository;
import com.ecommerce.project.dto.Order.OrderDto;
import com.ecommerce.project.dto.Order.OrderItemDto;
import com.ecommerce.project.dto.Order.OrderResponseDto;
import com.ecommerce.project.dto.Order.OrderUpdateDto;
import com.ecommerce.project.dto.stock.UpdateStockDto;
import com.ecommerce.project.exception.Model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private StockRepository stockRepository;
    public List<OrderResponseDto>  listOrder() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toResponseDtoList(orders);
    }
    public ResponseEntity<BaseResponseModel> createdOrder(OrderDto payload) {
        //map for product ids
        List<Long> productIds = payload.getOrderItems().stream()
                .map(item -> item.getProductId())
                .toList();

        // get stocks in productsIds
        List<Stock> stocks = stockRepository.findByProductIdIn(productIds,
                Sort.by(Sort.Direction.ASC, "createdAt"));

        //map for required qty of productIds
        Map<Long, Long> requiredQuantity = payload.getOrderItems().stream()
                .collect(Collectors.toMap(OrderItemDto::getProductId, OrderItemDto::getAmount));

        //deduct stock for each product
        for (Long productId : requiredQuantity.keySet()) {

            long remain = requiredQuantity.get(productId);
            //filter stock by productId
            List<Stock> stockByProduct = stocks.stream()
                    .filter(stock -> stock.getProduct().getId().equals(productId))
                    .toList();

            //for calculate and compare qty
            for (Stock stock : stockByProduct) {
                if (remain <= 0) break;

                long available = stock.getQuantity();

                if (available >= remain) {
                    stock.setQuantity(available - remain);
                    remain = 0;
                } else {
                    stock.setQuantity(0L);
                    remain -= available;
                }
            }
            if (remain > 0) {
                throw new RuntimeException("Not enough stock for product id: " + productId);

                //   Order order = orderMapper.toEntity(dto);
            }
        }

                //orderRepository.save(order);

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new BaseResponseModel("success", "successfully created order"));
            }
        public ResponseEntity<BaseResponseModel> updateOrderStatus(Long orderId, OrderUpdateDto payload){
            Order existingOrder = orderRepository.findById(orderId)
                    .orElseThrow( ()-> {
                        throw new ResourceNotFoundException("order not found with id :" +orderId);
                    });
            orderMapper.updateEntityDto(existingOrder,payload);

            orderRepository.save(existingOrder);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BaseResponseModel("success","successfully updated order status"));
        }
        public ResponseEntity<BaseResponseModel> deleteOrder(Long orderId) {
            Order existingOrder = orderRepository.findById(orderId)
                    .orElseThrow(() -> {
                        throw new ResourceNotFoundException("order not found with id : " + orderId);
                    });
            orderRepository.deleteById(orderId);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new BaseResponseModel("success","successfully deleted order id :" +orderId));
        }

        }


