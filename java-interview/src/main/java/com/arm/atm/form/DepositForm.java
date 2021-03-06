package com.arm.atm.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.arm.atm.entity.Account;
import com.arm.atm.service.AccountService;

import lombok.Data;

@Data
public class DepositForm {

	@NotNull
	@NotEmpty
	private String bankName;

	@NotNull
	private Long accountNumber;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	private BigDecimal value;

	public void deposit(AccountService accountService) {

		Account account = accountService.findByBankNameAndAccount(this.bankName, this.accountNumber, this.password);

		account.setBalance(account.getBalance().add(this.value));
		accountService.save(account);

	}

}
