package com.arm.atm.Form;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.arm.atm.entity.Account;
import com.arm.atm.entity.Bank;
import com.arm.atm.entity.User;
import com.arm.atm.service.BankService;
import com.arm.atm.service.UserService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountForm {

	@NotNull
	private Long bankId;
	
	@NotNull
	private Long accountNumber;
	
	@NotNull @NotEmpty
	private String password;
	
	public Account converter(UserService userService, BankService bankService, Long userId) {

		Optional<Bank> bank = bankService.findById(this.bankId);
		Optional<User> user = userService.findById(userId);
		
		if (bank.isPresent() && user.isPresent()) {
			
			return new Account(null, accountNumber, user.get(), new BigDecimal(0.0), password, bank.get());
		}
		
		return null;
	}
}
