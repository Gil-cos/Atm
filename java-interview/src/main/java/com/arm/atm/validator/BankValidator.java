package com.arm.atm.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arm.atm.entity.Bank;
import com.arm.atm.form.AccountForm;
import com.arm.atm.service.BankService;

@Component
public class BankValidator {
	
	@Autowired
	private BankService bankService;

	public Bank validateBank(AccountForm accountForm) {
		Bank bank = bankService.findById(accountForm.getBankId()).get();

		return Optional.ofNullable(bank).orElseThrow(() -> new RuntimeException("Bank does not exist."));
	}
}
