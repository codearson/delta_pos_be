package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Domain.TransactionPaymentMethod;
import com.pos_main.Dto.TransactionPaymentMethodDto;

/**
 * Title: TransactionPaymentMethodDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 14, 2025
 * @version 1.0
 **/

public interface TransactionPaymentMethodDao extends BaseDao<TransactionPaymentMethod> {

	List<TransactionPaymentMethodDto> getByTransactionId(Integer transactionId);
	
}
