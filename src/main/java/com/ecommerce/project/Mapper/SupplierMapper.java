package com.ecommerce.project.Mapper;

import com.ecommerce.project.Entity.Stock;
import com.ecommerce.project.dto.supplier.SupplierDto;
import com.ecommerce.project.dto.supplier.SupplierResponseDto;
import org.springframework.stereotype.Component;
import com.ecommerce.project.Entity.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierMapper {
    public Supplier toEntity(SupplierDto dto) {
        Supplier entity = new Supplier();
        entity.setName(dto.getSupplierName());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        return entity;
    }
    public SupplierResponseDto toDto(Supplier supplier){
        SupplierResponseDto dto = new SupplierResponseDto();
        dto.setSupplierId(supplier.getSupplierId());
        dto.setSupplierName(supplier.getName());
        dto.setPhone(supplier.getPhone());
        dto.setAddress(supplier.getAddress());
       return dto;
    }
    public void updateEntityFromDto(SupplierDto dto, Supplier entity){
        if (entity == null || dto == null){
            return;
        }
        entity.setName(dto.getSupplierName());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
    }
    public List<SupplierResponseDto> toDtoList(List<Supplier> entities){
        if (entities == null || entities.isEmpty()){
            return new ArrayList<>();
        }
        return entities.stream()
                .map(supplier -> this.toDto(supplier))
                .collect(Collectors.toList());
    }
}
