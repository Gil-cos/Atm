package com.arm.atm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Bill {
	
	private Integer value;
	private Integer amount;

}
