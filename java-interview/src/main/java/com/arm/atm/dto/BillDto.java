package com.arm.atm.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillDto {
	
	private Integer value;
	private Integer amount;
	
	
	public static List<BillDto> numberOfBills(BigDecimal value) {

		Integer valueInt = value.intValue();
		List<BillDto> bills = new ArrayList<BillDto>();

		int[] notas = { 0, 0, 0, 0 };
		int[] values = { 100, 50, 20, 10 };

		while (valueInt >= 10) {
			if (valueInt >= 100) {
				valueInt -= 100;
				notas[0] += 1;
			} else if (valueInt >= 50) {
				valueInt -= 50;
				notas[1] += 1;
			} else if (valueInt >= 20) {
				valueInt -= 20;
				notas[2] += 1;
			} else if (valueInt >= 10) {
				valueInt -= 10;
				notas[3] += 1;
			}
		}

		for (int i = 0; i < notas.length; i++) {
			if (notas[i] != 0) {
				bills.add(new BillDto(values[i], notas[i]));
			}
		}

		return bills;
	}

}
