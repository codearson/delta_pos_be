package com.pos_main.Service.BL;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.UserLogsDao;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.UserLogsDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StockServiceBL.java. Company: www.codearson.com Copyright: Copyright
 * (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 16:45:27
 * @version 1.0
 **/

@Slf4j
@Service
public class UserLogsServiceBL {

	@Autowired
	UserLogsDao userLogsDao;
	
	public UserLogsDto login(UserLogsDto userLogsDto) {
        log.info("UserLogsServiceBL.save() invoked.");
        userLogsDto.setSignOff(true);
        userLogsDto.setLogIn(LocalDateTime.now());
        userLogsDto.setLogOut(null);
        userLogsDto.setDescription("Login");
        return userLogsDao.login(userLogsDto);
    }
	
	public UserLogsDto save(UserLogsDto userLogsDto) {
        log.info("UserLogsServiceBL.save() invoked.");
        return userLogsDao.login(userLogsDto);
    }
	
	public PaginatedResponseDto getAllPageUserLogs(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("UserLogsServiceBL.getAllPageUserLogs()invoked");
		return userLogsDao.getAllPageUserLogs(pageNumber, pageSize, searchParams);
	}
}
