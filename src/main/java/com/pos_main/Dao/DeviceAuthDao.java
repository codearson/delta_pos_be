package com.pos_main.Dao;

import com.pos_main.Domain.DeviceAuth;
import com.pos_main.Dto.DeviceAuthDto;

/**
 * Title: DeviceAuthDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 29 Apr 2025
 * @time 19:40:32
 * @version 1.0
 **/

public interface DeviceAuthDao extends BaseDao<DeviceAuth> {

    DeviceAuthDto saveDeviceAuth(DeviceAuthDto deviceAuthDto);
    
    DeviceAuth getDeviceAuthById(Integer id);
    
    DeviceAuth getDeviceAuthByTillId(String tillId);

}