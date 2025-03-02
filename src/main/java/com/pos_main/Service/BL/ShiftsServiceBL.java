package com.pos_main.Service.BL;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.ShiftsDao;
import com.pos_main.Dto.ShiftsDto;

import lombok.extern.slf4j.Slf4j;



/**
 * Title: ShiftsServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 19:48:55
 * @version 1.0
 **/

@Slf4j
@Service
public class ShiftsServiceBL {
	
	@Autowired
	ShiftsDao shiftsDao;
	
	public ShiftsDto save(ShiftsDto shiftsDto) {
		log.info("ShiftsServiceBL.save() invoked.");
		return shiftsDao.save(shiftsDto);
	}

}
