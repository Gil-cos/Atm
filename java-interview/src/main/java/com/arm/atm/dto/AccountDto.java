package com.arm.atm.dto;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.arm.atm.entity.Account;

import lombok.Data;

@Data
public class AccountDto {

	private Long number;

	private String owner;

	private BigDecimal balance;

	private String bank;

	public AccountDto(Account account) {
		this.number = account.getNumber();
		this.owner = account.getOwner().getUsername();
		this.balance = account.getBalance();
		this.bank = account.getBank().getName();
	}

	public static Page<AccountDto> convert(Page<Account> accounts) {
		return accounts.map(AccountDto::new);
	}

}
