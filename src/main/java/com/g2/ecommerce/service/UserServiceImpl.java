package com.g2.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.dto.ProfileDto;
import com.g2.ecommerce.dto.UserDto;
import com.g2.ecommerce.model.Profile;
import com.g2.ecommerce.model.Role;
import com.g2.ecommerce.model.User;
import com.g2.ecommerce.repository.ProfileRepository;
import com.g2.ecommerce.repository.RoleRepository;
import com.g2.ecommerce.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private ProfileRepository profileRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@Override
	public User getUserById(int user_id) {
		return userRepository.findById(user_id).orElse(null);
	}
	
	@Override
	public long getUserCount() {
		return userRepository.count();
	}
	
	@Override
	@Transactional
	public void registerCustomer(UserDto userDto, ProfileDto profileDto) {
//		if (userRepository.findByUsername(userDto.getUsername()) != null) {
//			throw new RuntimeException("Username already exists.");
//		}
//		
//		if (profileRepository.findByEmail(profileDto.getEmail()) != null) {
//			throw new RuntimeException("Email already exists.");
//		}
		User user = new User();
		Profile profile = new Profile();
		user.setName(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		profile.setPhone(profileDto.getPhone_number());
		profile.setEmail(profileDto.getEmail());
		profile.setAddress(profileDto.getAddress());
		profile.setUser(user);
		
		user.setProfile(profile);
		
		userRepository.save(user);
		profileRepository.save(profile);
	}
	
	@Override
	public void editUser(int user_id, UserDto userDto) {
		User user = getUserById(user_id);
		user.setName(userDto.getUsername());
		user.getProfile().setAddress(userDto.getProfileDto().getAddress());
		user.getProfile().setEmail(userDto.getProfileDto().getEmail());
		user.getProfile().setPhone(userDto.getProfileDto().getPhone_number());
		
		userRepository.save(user);
	}
	
	@Override
	public void saveRole(int role_id, int user_id) {
		Role role = roleService.getRoleById(role_id);
		User user = getUserById(user_id);
		
		if (role !=  null && user != null) {
			user.getUser_roles().add(role);
			role.getUsers().add(user);
		}
		userRepository.save(user);
		roleRepository.save(role);
	}
	
	@Override
	public void deleteUser(int user_id) {
		User user = getUserById(user_id);
		userRepository.delete(user);
	}
}
