package com.pos_main.Service;

import com.pos_main.Dto.DeviceAuthDto;
import com.pos_main.Dto.ResponseDto;

/**
 * Title: DeviceAuthService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 29 Apr 2025
 * @time 19:37:18
 * @version 1.0
 **/

public interface DeviceAuthService {

    ResponseDto registerDeviceAuth(DeviceAuthDto deviceAuthDto);
    
    ResponseDto approveDeviceAuth(Integer id);
    
    ResponseDto declineDeviceAuth(Integer id);
    
    ResponseDto blockDeviceAuth(Integer id);
    
    ResponseDto loginDeviceAuth(String tillId);
    
    ResponseDto getAllPending();
    
    ResponseDto getAll();
    
    ResponseDto getByTillName(String tillName);
    
    ResponseDto getByTillId(String tillId);
    
    ResponseDto updateTillName(Integer id, String tillName);

}
