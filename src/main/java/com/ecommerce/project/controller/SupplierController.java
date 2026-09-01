package com.ecommerce.project.controller;

import com.ecommerce.project.service.SupplierService;
import com.ecommerce.project.dto.base.Response;
import com.ecommerce.project.dto.supplier.SupplierDto;
import com.ecommerce.project.dto.supplier.SupplierResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v10/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping()
    public ResponseEntity<Response> createdSupplier(@RequestBody SupplierDto payload){
         supplierService.createSupplier(payload);
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(Response.success("201","success","successfully created supplier"));
    }
    @GetMapping
    public ResponseEntity<Response> listSupplier(){

        List<SupplierResponseDto> suppliers =  supplierService.listSuppliers();
        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.success("200","success","successfully retrieved all supplier",suppliers));
    }
    @PutMapping("/{id}")
    ResponseEntity<Response> updateSupplier(@PathVariable("id") Long supplierId, @RequestBody SupplierDto payload) {
         supplierService.updateSupplier(supplierId,payload);
         return ResponseEntity.status(HttpStatus.OK)
                 .body(Response.success("200","success","successfully updated supplier",supplierId));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Response> deletedSupplier(@PathVariable("id") Long supplierId){
         supplierService.deleteSupplier(supplierId);
         return ResponseEntity.status(HttpStatus.OK)
                 .body(Response.success("200","success","successfully deleted supplier id :",supplierId));
    }
}
