package com.pos_main.Service;

import com.pos_main.Dto.ResponseDto;

/**
 * Title: TransactionDetailsListService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 14, 2025
 * @version 1.0
 **/

public interface TransactionDetailsListService {
	
	ResponseDto getByTransactionId(Integer transactionId);
	
	ResponseDto getAll();
	
}
