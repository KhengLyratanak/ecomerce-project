package com.ecommerce.project.controller;

import com.ecommerce.project.service.StockService;
import com.ecommerce.project.dto.base.Response;
import com.ecommerce.project.dto.stock.StockDto;
import com.ecommerce.project.dto.stock.StockResponseDto;
import com.ecommerce.project.dto.stock.UpdateStockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v10/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<Response> createStock (@RequestBody StockDto stock){
         stockService.createdStocks(stock);
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(Response.success("201","success","successfully created stock "));

    }
    @GetMapping
    public ResponseEntity<Response> listStock(){

        List<StockResponseDto> stocks = stockService.listStock();
        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.success("200","success","successfully retrived all stock",stocks));
    }
    @GetMapping("/{stock_id}")
    public ResponseEntity<Response> getStock(@PathVariable ("stock_id") Long stockId){

        StockResponseDto stock =  stockService.getStock(stockId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.success("200","success","successfully retrieved stock" ,stock));
    }
    @DeleteMapping("/{stock_id}")
    public ResponseEntity<Response> deleteStock(@PathVariable ("stock_id") Long stockId){
         stockService.deleteStock(stockId);
         return ResponseEntity.status(HttpStatus.OK)
                 .body(Response.success("200","success","successfully deleted stock id :",stockId));
    }
    @PutMapping("/{stock_id}")
    public ResponseEntity<Response> updateStock(@PathVariable ("stock_id") Long stockId,@RequestBody UpdateStockDto updateStock){
         stockService.adjustQuantity(stockId,updateStock);
         return ResponseEntity.status(HttpStatus.OK)
                 .body(Response.success("200","success","succesfully updated stock id: ",stockId));
    }
}
