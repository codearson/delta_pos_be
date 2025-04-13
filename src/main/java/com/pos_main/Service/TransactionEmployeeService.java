package com.pos_main.Service;

import com.pos_main.Dto.ResponseDto;

/**
 * Title: TransactionEmployeeService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 17:44:45
 * @version 1.0
 **/

public interface TransactionEmployeeService {
	
    ResponseDto getByTransactionId(Integer transactionId);
    
}