package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.UserLogsDto;

/**
 * Title: UserLogsService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 16:38:16
 * @version 1.0
 **/

public interface UserLogsService {
	
	public ResponseDto login(UserLogsDto userLogsDto);
	
	public ResponseDto save(UserLogsDto userLogsDto);
	
	public ResponseDto getAllPageUserLogs(int pageNumber, int pageSize, Map<String, String> searchParameters);

}
