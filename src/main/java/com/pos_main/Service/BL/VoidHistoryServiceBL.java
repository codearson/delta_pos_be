package com.pos_main.Service.BL;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.VoidHistoryDao;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.VoidHistoryDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: VoidHistoryServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 5, 2025
 * @version 1.0
 **/

@Slf4j
@Service
public class VoidHistoryServiceBL {

	@Autowired
	VoidHistoryDao voidHistoryDao;
	
	public VoidHistoryDto save(VoidHistoryDto voidHistoryDto) {
		log.info("VoidHistoryServiceBL.save() invoked.");
		voidHistoryDto.setDateTime(LocalDateTime.now());
		return voidHistoryDao.save(voidHistoryDto);
	}
	
	public List<VoidHistoryDto> gellAll() {
		log.info("VoidHistoryServiceBL.gellAll() invoked");
		return voidHistoryDao.getAll();
	}
	
	public PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("VoidHistoryServiceBL.getAllPage()invoked");
		return voidHistoryDao.getAllPage(pageNumber, pageSize, searchParams);
	}
	
	public PaginatedResponseDto getAllPageByDate(int pageNumber, int pageSize, String date, Map<String, String> searchParams) {
		log.info("VoidHistoryServiceBL.getAllPageByDate() invoked");
		return voidHistoryDao.getAllPageByDate(pageNumber, pageSize, date, searchParams);
	}
	
	public PaginatedResponseDto getAllPageByUserId(int pageNumber, int pageSize, Integer userId, Map<String, String> searchParams) {
		log.info("VoidHistoryServiceBL.getAllPageByUserId() invoked");
		return voidHistoryDao.getAllPageByUserId(pageNumber, pageSize, userId, searchParams);
	}
	
}
