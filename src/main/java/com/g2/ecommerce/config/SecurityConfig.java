package com.g2.ecommerce.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.g2.ecommerce.security.OurUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final OurUserDetailService userDetailService;
	
	public SecurityConfig(OurUserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.cors(Customizer.withDefaults())
			.httpBasic(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/images/**","/assets/**","/css/**","/js/**","/scss/**","/fonts/**").permitAll()
					.requestMatchers("/adminDashboard/**").hasAnyAuthority("ADMIN")
					.requestMatchers("/customer/**").hasAnyAuthority("USER")
					.anyRequest().authenticated()
			)
			.exceptionHandling(
					(exceptionHandling) -> exceptionHandling
						.accessDeniedPage("/accessDenied")
			)
			.formLogin(form -> form
							.loginPage("/auth/login")
							.loginProcessingUrl("/auth/login")
							.successHandler(customAuthenticationSuccessHandler())
							.failureHandler(authenticationFailureHandler())
							.permitAll()
			)
			.logout(
					logout -> logout
							.logoutUrl("/logout")
							.logoutSuccessUrl("/auth/login")
							.logoutSuccessHandler(
									((request, response, authentication) -> {
										response.sendRedirect("/auth/login");
									})
							)							
							.invalidateHttpSession(true)
							.permitAll()
			);
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailService() {
		return new OurUserDetailService();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return (request, response, exception)-> {
			String errorMessage = "Incorrect Email or Password.";
			request.getSession().setAttribute("errorMessage", errorMessage);
			response.sendRedirect("/auth/login?error=true");
		};
	}
	
	@Bean
	public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		return new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
												Authentication authentication)
														throws IOException, ServletException {
				Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
				if (authorities.contains("ADMIN")) {
					response.sendRedirect("/adminDashboard");
				}
				else if (authorities.contains("USER")) {
					response.sendRedirect("/customer");
				}
				else {
					response.sendRedirect("/auth/login");
				}
			}
		};
	}
}
