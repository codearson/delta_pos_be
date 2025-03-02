package com.pos_main.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.TransactionPaymentMethodDao;
import com.pos_main.Dto.TransactionPaymentMethodDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TransactionPaymentMethodServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 14, 2025
 * @version 1.0
 **/

@Slf4j
@Service
public class TransactionPaymentMethodServiceBL {
	
	@Autowired
	TransactionPaymentMethodDao transactionPaymentMethodDao;
	
	public List<TransactionPaymentMethodDto> getByTransactionId(Integer transactionId) {
	    log.info("TransactionPaymentMethodServiceBL.getByTransactionId() invoked with transactionId: {}", transactionId);
	    return transactionPaymentMethodDao.getByTransactionId(transactionId);
	}
	
}
