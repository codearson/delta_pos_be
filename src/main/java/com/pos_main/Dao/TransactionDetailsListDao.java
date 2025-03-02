package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Domain.TransactionDetailsList;
import com.pos_main.Dto.TransactionDetailsListDto;

/**
 * Title: TransactionDetailsListDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 14, 2025
 * @version 1.0
 **/

public interface TransactionDetailsListDao extends BaseDao<TransactionDetailsList> {
	
	List<TransactionDetailsListDto> getByTransactionId(Integer transactionId);
	
}
