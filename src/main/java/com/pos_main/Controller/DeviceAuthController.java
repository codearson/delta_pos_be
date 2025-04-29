package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.DeviceAuthDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.DeviceAuthService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: DeviceAuthController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 29 Apr 2025
 * @time 19:36:58
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("deviceAuth")
public class DeviceAuthController {

    @Autowired
    DeviceAuthService deviceAuthService;

    @PostMapping("/register")
    public ResponseDto registerDeviceAuth(@RequestBody DeviceAuthDto deviceAuthDto) {
        log.info("DeviceAuthController.registerDeviceAuth() invoked with tillName: {}, tillId: {}", 
                deviceAuthDto.getTillName(), deviceAuthDto.getTillId());
        return deviceAuthService.registerDeviceAuth(deviceAuthDto);
    }
    
    @PutMapping("/approve")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto approveDeviceAuth(@RequestParam("id") Integer id) {
        log.info("DeviceAuthController.approveDeviceAuth() invoked with id: {}", id);
        return deviceAuthService.approveDeviceAuth(id);
    }
    
    @PutMapping("/decline")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto declineDeviceAuth(@RequestParam("id") Integer id) {
        log.info("DeviceAuthController.declineDeviceAuth() invoked with id: {}", id);
        return deviceAuthService.declineDeviceAuth(id);
    }
    
    @PutMapping("/block")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto blockDeviceAuth(@RequestParam("id") Integer id) {
        log.info("DeviceAuthController.blockDeviceAuth() invoked with id: {}", id);
        return deviceAuthService.blockDeviceAuth(id);
    }
    
    @PostMapping("/login")
    public ResponseDto loginDeviceAuth(@RequestParam("tillId") String tillId) {
        log.info("DeviceAuthController.loginDeviceAuth() invoked with tillId: {}", tillId);
        return deviceAuthService.loginDeviceAuth(tillId);
    }

}