package com.pos_main.Service;

import com.pos_main.Dto.ProductDiscountTypeDto;
import com.pos_main.Dto.ResponseDto;

/**
 * Title: ProductDiscountTypeService.java. Company: www.codearson.com |
 * Copyright: Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Apr 10, 2025.
 * @version 1.0
 **/

public interface ProductDiscountTypeService {

	ResponseDto save(ProductDiscountTypeDto productDiscountTypeDto);

	ResponseDto update(ProductDiscountTypeDto productDiscountTypeDto);

	ResponseDto updateStatus(Integer id, Boolean status);

	ResponseDto getAll();

}
