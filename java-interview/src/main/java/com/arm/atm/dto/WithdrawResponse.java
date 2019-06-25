package com.arm.atm.dto;

import java.util.List;

import lombok.Data;

@Data
public class WithdrawResponse {

	private List<Bill> data;
	private String message;

}
