package com.ecommerce.project.Controller;

import com.ecommerce.project.Model.BaseResponseModel;
import com.ecommerce.project.Model.BaseResponseModelWithData;
import com.ecommerce.project.Service.StockService;
import com.ecommerce.project.dto.stock.StockDto;
import com.ecommerce.project.dto.stock.UpdateStockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v10/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<BaseResponseModel> createStock (@RequestBody StockDto stock){
        return stockService.createdStocks(stock);

    }
    @GetMapping
    public ResponseEntity<BaseResponseModelWithData> listStock(){
        return stockService.listStock();
    }
    @GetMapping("/{stock_id}")
    public ResponseEntity<BaseResponseModelWithData> getStock(@PathVariable ("stock_id") Long stockId){
        return stockService.getStock(stockId);
    }
    @DeleteMapping("/{stock_id}")
    public ResponseEntity<BaseResponseModel> deleteStock(@PathVariable ("stock_id") Long stockId){
        return stockService.deleteStock(stockId);
    }
    @PutMapping("/{stock_id}")
    public ResponseEntity<BaseResponseModel> updateStock(@PathVariable ("stock_id") Long stockId,@RequestBody UpdateStockDto updateStock){
        return stockService.adjustQuantity(stockId,updateStock);
    }
}
