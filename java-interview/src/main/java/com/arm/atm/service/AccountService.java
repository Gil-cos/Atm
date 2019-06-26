package com.arm.atm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public Optional<Account> findById(Long accountId) {
		return accountRepository.findById(accountId);
	}

	public Account save(Account newAccount) {
		return accountRepository.save(newAccount);
	}

	public Page<Account> findAll(Pageable pageable) {
		return accountRepository.findAll(pageable);
	}

	public Account findByBankNameAndAccount(String bankName, Long Accountnumber, String Accountpassword) {
		return accountRepository.findByBankNameAndAccount(bankName, Accountnumber, Accountpassword);
	}

	public Account findByBankAndNumber(Long bankId, Long number) {
		return accountRepository.findByBankAndNumber(bankId, number);
	}

	public Optional<Account> findByOwnerUserName(String name) {
		return accountRepository.findByOwnerUserName(name);
	}

	public Optional<Account> findByBankAndOwnerUserName(Long bankId, String ownerName) {
		return accountRepository.findByBankAndOwnerUserName(bankId, ownerName);
	}

	public void deleteById(Long id) {
		accountRepository.deleteById(id);
	}

}
