package com.pos_main.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.PayoutCategoryDao;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.PayoutCategoryDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PayoutCategoryServiceBL {
    
    @Autowired
    PayoutCategoryDao payoutCategoryDao;

    public PayoutCategoryDto save(PayoutCategoryDto payoutCategoryDto) {
        log.info("PayoutCategoryServiceBL.save() invoked");
        return payoutCategoryDao.save(payoutCategoryDto);
    }

    public PayoutCategoryDto updatePayoutCategory(PayoutCategoryDto payoutCategoryDto) {
        log.info("PayoutCategoryServiceBL.updatePayoutCategory() invoked");
        return payoutCategoryDao.updatePayoutCategory(payoutCategoryDto);
    }

    public PayoutCategoryDto updatePayoutCategoryStatus(Integer payoutCategoryId, Boolean status) {
        log.info("PayoutCategoryServiceBL.updatePayoutCategoryStatus() invoked");
        PayoutCategoryDto payoutCategoryDto = payoutCategoryDao.checkPayoutCategoryAvailability(payoutCategoryId);
        if (payoutCategoryDto != null) {
            payoutCategoryDto.setIsActive(status);
            return payoutCategoryDao.updatePayoutCategory(payoutCategoryDto);
        }
        return null;
    }

    public List<PayoutCategoryDto> getAllPayoutCategory() {
        log.info("PayoutCategoryServiceBL.getAllPayoutCategory() invoked");
        return payoutCategoryDao.getAllPayoutCategory();
    }

    public PaginatedResponseDto getAllPagePayoutCategory(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
        log.info("PayoutCategoryServiceBL.getAllPagePayoutCategory() invoked");
        return payoutCategoryDao.getAllPagePayoutCategory(pageNumber, pageSize, status, searchParams);
    }
    
    public List<PayoutCategoryDto> getAllByName(String payoutCategory) {
        log.info("PayoutCategoryServiceBL.getAllByName() invoked.");
        return payoutCategoryDao.getAllByName(payoutCategory);
    }
}