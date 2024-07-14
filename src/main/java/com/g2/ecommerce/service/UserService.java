package com.g2.ecommerce.service;

import java.util.List;

import com.g2.ecommerce.dto.ProfileDto;
import com.g2.ecommerce.dto.UserDto;
import com.g2.ecommerce.model.User;

public interface UserService {
	List<User> getAllUsers();
	User getUserById(int user_id);
	long getUserCount();
 	void registerCustomer(UserDto userDto, ProfileDto profileDto);
	void saveRole(int role_id, int user_id);
	void editUser(int user_id, UserDto userDto);
	void deleteUser(int user_id);
}
