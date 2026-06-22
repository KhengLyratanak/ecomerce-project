package com.ecommerce.project.dto.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"supplier_id","supplier_name","contact_no","location"})
public class SupplierResponseDto {
    @JsonProperty("supplier_id")
    private Long SupplierId;
    @JsonProperty("supplier_name")
    private String SupplierName;
    @JsonProperty("contact_no")
    private Long phone;
    @JsonProperty("location")
    private String address;
}
