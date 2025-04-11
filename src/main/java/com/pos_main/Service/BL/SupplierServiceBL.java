package com.pos_main.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.SupplierDao;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.SupplierDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SupplierServiceBL {
	
	@Autowired
	private SupplierDao supplierDao;
	
	public SupplierDto saveSupplier(SupplierDto supplierDto) {
		log.info("SupplierServiceBL.saveSupplier() invoked.");
		return supplierDao.saveSupplier(supplierDto);
	}
	public List<SupplierDto> getSupplierByName(String name) {
		log.info("SupplierServiceBL.getSupplierByName()invoked");
		return supplierDao.getSupplierByName(name);
	}
	
	public SupplierDto updateSupplier(SupplierDto supplierDto) {
	    log.info("SupplierServiceBL.updateSupplier() invoked.");
	    return supplierDao.updateSupplier(supplierDto);
	}
	
	public SupplierDto updateSupplierStatus(Integer supplierId, Boolean status) {
		SupplierDto supplierDto = supplierDao.checkProductAvailability(supplierId);
		if (supplierDto != null) {
			supplierDto.setIsActive(status);
			return supplierDao.updateSupplier(supplierDto);
		} else {
			return null;
		}
	}

	public List<SupplierDto> getSupplierById(Integer id) {
		log.info("SupplierServiceBL.getSupplierById()invoked");
		return supplierDao.getSupplierById(id);
	}
	
	public List<SupplierDto> getAllSupplier() {
		log.info("SupplierServiceBL.getAllSupplier() invoked");
		return supplierDao.getAllSupplier();
	}
	
	public PaginatedResponseDto getAllPageSupplier(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("SupplierServiceBL.getAllPageSupplier()invoked");
		return supplierDao.getAllPageSupplier(pageNumber, pageSize, searchParams);
	}

}

