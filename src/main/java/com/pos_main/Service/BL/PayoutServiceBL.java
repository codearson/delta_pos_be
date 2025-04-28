package com.pos_main.Service.BL;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.PayoutDao;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.PayoutDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PayoutServiceBL {

    @Autowired
    PayoutDao payoutDao;

	public List<PayoutDto> getAllPayout() {
		log.info("PayoutServiceBL.getAllPayout()invoked");
		return payoutDao.getAllPayout();
	}

    public PaginatedResponseDto getAllPagePayout(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
    	log.info("PayoutServiceBL.getAllPagePayout()invoked");
		return payoutDao.getAllPagePayout(pageNumber, pageSize, status, searchParams);
    }

    public PayoutDto save(PayoutDto payoutDto) {
        log.info("PayoutServiceBL.savePayout() invoked.");
        if (payoutDto != null) {
            if (payoutDto.getDateTime() == null) {
                payoutDto.setDateTime(LocalDateTime.now());
            }
        }
        return payoutDao.save(payoutDto);
    }

	public PayoutDto updatePayout(PayoutDto payoutDto) {
		log.info("PayoutServiceBL.updatePayout() invoked.");
		return payoutDao.updatePayout(payoutDto);
	}

	public PayoutDto updatePayoutStatus(Integer payoutId, Boolean status) {
		PayoutDto payoutDto = payoutDao.checkPayoutAvailability(payoutId);
		if (payoutDto != null) {
			payoutDto.setIsActive(status);
			return payoutDao.updatePayout(payoutDto);
		} else {
			return null;
		}
    }
    
    public Double getTotalPayout() {
        log.info("PayoutServiceBL.getTotalPayout() invoked");
        return payoutDao.getTotalPayout();
    }

    public Integer getPayoutCount(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("PayoutServiceBL.getPayoutCount() invoked");
        return payoutDao.getPayoutCount(startDate, endDate);
    }
}