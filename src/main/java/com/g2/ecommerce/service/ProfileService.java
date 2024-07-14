package com.g2.ecommerce.service;

import java.util.List;

import com.g2.ecommerce.dto.ProfileDto;
import com.g2.ecommerce.model.Profile;

public interface ProfileService {
	List<Profile> getAllProfiles();
	Profile getProfileById(int id);
	void editProfile(int id,ProfileDto profileDto);
}
