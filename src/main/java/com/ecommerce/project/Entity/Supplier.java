package com.ecommerce.project.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "supplier")
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;
    @Column(name = "supplier_name")
    private String name;
    @Column(name = "contact_no")
    private Long phone;
    @Column(name = "location")
    private String address;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "supplier",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Stock> stocks;
    @PrePersist
    private void prepersist(){
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    private void preupdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
