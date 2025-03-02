package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.PasswordResetRequestDto;
import com.pos_main.Dto.ResetPasswordDto;
import com.pos_main.Service.PasswordResetService;

/**
 * Title: PasswordResetController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 19, 2025
 * @version 1.0
 **/

@RestController
@RequestMapping("/auth")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody PasswordResetRequestDto request) {
        passwordResetService.forgotPassword(request);
        return "Reset password link sent successfully!";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody ResetPasswordDto request) {
        passwordResetService.resetPassword(request);
        return "Password reset successfully!";
    }
}

