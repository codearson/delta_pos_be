package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Domain.TransactionEmployee;
import com.pos_main.Dto.TransactionEmployeeDto;

/**
 * Title: TransactionEmployeeDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 17:45:48
 * @version 1.0
 **/

public interface TransactionEmployeeDao extends BaseDao<TransactionEmployee> {
	
    List<TransactionEmployeeDto> getByTransactionId(Integer transactionId);
    
}