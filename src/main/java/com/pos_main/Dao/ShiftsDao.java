package com.pos_main.Dao;

import com.pos_main.Domain.Shifts;
import com.pos_main.Dto.ShiftsDto;


/**
 * Title: ShiftsDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 19:50:50
 * @version 1.0
 **/

public interface ShiftsDao extends BaseDao<Shifts>{
	
	ShiftsDto save(ShiftsDto shiftsDto);

}
