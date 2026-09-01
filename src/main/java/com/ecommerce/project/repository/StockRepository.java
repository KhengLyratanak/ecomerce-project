package com.ecommerce.project.repository;

import com.ecommerce.project.Entity.Stock;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
        List<Stock> findByProductIdIn(List<Long> productIds , Sort createdAt);
}
