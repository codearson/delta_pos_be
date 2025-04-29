package com.pos_main.Service.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.DeviceAuthDao;
import com.pos_main.Domain.DeviceAuth;
import com.pos_main.Dto.DeviceAuthDto;
import com.pos_main.Transformer.DeviceAuthTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: DeviceAuthServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 29 Apr 2025
 * @time 19:40:04
 * @version 1.0
 **/

@Slf4j
@Service
public class DeviceAuthServiceBL {

    @Autowired
    private DeviceAuthDao deviceAuthDao;

    @Autowired
    private DeviceAuthTransformer deviceAuthTransformer;

    public DeviceAuthDto registerDeviceAuth(DeviceAuthDto deviceAuthDto) {
        log.info("DeviceAuthServiceBL.registerDeviceAuth() invoked.");
        deviceAuthDto.setApproveStatus("Pending");
        deviceAuthDto.setLoginStatus("False");
        deviceAuthDto.setIsActive("1");
        return deviceAuthDao.saveDeviceAuth(deviceAuthDto);
    }
    
    public DeviceAuthDto approveDeviceAuth(Integer id) {
        log.info("DeviceAuthServiceBL.approveDeviceAuth() invoked with id: {}", id);
        DeviceAuth deviceAuth = deviceAuthDao.getDeviceAuthById(id);
        if (deviceAuth != null) {
            deviceAuth.setApproveStatus("Approved");
            deviceAuth.setLoginStatus("True");
            deviceAuth.setIsActive("1");
            DeviceAuth updatedDeviceAuth = deviceAuthDao.saveOrUpdate(deviceAuth);
            return deviceAuthTransformer.transform(updatedDeviceAuth);
        }
        return null;
    }
    
    public DeviceAuthDto declineDeviceAuth(Integer id) {
        log.info("DeviceAuthServiceBL.declineDeviceAuth() invoked with id: {}", id);
        DeviceAuth deviceAuth = deviceAuthDao.getDeviceAuthById(id);
        if (deviceAuth != null) {
            deviceAuth.setApproveStatus("Declined");
            deviceAuth.setLoginStatus("False");
            deviceAuth.setIsActive("0");
            DeviceAuth updatedDeviceAuth = deviceAuthDao.saveOrUpdate(deviceAuth);
            return deviceAuthTransformer.transform(updatedDeviceAuth);
        }
        return null;
    }
    
    public DeviceAuthDto blockDeviceAuth(Integer id) {
        log.info("DeviceAuthServiceBL.blockDeviceAuth() invoked with id: {}", id);
        DeviceAuth deviceAuth = deviceAuthDao.getDeviceAuthById(id);
        if (deviceAuth != null) {
            deviceAuth.setLoginStatus("False");
            DeviceAuth updatedDeviceAuth = deviceAuthDao.saveOrUpdate(deviceAuth);
            return deviceAuthTransformer.transform(updatedDeviceAuth);
        }
        return null;
    }
    
    public DeviceAuthDto loginDeviceAuth(String tillId) {
        log.info("DeviceAuthServiceBL.loginDeviceAuth() invoked with tillId: {}", tillId);
        DeviceAuth deviceAuth = deviceAuthDao.getDeviceAuthByTillId(tillId);
        if (deviceAuth != null) {
            if ("Approved".equals(deviceAuth.getApproveStatus()) && "True".equals(deviceAuth.getLoginStatus())) {
                log.info("Login successful for tillId: {}", tillId);
                return deviceAuthTransformer.transform(deviceAuth);
            } else {
                log.info("Login unsuccessful for tillId: {}. approveStatus: {}, loginStatus: {}", 
                        tillId, deviceAuth.getApproveStatus(), deviceAuth.getLoginStatus());
                return null;
            }
        }
        log.info("No DeviceAuth found for tillId: {}", tillId);
        return null;
    }
}