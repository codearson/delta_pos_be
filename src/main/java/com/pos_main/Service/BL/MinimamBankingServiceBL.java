package com.pos_main.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.MinimamBankingDao;
import com.pos_main.Dto.MinimamBankingDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: MinimamBankingServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 24 Apr 2025
 * @time 23:03:26
 * @version 1.0
 **/

@Slf4j
@Service
public class MinimamBankingServiceBL {
    
    @Autowired
    MinimamBankingDao minimamBankingDao;
    
    public MinimamBankingDto save(MinimamBankingDto minimamBankingDto) {
        log.info("MinimamBankingServiceBL.save() invoked.");
        return minimamBankingDao.save(minimamBankingDto);
    }
    
    public MinimamBankingDto update(MinimamBankingDto minimamBankingDto) {
        log.info("MinimamBankingServiceBL.update() invoked");
        return minimamBankingDao.update(minimamBankingDto);
    }
    
    public List<MinimamBankingDto> getAll() {
        log.info("MinimamBankingServiceBL.getAll() invoked");
        return minimamBankingDao.getAll();
    }
}
