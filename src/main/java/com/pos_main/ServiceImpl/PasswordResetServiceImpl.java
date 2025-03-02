package com.pos_main.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.PasswordResetTokenDao;
import com.pos_main.Dao.UserDao;
import com.pos_main.Domain.PasswordResetToken;
import com.pos_main.Domain.User;
import com.pos_main.Dto.PasswordResetRequestDto;
import com.pos_main.Dto.ResetPasswordDto;
import com.pos_main.Dto.UserDto;
import com.pos_main.Dto.UserLogsDto;
import com.pos_main.Service.EmailService;
import com.pos_main.Service.PasswordResetService;
import com.pos_main.Service.UserLogsService;
import com.pos_main.Transformer.PasswordResetTokenTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: PasswordResetServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 19, 2025
 * @version 1.0
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PasswordResetRequestDto;
import com.pos_main.Dto.ResetPasswordDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.BL.PasswordResetServiceBL;
import com.pos_main.Service.PasswordResetService;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private PasswordResetServiceBL passwordResetServiceBL;

    @Override
    public ResponseDto forgotPassword(PasswordResetRequestDto request) {
        log.info("PasswordResetServiceImpl.forgotPassword invoked for email: {}", request.getEmail());
        ResponseDto responseDto;
        
        try {
            boolean success = passwordResetServiceBL.forgotPassword(request);
            
            if (success) {
                log.info("Password reset email sent successfully.");
                responseDto = serviceUtil.getServiceResponse("Reset password link sent successfully!");
            } else {
                log.warn("Failed to send reset password email.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_FORGOT_PASSWORD_EMAIL);
            }
        } catch (Exception e) {
            log.error("Exception in forgotPassword process.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_FORGOT_PASSWORD_EMAIL);
        }
        
        return responseDto;
    }

    @Override
    public ResponseDto resetPassword(ResetPasswordDto request) {
        log.info("PasswordResetServiceImpl.resetPassword invoked.");
        ResponseDto responseDto;

        try {
            boolean success = passwordResetServiceBL.resetPassword(request);
            
            if (success) {
                log.info("Password reset successfully.");
                responseDto = serviceUtil.getServiceResponse("Password reset successfully!");
            } else {
                log.warn("Password reset failed.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RESET_PASSWORD);
            }
        } catch (Exception e) {
            log.error("Exception in resetPassword process.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RESET_PASSWORD);
        }
        
        return responseDto;
    }
}
