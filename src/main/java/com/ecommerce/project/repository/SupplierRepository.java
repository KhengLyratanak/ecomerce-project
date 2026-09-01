package com.ecommerce.project.repository;


import com.ecommerce.project.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    Boolean existsByName(String name);
}
