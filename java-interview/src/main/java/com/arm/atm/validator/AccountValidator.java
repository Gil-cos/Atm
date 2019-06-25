package com.arm.atm.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arm.atm.dto.AccountForm;
import com.arm.atm.entity.Account;
import com.arm.atm.service.AccountService;

@Component
public class AccountValidator {

	@Autowired
	private AccountService accountService;

	public boolean validateAccount(AccountForm accountForm) {
		Account existingAccount = accountService.findByBankAndNumber(accountForm.getBankId(),
				accountForm.getAccountNumber());

		if (existingAccount != null) {
			return false;
		} else {
			return true;
		}
	}
}
