package com.pos_main.Dto;

import lombok.Data;

/**
 * Title: ResetPasswordDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 19, 2025
 * @version 1.0
 **/

@Data
public class ResetPasswordDto {

	private String token;
    private String newPassword;

}
