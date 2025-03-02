package com.pos_main.Service.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos_main.Domain.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtil {

	@Value("${pos.web.jwtSecret}")
	private String jwtSecret;

	private final ObjectMapper objectMapper = new ObjectMapper();

	private final Map<String, Date> previousTokenExpiry = new HashMap<>();

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public boolean validateToken(String token) {
		try {
			if (!isTokenExpired(token)) {
				String username = extractUsername(token);
				Date previousExpiry = previousTokenExpiry.get(username);
				Date currentExpiry = extractExpiration(token);
				if (!previousTokenExpiry.containsKey(username)
						|| currentExpiry.after(previousExpiry)) {
					return true;
				} else {
					log.error("Old token is being used after generating a new token.");
				}
			}
		} catch (Exception e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		}
		return false;
	}

	public String generateToken(CustomUserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("roles", userDetails.getAuthorities());
		String username = userDetails.getUsername();
		Date expiryDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60); // 1 hour expiration
//		previousTokenExpiry.put(username, expiryDate);
		// Revoke existing tokens for this user
		previousTokenExpiry.remove(username);
		return createToken(claims, username, expiryDate);
	}

	private String createToken(Map<String, Object> claims, String subject, Date expiryDate) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(getSignKey()).compact();
	}

	private SecretKey getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
