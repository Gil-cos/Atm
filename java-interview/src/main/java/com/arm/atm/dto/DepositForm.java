package com.arm.atm.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DepositForm {

	private String bankName;
	private Long accountNumber;
	private String password;
	private BigDecimal value;

}
