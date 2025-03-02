package com.pos_main.Transformer;

import org.springframework.stereotype.Component;

import com.pos_main.Domain.Supplier;
import com.pos_main.Dto.SupplierDto;

/**
 * Feb 13, 2024 
 * 10:52:25 PM
 * @author Lathusan Thurairajah
 **/

@Component
public class SupplierTransfomer implements BaseTransformer<Supplier, SupplierDto>{

	@Override
	public SupplierDto transform(Supplier supplier) {
		SupplierDto supplierDto= null;
		if (supplier != null) {
			supplierDto = new SupplierDto();
			supplierDto.setId(supplier.getId());
			supplierDto.setName(supplier.getName());
			supplierDto.setEmailAddress(supplier.getEmailAddress());
			supplierDto.setMobileNumber(supplier.getMobileNumber());
			supplierDto.setWhatsappNumber(supplier.getWhatsappNumber());
			supplierDto.setCreatedDate(supplier.getCreatedDate());
			supplierDto.setIsActive(supplier.getIsActive());
		}
		return supplierDto;
	}

	@Override
	public Supplier reverseTransform(SupplierDto supplierDto) {
		Supplier supplier = null;
		if (supplierDto != null) {
			supplier = new Supplier();
			supplier.setId(supplierDto.getId());
			supplier.setName(supplierDto.getName());
			supplier.setEmailAddress(supplierDto.getEmailAddress());
			supplier.setMobileNumber(supplierDto.getMobileNumber());
			supplier.setWhatsappNumber(supplierDto.getWhatsappNumber());
			supplier.setCreatedDate(supplierDto.getCreatedDate());
			supplier.setIsActive(supplierDto.getIsActive());
		}
		return supplier;
	}

}
