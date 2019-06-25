package com.arm.atm.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arm.atm.component.AccountParser;
import com.arm.atm.dto.AccountForm;
import com.arm.atm.entity.Account;
import com.arm.atm.entity.Bank;
import com.arm.atm.entity.User;
import com.arm.atm.service.AccountService;
import com.arm.atm.service.BankService;
import com.arm.atm.service.UserService;
import com.arm.atm.validator.AccountValidator;

@RestController
@RequestMapping("api/account")
public class AccountController {

	@Autowired
	public AccountService accountService;

	@Autowired
	private BankService bankService;

	@Autowired
	private UserService userService;

	@Autowired
	private AccountParser accountParser;
	
	@Autowired
	private AccountValidator accountValidator;

	@GetMapping()
	public ResponseEntity<List<Account>> listAccounts() {

		List<Account> accountDb = accountService.findAll();

		return new ResponseEntity<List<Account>>(accountDb, OK);
	}
	
	@GetMapping(value = "/{name}")
	public ResponseEntity<Account> getAccount(@PathVariable String name) {

		Account accountDb = accountService.findByOwnerUserName(name);

		return new ResponseEntity<Account>(accountDb, OK);
	}

	@PostMapping(value = "/{userId}")
	public ResponseEntity<Account> createAccount(@RequestBody AccountForm accountForm, @PathVariable Long userId) {

		System.out.println(accountForm);
		if (accountValidator.validateAccount(accountForm)) {
			
			Bank bank = bankService.validateBank(accountForm);
			User user = userService.findById(userId);
			
			Account newAccount = accountParser.parse(accountForm, bank, user);
			Account accountDb = accountService.save(newAccount);

			return new ResponseEntity<Account>(accountDb, OK);
			
		} else {
			
			return ResponseEntity.badRequest().body(null);
		}

	}
}
