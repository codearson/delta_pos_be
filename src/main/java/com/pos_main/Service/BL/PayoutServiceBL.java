package com.pos_main.Service.BL;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.PayoutDao;
import com.pos_main.Dto.PayoutDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BranchBL.java. Company: www.codearson.com Copyright: Copyright (c)
 * 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Slf4j
@Service
public class PayoutServiceBL {

	@Autowired
	PayoutDao payoutDao;


	public List<PayoutDto> getAllPayout() {
		log.info("PayoutServiceBL.gellAllPayout() invoked");
		return payoutDao.getAllPayout();

	}
	
	public PayoutDto save(PayoutDto payoutDto) {
		log.info("PayoutServiceBL.save() invoked.");
		payoutDto.setDateTime(LocalDateTime.now());
		
		return payoutDao.save(payoutDto);	
	}

	
	public PayoutDto updatePayout(PayoutDto PayoutDto) {
	    log.info("PayoutServiceBL.updatePayout() invoked.");
	    return payoutDao.update(PayoutDto);
	}

	public PayoutDto updatePayoutStatus(Integer id, Boolean status) {
		PayoutDto payoutDto = payoutDao.checkPayoutAvailability(id);
		if (payoutDto != null) {
			payoutDto.setIsActive(status);
			return payoutDao.update(payoutDto);
		} else {
			return null;
		}
	}





}
