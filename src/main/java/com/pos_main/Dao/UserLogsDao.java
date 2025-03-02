package com.pos_main.Dao;

import com.pos_main.Domain.UserLogs;
import com.pos_main.Dto.UserLogsDto;

/**
 * Title: StockDao.java. Company: www.codearson.com Copyright: Copyright (c)
 * 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 16:47:32
 * @version 1.0
 **/

public interface UserLogsDao extends BaseDao<UserLogs>{
	
	UserLogsDto login(UserLogsDto userLogsDto);
	
	UserLogsDto save(UserLogsDto userLogsDto);

}
