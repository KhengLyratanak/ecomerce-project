package com.ecommerce.project.Service;

import com.ecommerce.project.Entity.Product;
import com.ecommerce.project.Entity.Stock;
import com.ecommerce.project.Mapper.StockMapper;
import com.ecommerce.project.Model.BaseResponseModel;
import com.ecommerce.project.Model.BaseResponseModelWithData;
import com.ecommerce.project.Repository.ProductRepository;
import com.ecommerce.project.Repository.StockRepository;
import com.ecommerce.project.dto.stock.StockDto;
import com.ecommerce.project.dto.stock.StockResponseDto;
import com.ecommerce.project.dto.stock.UpdateStockDto;
import com.ecommerce.project.exception.Model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockMapper mapper;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<BaseResponseModel> createdStocks(StockDto stock) {
        //product not found
        Product existingProduct = productRepository.findById(stock.getProductId())
                //product not found
                .orElseThrow(()->
                        new ResourceNotFoundException("product not found with id:" +stock.getProductId()));
        Stock stockEntity = mapper.toEntity(stock,existingProduct);
        stockRepository.save(stockEntity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseModel("success", "successfully created stock"));
    }
    public ResponseEntity<BaseResponseModelWithData> getStock(Long stockId){
     Stock stock = stockRepository.findById(stockId)
             .orElseThrow(() ->
                     new ResourceNotFoundException("stock not found with id :" +stockId));

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModelWithData("success","successfully retrieved stock id:"+stockId,stock));
    }

    public ResponseEntity<BaseResponseModelWithData> listStock (){
        List<Stock> stocks = stockRepository.findAll();
       List<StockResponseDto> dtos = mapper.toDtoList(stocks);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModelWithData("success","successfully retrieved Stock",dtos));
    }
    public ResponseEntity<BaseResponseModel> deleteStock (Long stockId){
        if (!stockRepository.existsById(stockId)){
            throw new ResourceNotFoundException("stock not found with id "  +stockId);
        }
        stockRepository.deleteById(stockId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success","successfully deleted user wiht id :"+stockId));
    }
    public ResponseEntity<BaseResponseModel> adjustQuantity(Long stockId, UpdateStockDto updateStock){
        Stock existingStock = stockRepository.findById(stockId)

        //stock not found in db
                .orElseThrow(() ->
                        new ResourceNotFoundException("stock not found with id :" +stockId));

     //   Stock stock = existingStock;

        if (updateStock.getOperationType() == 1){
            Long newQty = existingStock.getQuantity() + updateStock.getQuantity();
            existingStock.setQuantity(newQty);
        }
        else if (updateStock.getOperationType() == 2){
            //when remove amount> existing amount
            if (existingStock.getQuantity() < updateStock.getQuantity()){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT)
                        .body(new BaseResponseModel("fail","quantity to remove can not be exceeded than existing stock:" +existingStock.getQuantity()));
            }
            Long newQty = existingStock.getQuantity() - updateStock.getQuantity();
            existingStock.setQuantity(newQty);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseModel("fail","invalid operation type"));
        }
        stockRepository.save(existingStock);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success","successfully adjusted stock quantity"));
    }

        }