package com.arm.atm.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arm.atm.dto.TokenDto;
import com.arm.atm.form.LoginForm;
import com.arm.atm.service.TokenService;

@RestController
public class AuthRestController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;


	@PostMapping(value = "/api/auth")
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
