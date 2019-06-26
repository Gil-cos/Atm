package com.arm.atm.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.arm.atm.dto.AccountDto;
import com.arm.atm.dto.UserDto;
import com.arm.atm.entity.Account;
import com.arm.atm.entity.User;
import com.arm.atm.form.AccountForm;
import com.arm.atm.form.UserForm;
import com.arm.atm.service.AccountService;
import com.arm.atm.service.BankService;
import com.arm.atm.service.UserService;

@RestController
@RequestMapping("api/account")
public class AccountController {

	@Autowired
	public AccountService accountService;

	@Autowired
	private BankService bankService;

	@Autowired
	private UserService userService;

	@GetMapping(value = "/{page}/{size}")
	public Page<AccountDto> listAccounts(@PathVariable Integer page, @PathVariable Integer size) {

		Pageable pageable = PageRequest.of(page, size);

		Page<Account> accounts = accountService.findAll(pageable);

		return AccountDto.convert(accounts);
	}

	@GetMapping(value = "/{name}")
	public ResponseEntity<AccountDto> getAccount(@PathVariable String name) {

		Optional<Account> account = accountService.findByOwnerUserName(name);
		if (account.isPresent()) {
			return ResponseEntity.ok(new AccountDto(account.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping(value = "/{userId}")
	public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid AccountForm accountForm, @PathVariable Long userId,
			UriComponentsBuilder uriBuilder) {

		try {

			Account newAccount = accountForm.converter(userService, bankService, userId);
			accountService.save(newAccount);

			URI uri = uriBuilder.path("/api/account/{name}").buildAndExpand(newAccount.getOwner().getUsername())
					.toUri();

			return ResponseEntity.created(uri).body(new AccountDto(newAccount));
			

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@Transactional
	@PutMapping(value = "/{id}")
	public ResponseEntity<AccountDto> updateAccount(@RequestBody @Valid AccountForm accountForm, @PathVariable Long id) {

		Optional<Account> optional =  accountService.findById(id);
		if (optional.isPresent()) {
			Account account = accountForm.update(id, accountService);
			return ResponseEntity.ok(new AccountDto(account));
		}
		
		return ResponseEntity.notFound().build();
	}
}
