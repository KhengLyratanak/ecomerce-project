package com.ecommerce.project.Mapper;

import com.ecommerce.project.Entity.Product;
import com.ecommerce.project.dto.product.ProductDto;
import com.ecommerce.project.dto.product.ProductResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Product toEntity(ProductDto dto){
        Product entity = new Product();
        entity.setProductName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setSize(dto.getSize());
        entity.setColor(dto.getColor());
      return entity;
    }
    public void updateEntityFromDto(Product entity, ProductDto dto){
        if (entity == null || dto == null){
            return;
        }
        entity.setProductName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setSize(dto.getSize());
        entity.setColor(dto.getColor());

    }
    public ProductResponseDto toDto(Product entity){
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(entity.getId());
        dto.setProductName(entity.getProductName());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getColor());
        dto.setColor(entity.getColor());
        dto.setSize(entity.getSize());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
    public List<ProductResponseDto> toDtoList(List<Product> entities){
        if (entities == null || entities.isEmpty()){
            return new ArrayList<>();
        }
        return entities.stream()
                .map(product -> this.toDto(product))
                .collect(Collectors.toList());
    }

}
