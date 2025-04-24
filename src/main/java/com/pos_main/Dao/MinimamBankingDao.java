package com.pos_main.Dao;

import java.util.List;
import com.pos_main.Dto.MinimamBankingDto;

/**
 * Title: MinimamBankingDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 24 Apr 2025
 * @time 23:03:37
 * @version 1.0
 **/

public interface MinimamBankingDao {
    
    MinimamBankingDto save(MinimamBankingDto minimamBankingDto);
    
    MinimamBankingDto update(MinimamBankingDto minimamBankingDto);
    
    List<MinimamBankingDto> getAll();
}
