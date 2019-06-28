package com.arm.atm.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.arm.atm.entity.Account;
import com.arm.atm.service.AccountService;

import lombok.Data;

@Data
public class BalanceForm {

	@NotNull
	@NotEmpty
	private String bankName;

	@NotNull
	private Long accountNumber;

	@NotNull
	@NotEmpty
	private String password;

	public String balance(AccountService accountService) {

		Account account = accountService.findByBankNameAndAccount(this.bankName, this.accountNumber, this.password);
		return account.getBalance().toString();

	}

}
