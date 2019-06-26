package com.arm.atm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arm.atm.dto.BillDto;
import com.arm.atm.entity.Account;

@Service
public class AtmService {
	
	@Autowired
	private AccountService accountService;

	public void deposit(BigDecimal value, Account account) {

		try {
			account.setBalance(account.getBalance().add(value));
			accountService.save(account);

		} catch (Exception e) {
			throw e;
		}
	}

	public void withdraw(BigDecimal value, Account account) {

		if (account.getBalance().intValue() >= value.intValue()) {
			account.setBalance(account.getBalance().subtract(value));
			accountService.save(account);
		} else {
			throw new RuntimeException("Saldo do usuario abaixo do valor de saque");
		}
	}

	public List<BillDto> numberOfBills(BigDecimal value) {

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
			bills.add(new BillDto(values[i], notas[i]));
		}

		return bills;
	}
}
