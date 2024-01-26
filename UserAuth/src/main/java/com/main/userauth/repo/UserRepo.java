package com.main.userauth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.userauth.entity.User;


public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findByUsername(String username);

}
