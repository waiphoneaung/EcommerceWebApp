package com.g2.ecommerce.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.g2.ecommerce.model.Role;
import com.g2.ecommerce.model.User;

public class LoginUserDetail implements UserDetails {
	private static final long serialVersionUID = 1L;
	private  final User user;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getUser_roles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}
	
	public LoginUserDetail(User user) {
		this.user = user;
	}
	
	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
	
	public User getUser() {
		return this.user;
	}
}
