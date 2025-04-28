package com.pos_main.ServiceBL;

import java.util.Map;
import com.pos_main.Dto.PaginatedResponseDto;

public interface ProductCategoryServiceBL {
    public PaginatedResponseDto getAllPageProductCategory(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams);
} 