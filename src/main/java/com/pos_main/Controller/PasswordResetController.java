package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.PasswordResetRequestDto;
import com.pos_main.Dto.ResetPasswordDto;
import com.pos_main.Service.PasswordResetService;
import com.pos_main.Service.BL.PasswordResetServiceBL;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: PasswordResetController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 19, 2025
 * @version 1.0
 **/

@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;
    @Autowired
    private PasswordResetServiceBL passwordResetServiceBL;

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody PasswordResetRequestDto request) {
    	String message = passwordResetServiceBL.forgotPassword(request);
        return message;
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody ResetPasswordDto request) {
        passwordResetService.resetPassword(request);
        return "Password reset successfully!";
    }
    
}

