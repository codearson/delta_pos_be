package com.pos_main.Service;

import com.pos_main.Dto.PasswordResetRequestDto;
import com.pos_main.Dto.ResetPasswordDto;
import com.pos_main.Dto.ResponseDto;

/**
 * Title: PasswordResetService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 19, 2025
 * @version 1.0
 **/

public interface PasswordResetService {
	
	ResponseDto forgotPassword(PasswordResetRequestDto request);
	
	ResponseDto resetPassword(ResetPasswordDto request);
	
}
