package com.ecommerce.project.service;

import com.ecommerce.project.Entity.Product;
import com.ecommerce.project.Mapper.ProductMapper;
import com.ecommerce.project.Model.BaseResponseModel;
import com.ecommerce.project.repository.ProductRepository;
import com.ecommerce.project.dto.product.ProductDto;
import com.ecommerce.project.dto.product.ProductResponseDto;
import com.ecommerce.project.exception.Model.DuplicateResourceException;
import com.ecommerce.project.exception.Model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper mapper;
    public List<ProductResponseDto> listProduct(){
        List<Product> products = productRepository.findAll();

      return   mapper.toDtoList(products);
    }
    public  ProductResponseDto getProduct(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("product not found with id :"+productId));

      return  mapper.toDto(product);

    }
    public void updateUser(Long productId,ProductDto payload){
        Product existing = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("product not found with id:" +productId));

         mapper.updateEntityFromDto(existing,payload);
         productRepository.save(existing);
    }
    public List<ProductResponseDto> searchProduct (String name, Double  minPrice, Double maxPrice){
        String formattedName = name != null ?
                name.toLowerCase()
                :name;
        List<Product> products = productRepository.findProductWithFilters(formattedName,minPrice,maxPrice);
        return mapper.toDtoList(products);
    }
    public void createProduct(ProductDto payload){
        //validate if product is already exists
        if (productRepository.existsByProductName(payload.getName()))
          throw new DuplicateResourceException("product already existed" );
    Product products = mapper.toEntity(payload);

    productRepository.save(products);
    }
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId))
            throw new ResourceNotFoundException("product not found with id" + productId);
            //product found
            productRepository.deleteById(productId);
        }
    }
