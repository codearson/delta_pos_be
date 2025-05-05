package com.pos_main.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.VoidHistoryDao;
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
		return voidHistoryDao.save(voidHistoryDto);
	}
	
	public List<VoidHistoryDto> gellAll() {
		log.info("VoidHistoryServiceBL.gellAll() invoked");
		return voidHistoryDao.getAll();
	}
	
}
