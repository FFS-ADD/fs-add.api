package com.accenture.fsadd.user.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.user.entity.User;
import com.accenture.fsadd.user.repository.UserRepository;
import com.accenture.fsadd.user.service.UserService;
import com.accenture.fsadd.web.author.form.WebDashboardLoginForm;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAllValidUsers() {
		return userRepository.findAll();
	}

	@Override
	public User registerUser(User user) {
		return userRepository.insert(user);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.getUserByEmail(username);
		if (user == null) {
			return null;
		} else {
			return new UserWrapper(user);
		}

	}
	
	@Override
	public boolean userLogin(WebDashboardLoginForm form) {
		User user = userRepository.getUserByNameAndPassword(form.getUsername(), form.getPassword());
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	public static class UserWrapper implements UserDetails {

		private User user;

		public UserWrapper(User user) {
			this.user = user;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			GrantedAuthority grantedAuthority = () -> {
				return user.getRole();
			};
			return Arrays.asList(grantedAuthority);
		}

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return user.getPassword();
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return user.getEmail();
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}

	}

}
