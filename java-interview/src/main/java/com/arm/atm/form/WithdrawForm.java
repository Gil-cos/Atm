package com.arm.atm.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.arm.atm.entity.Account;
import com.arm.atm.service.AccountService;

import lombok.Data;

@Data
public class WithdrawForm {

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

	public void withdraw(AccountService accountService) {

		Account account;
		account = accountService.findByBankNameAndAccount(this.bankName, this.accountNumber, this.password);

		if (account.getBalance().intValue() >= value.intValue() && this.value.intValue() % 10 == 0) {
			account.setBalance(account.getBalance().subtract(value));
			accountService.save(account);

		} else {
			throw new RuntimeException("Saldo do usuario abaixo do valor de saque");
		}
	}

}
