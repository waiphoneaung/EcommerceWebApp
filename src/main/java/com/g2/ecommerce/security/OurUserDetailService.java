package com.g2.ecommerce.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.model.User;
import com.g2.ecommerce.repository.UserRepository;

@Service
public class OurUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByName(name);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User not found.");
		}
		return new LoginUserDetail(user.get());
	}
}