package com.pos_main.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.ManagerToggleDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.ManagerToggleService;
import com.pos_main.Service.BL.ManagerToggleServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ManagerToggleServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 19:53:36
 * @version 1.0
 **/

@Slf4j
@Service
public class ManagerToggleServiceImpl implements ManagerToggleService {
    
    @Autowired
    ServiceUtil serviceUtil;
    
    @Autowired
    ManagerToggleServiceBL managerToggleServiceBL;

    @Override
    public ResponseDto save(ManagerToggleDto managerToggleDto) {
        log.info("ManagerToggleServiceImpl.save invoked");
        ResponseDto responseDto = null;
        try {
            ManagerToggleDto savedDto = managerToggleServiceBL.save(managerToggleDto);
            if (savedDto != null) {
                log.info("Manager Toggle Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedDto);
            } else {
                log.info("Unable to save Manager Toggle details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_MANAGER_TOGGLE_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving Manager Toggle details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_MANAGER_TOGGLE_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto update(ManagerToggleDto managerToggleDto) {
        log.info("ManagerToggleServiceImpl.update() invoked");
        ResponseDto responseDto = null;
        try {
            ManagerToggleDto updatedDto = managerToggleServiceBL.update(managerToggleDto);
            if (updatedDto != null) {
                log.info("Manager Toggle Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedDto);
            } else {
                log.info("Unable to update Manager Toggle details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_MANAGER_TOGGLE_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Manager Toggle details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_MANAGER_TOGGLE_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateStatus(Integer id, Boolean status) {
        log.info("ManagerToggleServiceImpl.updateStatus() invoked");
        ResponseDto responseDto = null;
        try {
            ManagerToggleDto updatedStatusDto = managerToggleServiceBL.updateStatus(id, status);
            if (updatedStatusDto != null) {
                log.info("Manager Toggle Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedStatusDto);
            } else {
                log.info("Unable to update Manager Toggle status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_MANAGER_TOGGLE_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Manager Toggle status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_MANAGER_TOGGLE_STATUS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto updateAdminStatus(Integer id, Boolean status) {
        log.info("ManagerToggleServiceImpl.updateStatus() invoked");
        ResponseDto responseDto = null;
        try {
            ManagerToggleDto updatedStatusDto = managerToggleServiceBL.updateAdminStatus(id, status);
            if (updatedStatusDto != null) {
                log.info("Manager Toggle Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedStatusDto);
            } else {
                log.info("Unable to update Manager Toggle status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_MANAGER_TOGGLE_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Manager Toggle status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_MANAGER_TOGGLE_STATUS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAll() {
        log.info("ManagerToggleServiceImpl.getAll() invoked");
        ResponseDto responseDto;
        try {
            List<ManagerToggleDto> toggleList = managerToggleServiceBL.getAll();
            responseDto = (toggleList != null && !toggleList.isEmpty())
                ? serviceUtil.getServiceResponse(toggleList)
                : serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_MANAGER_TOGGLE_DETAILS);
        } catch (Exception e) {
            log.error("Exception occurs while retrieving All Manager Toggle details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_MANAGER_TOGGLE_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getByName(String action) {
        log.info("ManagerToggleServiceImpl.getByName() invoked");
        ResponseDto responseDto;
        try {
            List<ManagerToggleDto> toggleList = managerToggleServiceBL.getByName(action);
            responseDto = (toggleList != null && !toggleList.isEmpty())
                ? serviceUtil.getServiceResponse(toggleList)
                : serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_MANAGER_TOGGLE_BY_NAME);
        } catch (Exception e) {
            log.error("Exception occurs while retrieving Manager Toggle by action.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_MANAGER_TOGGLE_BY_NAME);
        }
        return responseDto;
    }
}