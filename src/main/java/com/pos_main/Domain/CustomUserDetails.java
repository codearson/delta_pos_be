package com.pos_main.Domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	Collection<? extends GrantedAuthority> authorities;

	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		this.userName = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static CustomUserDetails build(User user) {
		List<GrantedAuthority> auths = new ArrayList<>();
		UserRole userType = user.getUserRole();
		auths.add(new SimpleGrantedAuthority("ROLE_" + userType.getUserRole().toUpperCase()));
		return new CustomUserDetails(user.getEmailAddress(), user.getPassword(), auths);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
