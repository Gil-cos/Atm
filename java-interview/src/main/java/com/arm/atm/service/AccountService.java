package com.arm.atm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arm.atm.entity.Account;
import com.arm.atm.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account findByNumberAndPassword(Long number, String password) {
		return accountRepository.findByNumberAndPassword(number, password);
	}

	public Account findById(Long accountId) {
		return accountRepository.findById(accountId).get();
	}

	public Account save(Account newAccount) {
		return accountRepository.save(newAccount);
	}

	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	public Account findByBankNameAndAccount(String bankName, Long Accountnumber, String Accountpassword) {
		return accountRepository.findByBankNameAndAccount(bankName, Accountnumber, Accountpassword);
	}

	public Account findByBankAndNumber(Long bankId, Long number) {
		return accountRepository.findByBankAndNumber(bankId, number);
	}

	public Account findByOwnerUserName(String name) {
		return accountRepository.findByOwnerUserName(name);
	}

}
