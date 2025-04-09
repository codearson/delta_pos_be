package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Domain.NonScanProduct;
import com.pos_main.Dto.NonScanProductDto;

/**
 * Title: NonScanProductDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 19:24:49
 * @version 1.0
 **/

public interface NonScanProductDao extends BaseDao<NonScanProduct> {
    
    List<NonScanProductDto> getAll();
    
    List<NonScanProductDto> getAllPageNonScanProduct(int pageNumber, int pageSize);
    
    NonScanProductDto save(NonScanProductDto nonScanProductDto);
    
    NonScanProductDto update(NonScanProductDto nonScanProductDto);
    
    List<NonScanProductDto> getAllByName(String nonScanProduct);
    
    NonScanProductDto checkAvailability(Integer id);
}