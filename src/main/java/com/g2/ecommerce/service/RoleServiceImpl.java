package com.g2.ecommerce.service;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.dto.RoleDto;
import com.g2.ecommerce.model.Role;
import com.g2.ecommerce.model.User;
import com.g2.ecommerce.repository.RoleRepository;

import jakarta.transaction.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	@Lazy
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
//	private final RoleRepository roleRepository;
//	
//	public RoleServiceImpl(RoleRepository roleRepository) {
//		this.roleRepository = roleRepository;
//	}

	@Override
	@Transactional
	public void initializeRoles(List<String> roles) throws Exception {
		for (String roleName : roles) {
			try {
				if (roleRepository.findByName(roleName).isEmpty()) {
					Role role = new Role();
					role.setRole(roleName);
					roleRepository.save(role);
					logger.info("Role {} has been inserted.",roleName);
				}
				else {
					logger.info("Role {} already exists.", roleName);
				}
			}
			catch (DataAccessException e){
				logger.error("Database access error while inserting role {}: {}", roleName, e.getMessage());
				throw new Exception("Error while accessing the database for role: " + roleName, e);
			}
			catch (Exception e) {
				logger.error("Unexpected error while inserting role {}: {}", roleName,e.getMessage());
				throw new Exception("Unexpected error while inserting role: " + roleName, e);
			}
		}
	}
	
	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role getRoleById(int role_id) {
		return roleRepository.findById(role_id).orElse(null);
	}

	@Override
	public void createRole(RoleDto roleDto) {
		Role role = new Role();
		role.setRole(roleDto.getRole());

		roleRepository.save(role);
	}

	@Override
	public void editRole(int role_id, RoleDto roleDto) {
		Role role = getRoleById(role_id);
		if (role != null) {
			role.setRole(roleDto.getRole());
			roleRepository.save(role);
		}
	}

	@Override
	public void deleteRole(int role_id) {
		Role role = getRoleById(role_id);

		if (role != null) {
			List<User> users = userService.getAllUsers();
			for (User user : users) {
				Iterator<Role> iterator = user.getUser_roles().iterator();
				while (iterator.hasNext()) {
					Role userRole = iterator.next();
					if (userRole.getRole().equals(role.getRole())) {
						iterator.remove();
					}
				}
			}

			roleRepository.delete(role);
		}

//		List<User> users = userService.getAllUsers();
//		for (User user : users) {
//			for (Role user_role : user.getUser_roles()) {
//				if (user_role.getRole() == role.getRole()) {
//					user.getUser_roles().remove(role);
//				}
//			}
//		}
//		roleRepository.delete(role);
	}
}
