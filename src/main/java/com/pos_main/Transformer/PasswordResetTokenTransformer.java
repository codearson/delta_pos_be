package com.pos_main.Transformer;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

import com.pos_main.Domain.PasswordResetToken;
import com.pos_main.Domain.User;

/**
 * Title: PasswordResetTokenTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 19, 2025
 * @version 1.0
 **/

public class PasswordResetTokenTransformer {
	
	public static String generateRandomToken(int length) {
	    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    SecureRandom random = new SecureRandom();
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0; i < length; i++) {
	        sb.append(chars.charAt(random.nextInt(chars.length())));
	    }
	    
	    return sb.toString();
	}

	public static PasswordResetToken toEntity(User user) {
	    PasswordResetToken token = new PasswordResetToken();
	    token.setUser(user);
	    token.setToken(generateRandomToken(6)); // Generate a 6-character alphanumeric token
	    token.setExpiryTokenTime(LocalDateTime.now().plusHours(1));
	    return token;
	}
	
}
