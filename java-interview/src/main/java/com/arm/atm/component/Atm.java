package com.arm.atm.component;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.arm.atm.entity.Account;
import com.arm.atm.service.AccountService;

@Component
public class Atm {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ApplicationContext сontext;

	public Atm authenticate(String bankName, Long accountNumber, String password) {

		Account account = findAccount(bankName, accountNumber, password)
				.orElseThrow(() -> new RuntimeException("Usuário ou conta inválidos"));

		return сontext.getBean(Atm.class, account);
	}

	private Optional<Account> findAccount(String bankName, Long accountNumber, String password) {
		Account account = accountService.findByBankNameAndAccount(bankName, accountNumber, password);
		return Optional.ofNullable(account);
	}
}
