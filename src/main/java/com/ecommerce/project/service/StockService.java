package com.ecommerce.project.service;

import com.ecommerce.project.Entity.Product;
import com.ecommerce.project.Entity.Stock;
import com.ecommerce.project.Mapper.StockMapper;
import com.ecommerce.project.repository.ProductRepository;
import com.ecommerce.project.repository.StockRepository;
import com.ecommerce.project.dto.stock.StockDto;
import com.ecommerce.project.dto.stock.StockResponseDto;
import com.ecommerce.project.dto.stock.UpdateStockDto;
import com.ecommerce.project.exception.Model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockMapper mapper;

    @Autowired
    private ProductRepository productRepository;

    public void createdStocks(StockDto stock) {
        //product not found
        Product existingProduct = productRepository.findById(stock.getProductId())
                //product not found
                .orElseThrow(()->
                        new ResourceNotFoundException("product not found with id:" +stock.getProductId()));
        Stock stockEntity = mapper.toEntity(stock,existingProduct);
        stockRepository.save(stockEntity);
    }
    public StockResponseDto getStock(Long stockId){
     Stock stock = stockRepository.findById(stockId)
             .orElseThrow(() ->
                     new ResourceNotFoundException("stock not found with id :" +stockId));

       return mapper.toDto(stock);
    }

    public List<StockResponseDto> listStock (){
        List<Stock> stocks = stockRepository.findAll();
        return mapper.toDtoList(stocks);
    }
    public void deleteStock (Long stockId){
        if (!stockRepository.existsById(stockId)){
            throw new ResourceNotFoundException("stock not found with id "  +stockId);
        }
        stockRepository.deleteById(stockId);

    }
    public void adjustQuantity(Long stockId, UpdateStockDto updateStock){
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
                 throw new ResourceNotFoundException ("quantity to remove can not be exceeded than existing stock:" +existingStock.getQuantity());
            }
            Long newQty = existingStock.getQuantity() - updateStock.getQuantity();
            existingStock.setQuantity(newQty);
        } else{
          throw new ResourceNotFoundException ("invalid operation type");

        }
        stockRepository.save(existingStock);

    }

        }