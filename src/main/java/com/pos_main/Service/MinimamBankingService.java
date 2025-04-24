package com.pos_main.Service;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.MinimamBankingDto;

/**
 * Title: MinimamBankingService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 24 Apr 2025
 * @time 23:02:47
 * @version 1.0
 **/

public interface MinimamBankingService {
    
    ResponseDto save(MinimamBankingDto minimamBankingDto);
    
    ResponseDto update(MinimamBankingDto minimamBankingDto);
    
    ResponseDto getAll();
}
