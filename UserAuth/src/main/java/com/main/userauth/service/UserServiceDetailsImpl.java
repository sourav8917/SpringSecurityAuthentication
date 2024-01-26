package com.main.userauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.main.userauth.repo.UserRepo;

@Service
public class UserServiceDetailsImpl implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Username====="+username);
		com.main.userauth.entity.User user = repo.findByUsername(username);
		System.out.println("user:::"+user.getUsername() +"/n password::"+user.getPassword());

		if (user.getUsername() == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return User
				.withUsername(username)
				.password(user.getPassword())
				.build();
	}

}
