package com.pos_main.Service;

import com.pos_main.Dto.NonScanProductDto;
import com.pos_main.Dto.ResponseDto;

/**
 * Title: NonScanProductService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 19:24:01
 * @version 1.0
 **/

public interface NonScanProductService {
    
    ResponseDto getAll();
    
    ResponseDto getAllPageNonScanProduct(int pageNumber, int pageSize);
    
    ResponseDto save(NonScanProductDto nonScanProductDto);
    
    ResponseDto update(NonScanProductDto nonScanProductDto);
    
    ResponseDto getAllByName(String nonScanProduct);
    
    ResponseDto updateNonScanProductStatus(Integer id, Boolean status);
}
