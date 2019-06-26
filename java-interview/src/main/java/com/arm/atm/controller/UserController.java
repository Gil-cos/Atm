package com.arm.atm.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.arm.atm.Form.UserForm;
import com.arm.atm.dto.UserDto;
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
	public ResponseEntity<UserDto> signup(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder) {

		User newUser = userForm.convert(passwordEncoder);
		userService.save(newUser);

		URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(newUser.getId()).toUri();

		return ResponseEntity.created(uri).body(new UserDto(newUser));
	}

	@GetMapping(value = "/{page}/{size}")
	public Page<User> listUsers(@PathVariable Integer page, @PathVariable Integer size) {

		Pageable pageable = PageRequest.of(page, size);

		Page<User> user = userService.findAll(pageable);

		return user;
	}
}
