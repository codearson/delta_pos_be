package com.pos_main.ServiceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.DeviceAuthDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.DeviceAuthService;
import com.pos_main.Service.BL.DeviceAuthServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: DeviceAuthServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 29 Apr 2025
 * @time 19:37:37
 * @version 1.0
 **/
@Component
@Slf4j
@Service
public class DeviceAuthServiceImpl implements DeviceAuthService {

    @Autowired
    private DeviceAuthServiceBL deviceAuthServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Transactional
    @Override
    public ResponseDto registerDeviceAuth(DeviceAuthDto deviceAuthDto) {
        log.info("DeviceAuthServiceImpl.registerDeviceAuth() invoked");
        ResponseDto responseDto = null;
        try {
            DeviceAuthDto savedDeviceAuthDto = deviceAuthServiceBL.registerDeviceAuth(deviceAuthDto);
            if (savedDeviceAuthDto != null) {
                log.info("DeviceAuth details saved.");
                responseDto = serviceUtil.getServiceResponse(savedDeviceAuthDto);
            } else {
                log.info("Unable to save DeviceAuth details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_DEVICE_AUTH_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving DeviceAuth details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_DEVICE_AUTH_DETAILS);
        }
        return responseDto;
    }
    
    @Transactional
    @Override
    public ResponseDto approveDeviceAuth(Integer id) {
        log.info("DeviceAuthServiceImpl.approveDeviceAuth() invoked");
        try {
            DeviceAuthDto approvedDeviceAuthDto = deviceAuthServiceBL.approveDeviceAuth(id);
            if (approvedDeviceAuthDto != null) {
                return serviceUtil.getServiceResponse(approvedDeviceAuthDto);
            } else {
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_APPROVE_DEVICE_AUTH);
            }
        } catch (Exception e) {
            log.error("Exception while approving DeviceAuth", e);
            return serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_APPROVE_DEVICE_AUTH);
        }
    }
    
    @Override
    public ResponseDto declineDeviceAuth(Integer id) {
        log.info("DeviceAuthServiceImpl.declineDeviceAuth() invoked");
        try {
            DeviceAuthDto declinedDeviceAuthDto = deviceAuthServiceBL.declineDeviceAuth(id);
            if (declinedDeviceAuthDto != null) {
                return serviceUtil.getServiceResponse(declinedDeviceAuthDto);
            } else {
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_DECLINE_DEVICE_AUTH);
            }
        } catch (Exception e) {
            log.error("Exception while declining DeviceAuth", e);
            return serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_DECLINE_DEVICE_AUTH);
        }
    }
    
    @Transactional
    @Override
    public ResponseDto blockDeviceAuth(Integer id) {
        log.info("DeviceAuthServiceImpl.blockDeviceAuth() invoked");
        try {
            DeviceAuthDto blockedDeviceAuthDto = deviceAuthServiceBL.blockDeviceAuth(id);
            if (blockedDeviceAuthDto != null) {
                return serviceUtil.getServiceResponse(blockedDeviceAuthDto);
            } else {
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_BLOCK_DEVICE_AUTH);
            }
        } catch (Exception e) {
            log.error("Exception while blocking DeviceAuth", e);
            return serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_BLOCK_DEVICE_AUTH);
        }
    }
    
    @Override
    public ResponseDto loginDeviceAuth(String tillId) {
        log.info("DeviceAuthServiceImpl.loginDeviceAuth() invoked with tillId: {}", tillId);
        try {
            DeviceAuthDto deviceAuthDto = deviceAuthServiceBL.loginDeviceAuth(tillId);
            if (deviceAuthDto != null) {
                return serviceUtil.getServiceResponse(deviceAuthDto);
            } else {
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_LOGIN_DEVICE_AUTH);
            }
        } catch (Exception e) {
            log.error("Exception while logging in DeviceAuth", e);
            return serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_LOGIN_DEVICE_AUTH);
        }
    }
    
    @Override
    public ResponseDto getAllPending() {
        log.info("DeviceAuthServiceImpl.getAllPending() invoked");
        try {
            List<DeviceAuthDto> pendingDevices = deviceAuthServiceBL.getAllPending();
            if (!pendingDevices.isEmpty()) {
                return serviceUtil.getServiceResponse(pendingDevices);
            } else {
                return serviceUtil.getErrorServiceResponse(
                		ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_ALL_PENDING_TILL_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception while retrieving pending DeviceAuth records", e);
            return serviceUtil.getExceptionServiceResponseByProperties(
            		ApplicationMessageConstants.ServiceErrorMessages.EX_GET_ALL_PENDING_TILL_DETAILS);
        }
    }
    
    @Override
    public ResponseDto getAll() {
        log.info("DeviceAuthServiceImpl.getAll() invoked");
        try {
            List<DeviceAuthDto> devices = deviceAuthServiceBL.getAll();
            if (!devices.isEmpty()) {
                return serviceUtil.getServiceResponse(devices);
            } else {
                return serviceUtil.getErrorServiceResponse(
                		ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_ALL_TILL_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception while retrieving approved or declined DeviceAuth records", e);
            return serviceUtil.getExceptionServiceResponseByProperties(
            		ApplicationMessageConstants.ServiceErrorMessages.EX_GET_ALL_TILL_DETAILS);
        }
    }
    
    @Override
    public ResponseDto getByTillName(String tillName) {
        log.info("DeviceAuthServiceImpl.getByTillName() invoked with tillName: {}", tillName);
        try {
            DeviceAuthDto deviceAuthDto = deviceAuthServiceBL.getByTillName(tillName);
            if (deviceAuthDto != null) {
                return serviceUtil.getServiceResponse(deviceAuthDto);
            } else {
                return serviceUtil.getErrorServiceResponse(
                		ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_BY_TILL_NAME_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception while retrieving DeviceAuth by tillName", e);
            return serviceUtil.getExceptionServiceResponseByProperties(
            		ApplicationMessageConstants.ServiceErrorMessages.EX_GET_BY_TILL_NAME_DETAILS);
        }
    }

    @Override
    public ResponseDto getByTillId(String tillId) {
        log.info("DeviceAuthServiceImpl.getByTillId() invoked with tillId: {}", tillId);
        try {
            DeviceAuthDto deviceAuthDto = deviceAuthServiceBL.getByTillId(tillId);
            if (deviceAuthDto != null) {
                return serviceUtil.getServiceResponse(deviceAuthDto);
            } else {
                return serviceUtil.getErrorServiceResponse(
                		ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_BY_TILL_ID_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception while retrieving DeviceAuth by tillId", e);
            return serviceUtil.getExceptionServiceResponseByProperties(
            		ApplicationMessageConstants.ServiceErrorMessages.EX_GET_BY_TILL_ID_DETAILS);
        }
    }
    
    @Transactional
    @Override
    public ResponseDto updateTillName(Integer id, String tillName) {
        log.info("DeviceAuthServiceImpl.updateTillName() invoked with id: {}, tillName: {}", id, tillName);
        try {
            DeviceAuthDto updatedDeviceAuthDto = deviceAuthServiceBL.updateTillName(id, tillName);
            if (updatedDeviceAuthDto != null) {
                return serviceUtil.getServiceResponse(updatedDeviceAuthDto);
            } else {
                return serviceUtil.getErrorServiceResponse(
                		ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_TILL_NAME_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception while updating till name for id: {}", id, e);
            return serviceUtil.getExceptionServiceResponseByProperties(
            		ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_TILL_NAME_DETAILS);
        }
    }
}