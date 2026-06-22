package com.ecommerce.project.Service;


import com.ecommerce.project.Mapper.SupplierMapper;
import com.ecommerce.project.Model.BaseResponseModel;
import com.ecommerce.project.Model.BaseResponseModelWithData;
import com.ecommerce.project.Repository.SupplierRepository;
import com.ecommerce.project.dto.supplier.SupplierDto;
import com.ecommerce.project.exception.Model.DuplicateResourceException;
import com.ecommerce.project.exception.Model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import com.ecommerce.project.Entity.Supplier;
@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper mapper;

    public ResponseEntity<BaseResponseModelWithData> listSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new BaseResponseModelWithData(
                                "success",
                                "successfully retrieved suppliers",
                                mapper.toDtoList(suppliers)
                        )
                );
    }

    public ResponseEntity<BaseResponseModel> createSupplier(SupplierDto payload) {
        // if duplicate supplier name , then reject
        if(supplierRepository.existsByName(payload.getSupplierName())) {
            throw new DuplicateResourceException("supplier already existed");
        }

        Supplier supplier = mapper.toEntity(payload);

        supplierRepository.save(supplier);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success","successfully created supplier"));
    }

    public ResponseEntity<BaseResponseModel> updateSupplier(Long supplierId, SupplierDto dto) {
        Supplier existingSupplier = supplierRepository.findById(supplierId)

                // if supplier not found, return 404
                .orElseThrow(() ->
                        new ResourceNotFoundException("supplier not found with :"  +supplierId));

        mapper.updateEntityFromDto(dto, existingSupplier);

        supplierRepository.save(existingSupplier);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success","successfully updated supplier"));
    }

    public ResponseEntity<BaseResponseModel> deleteSupplier(Long supplierId) {
        if(!supplierRepository.existsById(supplierId)) {
            throw new ResourceNotFoundException("supplier not found with id:"  +supplierId);
        }

        supplierRepository.deleteById(supplierId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success","successfully deleted supplier"));
    }


}
