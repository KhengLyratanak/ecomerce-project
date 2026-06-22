package com.ecommerce.project.Mapper;

import com.ecommerce.project.Entity.Product;
import com.ecommerce.project.Entity.Stock;
import com.ecommerce.project.dto.stock.StockDto;
import com.ecommerce.project.dto.stock.StockResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {

    public Stock toEntity(StockDto dto, Product product){
        Stock entity = new Stock();
        entity.setQuantity(dto.getQuantity());
        entity.setProduct(product);

        return entity;
    }

    public StockResponseDto toDto (Stock stock){

        StockResponseDto dto = new StockResponseDto();
        dto.setStockId(stock.getStockId());
        dto.setProductId(stock.getProduct().getId());
        dto.setQuantity(stock.getQuantity());
        dto.setCreatedAt(stock.getCreatedAt());
        dto.setUpdatedAt(stock.getUpdatedAt());

        return dto;
    }

    public List<StockResponseDto> toDtoList(List<Stock> entities){
        if (entities==null || entities.isEmpty()){
            return new ArrayList<>();
        }
        return entities.stream()
                .map( stock -> this.toDto(stock))
                .collect(Collectors.toList());
    }

}