package com.pos_main.Dao;

import java.util.List;
import java.util.Map;


import com.pos_main.Domain.PayoutCategory;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.PayoutCategoryDto;

public interface PayoutCategoryDao extends BaseDao<PayoutCategory> {
	
    PayoutCategoryDto save(PayoutCategoryDto payoutCategoryDto);
    
    PayoutCategoryDto updatePayoutCategory(PayoutCategoryDto payoutCategoryDto);
    
    PayoutCategoryDto checkPayoutCategoryAvailability(Integer payoutCategoryId);
    
    List<PayoutCategoryDto> getAllPayoutCategory();
    
    PaginatedResponseDto getAllPagePayoutCategory(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams);

    List<PayoutCategoryDto> getAllByName(String payoutCategory); 
}