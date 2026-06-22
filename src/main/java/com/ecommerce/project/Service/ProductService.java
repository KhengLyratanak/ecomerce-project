package com.ecommerce.project.Service;

import com.ecommerce.project.Entity.Product;
import com.ecommerce.project.Mapper.ProductMapper;
import com.ecommerce.project.Model.BaseResponseModel;
import com.ecommerce.project.Model.BaseResponseModelWithData;
import com.ecommerce.project.Repository.ProductRepository;
import com.ecommerce.project.dto.product.ProductDto;
import com.ecommerce.project.dto.product.ProductResponseDto;
import com.ecommerce.project.exception.Model.DuplicateResourceException;
import com.ecommerce.project.exception.Model.ResourceNotFoundException;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper mapper;
    public ResponseEntity<BaseResponseModelWithData> listProduct(){
        List<Product> products = productRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModelWithData("success","Successfully retrieved Product",mapper.toDtoList(products)));
    }
    public ResponseEntity<BaseResponseModel> updateUser(Long productId,ProductDto payload){
        Product existing = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("product not found with id:" +productId));
        Product updatedProduct = existing;
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("Success","Successfully updated Product"));
    }

    public ResponseEntity<BaseResponseModel> createProduct(ProductDto payload){
        //validate if product is already exists
        if (productRepository.existsByProductName(payload.getName()))
          throw new DuplicateResourceException("product already existed" );
    Product products = mapper.toEntity(payload);

    productRepository.save(products);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(new BaseResponseModel("success","Successfully created Product"));
    }
    public ResponseEntity<BaseResponseModel> deleteProduct(Long productId) {
        if (!productRepository.existsById(productId))
            throw new ResourceNotFoundException("product not found with id" + productId);
            //product found
            productRepository.deleteById(productId);
            //200 ok
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BaseResponseModel("success", "successfully deleted product"));
        }
    }
