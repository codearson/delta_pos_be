package com.pos_main.Service;

import com.pos_main.Dto.PurchaseListDto;
import com.pos_main.Dto.ResponseDto;

public interface PurchaseListService {
	
    ResponseDto savePurchaseList(PurchaseListDto purchaseListDto);
    
    ResponseDto getAll();
    
    ResponseDto deleteAll();
    
    ResponseDto deleteById(Integer id);
    
}
