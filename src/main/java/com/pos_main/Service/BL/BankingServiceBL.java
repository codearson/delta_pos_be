package com.pos_main.Service.BL;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.BankingDao;
import com.pos_main.Dto.BankingDto;
import com.pos_main.Dto.PaginatedResponseDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BankingServiceBl.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Apr 4, 2025.
 * @version 1.0
 **/

@Slf4j
@Service
public class BankingServiceBL {
	
	@Autowired
	BankingDao bankingDao;
	
	public BankingDto save(BankingDto bankingDto) {
		log.info("BankingServiceBL.save() invoked.");
		bankingDto.setDateTime(LocalDateTime.now());
		bankingDto.setIsActive(true);
		return bankingDao.save(bankingDto);
	}
	
	public PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("BankingServiceBL.getAllPage()invoked");
		return bankingDao.getAllPage(pageNumber, pageSize, searchParams);
	}

}


