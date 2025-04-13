package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.PayoutCategoryDto;
import com.pos_main.Dto.ResponseDto;

public interface PayoutCategoryService {
	
    ResponseDto save(PayoutCategoryDto payoutCategoryDto);
    
    ResponseDto updatePayoutCategory(PayoutCategoryDto payoutCategoryDto);
    
    ResponseDto updatePayoutCategoryStatus(Integer id, Boolean status);
    
    ResponseDto getAllPayoutCategory();
    
    ResponseDto getAllPagePayoutCategory(int pageNumber, int pageSize, Map<String, String> searchParameters);
    
    ResponseDto getAllByName(String payoutCategory);
}