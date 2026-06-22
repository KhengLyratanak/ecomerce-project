package com.ecommerce.project.Controller;

import com.ecommerce.project.Model.BaseResponseModel;
import com.ecommerce.project.Model.BaseResponseModelWithData;
import com.ecommerce.project.Service.SupplierService;
import com.ecommerce.project.dto.supplier.SupplierDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v10/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping()
    public ResponseEntity<BaseResponseModel> createdSupplier( @RequestBody SupplierDto payload){
        return supplierService.createSupplier(payload);
    }
    @GetMapping
    public ResponseEntity<BaseResponseModelWithData> listSupplier(){
        return supplierService.listSuppliers();
    }
    @PutMapping("/{id}")
    ResponseEntity<BaseResponseModel> updateSupplier(@PathVariable("id") Long supplierId, @RequestBody SupplierDto payload) {
        return supplierService.updateSupplier(supplierId,payload);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<BaseResponseModel> deletedSupplier(@PathVariable("id") Long supplierId){
        return supplierService.deleteSupplier(supplierId);
    }
}
