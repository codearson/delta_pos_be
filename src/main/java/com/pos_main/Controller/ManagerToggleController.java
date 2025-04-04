package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.DiscountDto;
import com.pos_main.Dto.ManagerToggleDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.ManagerToggleService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ManagerToggleController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 19:47:42
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("managerToggle")
public class ManagerToggleController {
	
	@Autowired
    ManagerToggleService managerToggleService;
    
    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto save(@RequestBody ManagerToggleDto managerToggleDto) {
        log.info("ManagerToggleController.save() invoked");
        return managerToggleService.save(managerToggleDto);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto update(@RequestBody ManagerToggleDto managerToggleDto) {
        log.info("ManagerToggleController.update() invoked");
        return managerToggleService.update(managerToggleDto);
    }
    
    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateStatus(@RequestParam("id") Integer id, @RequestParam("status") Boolean status) {
        log.info("ManagerToggleController.updateStatus() invoked");
        return managerToggleService.updateStatus(id, status);
    }
    
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAll() {
        log.info("ManagerToggleController.getAll() invoked");
        return managerToggleService.getAll();
    }
    
    @GetMapping("/getByName")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getByName(@RequestParam("action") String action) {
        log.info("ManagerToggleController.getByName() invoked");
        return managerToggleService.getByName(action);
    }

}
