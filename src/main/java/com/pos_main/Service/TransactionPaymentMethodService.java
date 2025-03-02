package com.pos_main.Service;

import com.pos_main.Dto.ResponseDto;

/**
 * Title: TransactionPaymentMethodService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 14, 2025
 * @version 1.0
 **/

public interface TransactionPaymentMethodService {
	
	ResponseDto getByTransactionId(Integer transactionId);
	
}
