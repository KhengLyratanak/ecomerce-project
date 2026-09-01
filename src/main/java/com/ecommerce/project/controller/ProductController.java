package com.ecommerce.project.controller;

import com.ecommerce.project.service.ProductService;
import com.ecommerce.project.dto.base.Response;
import com.ecommerce.project.dto.product.ProductDto;
import com.ecommerce.project.dto.product.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v10/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Response> createProduct(@RequestBody ProductDto payload){
         productService.createProduct(payload);
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(Response.success("201","success","successfully created product "));
    }
    @GetMapping()
    public  ResponseEntity<Response> listProduct(){

        List<ProductResponseDto> products = productService.listProduct();
        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.success("200","success","successfully retrived product",products));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> getProduct(@PathVariable ("id") Long productId){
        ProductResponseDto product = productService.getProduct(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.success("200","success","successfully retrieved product with id :",product));
    }
    @GetMapping("/search")
    public ResponseEntity<Response> searchProduct(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice
    ){
        List<ProductResponseDto> products = productService.searchProduct
                (name, minPrice, maxPrice);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.success("200","success","successfully retrieved products with filter",products));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateProduct(@RequestBody ProductDto dto,@PathVariable ("id") Long productId){
         productService.updateUser(productId,dto);
         return ResponseEntity.status(HttpStatus.OK)
                 .body(Response.success("200","success","successfully updated product"));
    }
    @DeleteMapping("/{product_id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable ("product_id")  Long productId){
         productService.deleteProduct(productId);

         return ResponseEntity.status(HttpStatus.OK)
                 .body(Response.success("200","success","successfully deleted product id: ",productId));
    }

}

