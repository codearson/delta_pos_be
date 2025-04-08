package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.PurchaseListDto;
import com.pos_main.Dto.ResponseDto;

public interface PurchaseListService {
	
    ResponseDto savePurchaseList(PurchaseListDto purchaseListDto);
    
    ResponseDto getAll();
    
    public ResponseDto getAllPagePurchaseList(int pageNumber, int pageSize, Map<String, String> searchParameters);
    
    ResponseDto deleteAll();
    
    ResponseDto deleteById(Integer id);
    
}
