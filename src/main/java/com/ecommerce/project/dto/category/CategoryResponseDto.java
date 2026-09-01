package com.ecommerce.project.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryResponseDto {
    @JsonProperty("category_id")
    private Long id;

    private String name;

    private String description;

    @JsonProperty("created_At")
    private LocalDateTime createdAt;
    @JsonProperty("updated_At")
    private LocalDateTime updatedAt;

}
