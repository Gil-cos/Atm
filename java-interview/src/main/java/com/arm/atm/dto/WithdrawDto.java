package com.arm.atm.dto;

import java.util.List;

import lombok.Data;

@Data
public class WithdrawDto {

	private List<BillDto> bills;
	private String message;

}
