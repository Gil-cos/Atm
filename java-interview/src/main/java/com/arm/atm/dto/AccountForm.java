package com.arm.atm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountForm {

	private Long bankId;
	private String ownerName;
	private Long accountNumber;
	private String password;
}
