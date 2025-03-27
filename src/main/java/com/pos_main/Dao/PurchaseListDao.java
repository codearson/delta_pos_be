package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Domain.PurchaseList;
import com.pos_main.Dto.PurchaseListDto;

public interface PurchaseListDao extends BaseDao<PurchaseList> {
	
	PurchaseList savePurchaseList(PurchaseList purchaseList);
	
	List<PurchaseListDto> getAll();
	
	boolean deleteAll();
	
	boolean deleteById(Integer id);
	
	}
	

