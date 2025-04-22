package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.BankingDto;
import com.pos_main.Dto.ResponseDto;

/**
 * Title: BankingService.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Apr 4, 2025.
 * @version 1.0
 **/

public interface BankingService {

	ResponseDto getAllPage(int pageNumber, int pageSize, Map<String, String> searchParams);
	
	public ResponseDto save(BankingDto bankingDto);
	
	public ResponseDto getTotalBanking();
	
}


