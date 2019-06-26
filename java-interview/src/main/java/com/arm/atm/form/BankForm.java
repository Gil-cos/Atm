package com.arm.atm.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.arm.atm.entity.Bank;
import com.arm.atm.service.BankService;

import lombok.Data;

@Data
public class BankForm {
	
	@NotNull @NotEmpty
	private String name;

	public Bank convert() {
		return new Bank(null, this.name);
	}

	public Bank update(Long id, BankService bankService) {
		Bank bank = bankService.findById(id).get();
		bank.setName(this.name);

		return bank;
	}
	
	

}
