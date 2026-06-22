package com.ecommerce.project.Controller;

import com.ecommerce.project.Model.BaseResponseModel;
import com.ecommerce.project.Model.BaseResponseModelWithData;
import com.ecommerce.project.Service.ProductService;
import com.ecommerce.project.dto.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v10/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<BaseResponseModel> createProduct(@RequestBody ProductDto payload){
        return productService.createProduct(payload);
    }
    @GetMapping
    public  ResponseEntity<BaseResponseModelWithData> listProduct(){

        return productService.listProduct();
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseModel> updateProduct(@RequestBody ProductDto dto,@PathVariable ("id") Long productId){
        return productService.updateUser(productId,dto);
    }
    @DeleteMapping("/{product_id}")
    public ResponseEntity<BaseResponseModel> deleteProduct(@PathVariable ("product_id")  Long productId){
        return productService.deleteProduct(productId);
    }

}

