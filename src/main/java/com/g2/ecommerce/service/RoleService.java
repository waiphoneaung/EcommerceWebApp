package com.g2.ecommerce.service;

import java.util.List;

import com.g2.ecommerce.dto.RoleDto;
import com.g2.ecommerce.model.Role;

public interface RoleService {
	void initializeRoles(List<String> roles) throws Exception;
	List<Role> getAllRoles();
	Role getRoleById(int role_id);
	void createRole(RoleDto roleDto);
	void editRole(int role_id, RoleDto roleDto);
	void deleteRole(int role_id);
}
