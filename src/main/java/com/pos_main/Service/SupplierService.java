package com.pos_main.Service;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.SupplierDto;

public interface SupplierService {
	
	public ResponseDto saveSupplier(SupplierDto supplierDto);
	
	public ResponseDto getSupplierByName(String name);
	
	public ResponseDto updateSupplier(SupplierDto supplierDto);
	
	public ResponseDto updateSupplierStatus(Integer supplierId, Boolean status);

	public ResponseDto getSupplierById(Integer id);
	
	public ResponseDto getAllSupplier();

}

