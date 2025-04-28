package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.ProductDiscountDto;
import com.pos_main.Dto.ResponseDto;

public interface ProductDiscountService {

	public ResponseDto save(ProductDiscountDto productDiscountDto);

	ResponseDto update(ProductDiscountDto productDiscountDto);

	ResponseDto updateStatus(Integer id, Boolean status);

	ResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
}
