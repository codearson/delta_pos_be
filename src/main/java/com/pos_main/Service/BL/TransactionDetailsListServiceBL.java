package com.pos_main.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.TransactionDetailsListDao;
import com.pos_main.Dto.TransactionDetailsListDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionDetailsListServiceBL {
	
	@Autowired
	TransactionDetailsListDao transactionDetailsListDao;
	
	public List<TransactionDetailsListDto> getByTransactionId(Integer transactionId) {
	    log.info("TransactionDetailsListServiceBL.getByTransactionId() invoked with transactionId: {}", transactionId);
	    return transactionDetailsListDao.getByTransactionId(transactionId);
	}
	
}
