package com.arm.atm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FormErrorDto {
	
	private String field;
	private String error;

}
