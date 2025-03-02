package com.pos_main.Service.BL;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pos_main.Dao.PasswordResetTokenDao;
import com.pos_main.Dao.UserDao;
import com.pos_main.Domain.PasswordResetToken;
import com.pos_main.Domain.User;
import com.pos_main.Dto.PasswordResetRequestDto;
import com.pos_main.Dto.ResetPasswordDto;
import com.pos_main.Dto.UserDto;
import com.pos_main.Dto.UserLogsDto;
import com.pos_main.Service.EmailService;
import com.pos_main.Service.UserLogsService;
import com.pos_main.Transformer.PasswordResetTokenTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: PasswordResetServiceBL.java. Company: www.codearson.com | Copyright:
 * Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Feb 21, 2025.
 * @version 1.0
 **/

@Slf4j
@Component
public class PasswordResetServiceBL {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordResetTokenDao tokenDao;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    private UserLogsService userLogsService;

	public boolean forgotPassword(PasswordResetRequestDto request) {
		log.info("PasswordResetServiceBL.forgotPassword processing for email: {}", request.getEmail());

		Optional<User> userOpt = Optional.ofNullable(userDao.findByByEmail(request.getEmail()));

		if (userOpt.isEmpty()) {
			log.warn("User not found for email: {}", request.getEmail());
			return false;
		}

		User user = userOpt.get();
		PasswordResetToken token = PasswordResetTokenTransformer.toEntity(user);
		tokenDao.save(token);

		String emailBody = "Hi " + user.getFirstName() + ",\n\n" + "Your password reset code is: *" + token.getToken()
				+ "*\n\n" + "This token will expire in 1 hour.\n\n" + "Regards,\nDelta POS";

		emailService.sendEmail(request.getEmail(), "Password Reset Request", emailBody);

		log.info("Password reset email sent to: {}", request.getEmail());
		return true;
	}

	public boolean resetPassword(ResetPasswordDto request) {
		log.info("PasswordResetServiceBL.resetPassword processing.");

		PasswordResetToken token = tokenDao.findByToken(request.getToken());

		if (token == null || token.getExpiryTokenTime().isBefore(LocalDateTime.now())) {
			log.warn("Invalid or expired token.");
			return false;
		}

		User user = token.getUser();
		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		userDao.update(user);

		log.info("Password reset successfully for user: {}", user.getEmailAddress());
		
//      Prepare UserLogsDto


        UserLogsDto userLogsDto = new UserLogsDto();
        UserDto userDto = new UserDto();
        userDto.setId(user.getId()); // Set user ID in UserDto
        userLogsDto.setUserDto(userDto); // Set UserDto in UserLogsDto
        userLogsDto.setSignOff(false);
        userLogsDto.setLogIn(LocalDateTime.now());
        userLogsDto.setDescription("Password reset successfully");

        // Call UserLogsService directly
        userLogsService.save(userLogsDto);

        log.info("User log saved successfully for user ID: {}", user.getId());
        
		return true;
	}
}
