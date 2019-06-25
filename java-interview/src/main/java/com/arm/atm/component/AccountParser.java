package com.arm.atm.component;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.arm.atm.dto.AccountForm;
import com.arm.atm.entity.Account;
import com.arm.atm.entity.Bank;
import com.arm.atm.entity.User;

@Component
public class AccountParser {

	public Account parse(AccountForm accountForm, Bank bank, User user) {

		return Account.builder().number(accountForm.getAccountNumber())
								.owner(user)
								.balance(new BigDecimal("0.0"))
								.password(accountForm.getPassword())
								.bank(bank)
								.build();
	}

}
