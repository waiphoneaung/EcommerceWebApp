package com.g2.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.dto.ProfileDto;
import com.g2.ecommerce.model.Profile;
import com.g2.ecommerce.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public List<Profile> getAllProfiles(){
		return profileRepository.findAll();
	}
	
	@Override
	public Profile getProfileById(int id) {
		return profileRepository.findById(id).orElse(null);
	}
	
	@Override
	public void editProfile(int id,ProfileDto profileDto) {
		Profile profile = getProfileById(id);
		
		if (profile != null) {
			profile.setEmail(profileDto.getEmail());
			profile.setAddress(profileDto.getAddress());
			profile.setPhone(profileDto.getPhone_number());
		}
		profileRepository.save(profile);
	}
}
