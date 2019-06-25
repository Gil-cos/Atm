package com.arm.atm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arm.atm.entity.User;
import com.arm.atm.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User findById(Long userId) {
		return userRepository.findById(userId).get();
	}

	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
}
