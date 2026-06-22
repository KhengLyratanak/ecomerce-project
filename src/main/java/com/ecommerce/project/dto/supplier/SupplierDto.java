package com.ecommerce.project.dto.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SupplierDto {
    @NotBlank(message = "Supplier name is required")
    private String SupplierName;
    @NotBlank(message = "contact number is required")
    private Long phone;
    @NotNull(message = "location is required")
    private String address;


}
