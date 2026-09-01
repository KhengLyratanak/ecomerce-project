package com.ecommerce.project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    private Double price;

    private String description;

    private String size;

    private String color;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime  updatedAt;

    @OneToMany(mappedBy = "product" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    List<Stock> stocks;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;
    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

}
