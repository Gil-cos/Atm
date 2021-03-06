package com.arm.atm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public Optional<User> findById(Long userId) {
		return userRepository.findById(userId);
	}

	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public Page<User> findByProfilesName(Pageable pageable, String name) {
		return userRepository.findByProfilesName(pageable, name);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
}
