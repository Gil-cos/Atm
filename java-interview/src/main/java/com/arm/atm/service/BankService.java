package com.arm.atm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arm.atm.dto.AccountForm;
import com.arm.atm.entity.Bank;
import com.arm.atm.repository.BankRepository;

@Service
public class BankService {

	@Autowired
	private BankRepository bankRepository;
	
	public Bank findById(Long bankId) {
		return bankRepository.findById(bankId).get();
	}
	
	public Bank findByName(String name) {
		return bankRepository.findByName(name);
	}
	
	public Bank save(Bank newBank) {
		return bankRepository.save(newBank);
	}
	
	public List<Bank> findAll(){
		return bankRepository.findAll();
	}
	
	public Bank validateBank(AccountForm accountForm) {
		Bank bank = findById(accountForm.getBankId());

		return Optional.ofNullable(bank).orElseThrow(() -> new RuntimeException("Bank does not exist."));
	}
	
}
