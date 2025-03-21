package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.UserPaymentDetails;
import com.pos_main.Dto.UserPaymentDetailsDto;

/**
 * Title: UserPaymentDetailsTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 21:22:36
 * @version 1.0
 **/

@Component
public class UserPaymentDetailsTransformer implements BaseTransformer<UserPaymentDetails, UserPaymentDetailsDto>{

	@Autowired
	SalesDateDetailsTransformer salesDateDetailsTransformer;
	
	@Override
	public UserPaymentDetailsDto transform(UserPaymentDetails userPaymentDetails) {
		UserPaymentDetailsDto userPaymentDetailsDto = null;
		if (userPaymentDetails != null) {
			userPaymentDetailsDto = new UserPaymentDetailsDto();
			userPaymentDetailsDto.setId(userPaymentDetails.getId());
			if (userPaymentDetails.getSalesDateDetails() != null) {
				userPaymentDetailsDto.setSalesDateDetailsDto(salesDateDetailsTransformer.transform(userPaymentDetails.getSalesDateDetails()));
			}
			userPaymentDetailsDto.setUserName(userPaymentDetails.getUserName());
			userPaymentDetailsDto.setPaymentMethod(userPaymentDetails.getPaymentMethod());
			userPaymentDetailsDto.setPaymentTotal(userPaymentDetails.getPaymentTotal());
		}
		return userPaymentDetailsDto;
	}
	
	@Override
	public UserPaymentDetails reverseTransform(UserPaymentDetailsDto userPaymentDetailsDto) {
		UserPaymentDetails userPaymentDetails = null;
		if (userPaymentDetailsDto != null) {
			userPaymentDetails = new UserPaymentDetails();
			userPaymentDetails.setId(userPaymentDetailsDto.getId());
			if (userPaymentDetailsDto.getSalesDateDetailsDto() != null) {
				userPaymentDetails.setSalesDateDetails(
						salesDateDetailsTransformer.reverseTransform(userPaymentDetailsDto.getSalesDateDetailsDto()));
			}
			userPaymentDetails.setUserName(userPaymentDetailsDto.getUserName());
			userPaymentDetails.setPaymentMethod(userPaymentDetailsDto.getPaymentMethod());
			userPaymentDetails.setPaymentTotal(userPaymentDetailsDto.getPaymentTotal());
		}
		return userPaymentDetails;
	}

}
