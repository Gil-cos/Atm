package com.arm.atm.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arm.atm.entity.User;
import com.arm.atm.service.UserService;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping()
	public ResponseEntity<User> signup(HttpServletRequest request, @RequestBody User user, BindingResult result) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//user.setProfile(ProfileEnum.ROLE_CUSTOMER);

		User userPersisted = userService.save(user);

		return new ResponseEntity<User>(userPersisted, HttpStatus.OK);

	}
	
	@GetMapping()
	public ResponseEntity<List<User>> listUsers() {

		List<User> userDb = userService.findAll();

		return new ResponseEntity<List<User>>(userDb, OK);
	}
}
