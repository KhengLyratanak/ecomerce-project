package com.ecommerce.project.repository;

import com.ecommerce.project.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
        Boolean existsByName(String name);
}
