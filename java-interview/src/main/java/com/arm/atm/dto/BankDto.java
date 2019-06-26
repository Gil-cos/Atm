package com.arm.atm.dto;

import org.springframework.data.domain.Page;

import com.arm.atm.entity.Bank;

import lombok.Data;

@Data
public class BankDto {

	private String name;

	public BankDto(Bank bank) {
		this.name = bank.getName();
	}

	public static Page<BankDto> convert(Page<Bank> banks) {
		return banks.map(BankDto::new);
	}

}
