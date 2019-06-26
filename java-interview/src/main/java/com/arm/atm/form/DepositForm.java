package com.arm.atm.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DepositForm {

	@NotNull @NotEmpty
	private String bankName;
	
	@NotNull
	private Long accountNumber;
	
	@NotNull @NotEmpty
	private String password;
	
	@NotNull
	private BigDecimal value;

}
