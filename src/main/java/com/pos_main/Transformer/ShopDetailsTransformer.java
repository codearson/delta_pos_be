package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.ShopDetails;
import com.pos_main.Dto.ShopDetailsDto;

@Component
public class ShopDetailsTransformer implements BaseTransformer<ShopDetails, ShopDetailsDto> {
	
	@Autowired
	BranchTransformer branchTransformer;
	
	@Override
	public ShopDetailsDto transform(ShopDetails shopDetails) {
		ShopDetailsDto shopDetailsDto = null;
		if (shopDetails != null) {
			shopDetailsDto = new ShopDetailsDto();
			shopDetailsDto.setId(shopDetails.getId());
			shopDetailsDto.setName(shopDetails.getName());
			shopDetailsDto.setAddress(shopDetails.getAddress());
			shopDetailsDto.setContactNumber(shopDetails.getContactNumber());
			shopDetailsDto.setWhatsappNumber(shopDetails.getWhatsappNumber());
			shopDetailsDto.setIsActive(shopDetails.getIsActive());
		}
		return shopDetailsDto;
	}
	
	@Override
	public ShopDetails reverseTransform(ShopDetailsDto shopDetailsDto) {
		ShopDetails shopDetails = null;
		if (shopDetailsDto != null) {
			shopDetails = new ShopDetails();
			shopDetails.setId(shopDetailsDto.getId());
			shopDetails.setName(shopDetailsDto.getName());
			shopDetails.setAddress(shopDetailsDto.getAddress());
			shopDetails.setContactNumber(shopDetailsDto.getContactNumber());
			shopDetails.setWhatsappNumber(shopDetailsDto.getWhatsappNumber());
			shopDetails.setEmail(shopDetailsDto.getEmail());
			shopDetails.setIsActive(shopDetailsDto.getIsActive());
		}
		return shopDetails;
	}

}
