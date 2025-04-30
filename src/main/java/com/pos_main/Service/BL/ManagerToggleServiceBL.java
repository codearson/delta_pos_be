package com.pos_main.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.ManagerToggleDao;
import com.pos_main.Dto.ManagerToggleDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ManagerToggleServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 19:54:05
 * @version 1.0
 **/

@Slf4j
@Service
public class ManagerToggleServiceBL {
	
	@Autowired
    ManagerToggleDao managerToggleDao;
    
    public ManagerToggleDto save(ManagerToggleDto managerToggleDto) {
        log.info("ManagerToggleServiceBL.save() invoked.");
        return managerToggleDao.save(managerToggleDto);
    }
    
    public ManagerToggleDto update(ManagerToggleDto managerToggleDto) {
        log.info("ManagerToggleServiceBL.update() invoked");
        return managerToggleDao.update(managerToggleDto);
    }
    
    public ManagerToggleDto updateStatus(Integer id, Boolean status) {
        log.info("ManagerToggleServiceBL.updateStatus() invoked");
        ManagerToggleDto toggleDto = managerToggleDao.checkToggleAvailability(id);
        if (toggleDto != null) {
            toggleDto.setIsActive(status);
            return managerToggleDao.update(toggleDto);
        }
        return null;
    }
    
    public ManagerToggleDto updateAdminStatus(Integer id, Boolean status) {
        log.info("ManagerToggleServiceBL.updateStatus() invoked");
        ManagerToggleDto toggleDto = managerToggleDao.checkToggleAvailability(id);
        log.info("AAAAAAAAAAAAAA",toggleDto);
        if (toggleDto != null) {
            toggleDto.setAdminActive(status);
            return managerToggleDao.update(toggleDto);
        }
        return null;
    }
    
    public List<ManagerToggleDto> getAll() {
        log.info("ManagerToggleServiceBL.getAll() invoked");
        return managerToggleDao.getAll();
    }
    
    public List<ManagerToggleDto> getByName(String action) {
        log.info("ManagerToggleServiceBL.getByName() invoked");
        return managerToggleDao.getByName(action);
    }

}
