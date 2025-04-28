package com.pos_main.Dao;

import java.time.LocalDateTime;
import java.util.Map;

import com.pos_main.Domain.Banking;
import com.pos_main.Dto.BankingDto;
import com.pos_main.Dto.PaginatedResponseDto;

/**
 * Title: BankingDao.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Apr 4, 2025.
 * @version 1.0
 **/

public interface BankingDao extends BaseDao<Banking>{

	BankingDto save (BankingDto bankingDto);
	
	PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams);
	
	Double getTotalBanking();
	
	Integer getBankingCount(LocalDateTime startDate, LocalDateTime endDate);
	
}


