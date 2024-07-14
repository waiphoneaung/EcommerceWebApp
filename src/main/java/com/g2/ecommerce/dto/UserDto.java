package com.g2.ecommerce.dto;

import com.g2.ecommerce.model.Role;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {
	@NotEmpty(message = "Username is invalid.")
	private String username;

	@Size(min = 8, message = "password must be at least 8 characters.")
	private String password;

	private Role role;
	
	@Valid
	private ProfileDto profileDto;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ProfileDto getProfileDto() {
		return profileDto;
	}

	public void setProfileDto(ProfileDto profileDto) {
		this.profileDto = profileDto;
	}

	public UserDto() {

	}
}