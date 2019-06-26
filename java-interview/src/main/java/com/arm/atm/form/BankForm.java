package com.arm.atm.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.arm.atm.entity.Bank;

import lombok.Data;

@Data
public class BankForm {
	
	@NotNull @NotEmpty
	private String name;

	public Bank convert() {
		return new Bank(null, this.name);
	}
	
	

}
