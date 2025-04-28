package com.pos_main.Dao;

import java.util.List;
import java.util.Map;

import com.pos_main.Domain.PurchaseList;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.PurchaseListDto;

public interface PurchaseListDao extends BaseDao<PurchaseList> {
	
	PurchaseList savePurchaseList(PurchaseList purchaseList);
	
	List<PurchaseListDto> getAll();
	
	PaginatedResponseDto getAllPagePurchaseList(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams);
	
	boolean deleteAll();
	
	boolean deleteById(Integer id);
	
	}
	

