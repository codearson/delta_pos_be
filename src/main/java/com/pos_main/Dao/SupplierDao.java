package com.pos_main.Dao;

import java.util.List;
import java.util.Map;

import com.pos_main.Domain.Supplier;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.SupplierDto;

public interface SupplierDao extends BaseDao<Supplier>{
	
	SupplierDto saveSupplier(SupplierDto supplierDto);
	
	List<SupplierDto> getSupplierByName(String name);
	
	SupplierDto updateSupplier(SupplierDto supplierDto);

	SupplierDto checkProductAvailability(Integer supplierId);

	List<SupplierDto> getSupplierById(Integer id);
	
	List<SupplierDto> getAllSupplier();
	
	PaginatedResponseDto getAllPageSupplier(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams);


}

