package com.arm.atm.component;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.arm.atm.Form.AccountForm;
import com.arm.atm.entity.Account;
import com.arm.atm.entity.Bank;
import com.arm.atm.entity.User;

public class AccountParserTest {
	
	@Test
	public void canBuildAccount() {
		
		AccountForm accountForm = new AccountForm(1L, "Maria", 13L, "123");
		User user = new User(3L, "Maria", "123", null);
		Bank bank = new Bank(1L, "Itau");
		
		Account accountExpected = new Account(null, 13L, user, new BigDecimal("0.0"), "123", bank);
		Account accountParsed;
		
		AccountParser accountParser =  new AccountParser();
		accountParsed = accountParser.parse(accountForm, bank, user);
		
		assertEquals(accountExpected, accountParsed);
		
	}
	
	@Test(expected = AssertionError.class)
	public void cantBuildAccount() {
		
		AccountForm accountForm = new AccountForm(1L, "Maria", 13L, "123");
		User user = new User(3L, "Maria", "123", null);
		Bank bank = new Bank(1L, "Itau");
		
		Account accountExpected = new Account(null, 1L, user, new BigDecimal("0.0"), "123", bank);
		Account accountParsed;
		
		AccountParser accountParser =  new AccountParser();
		accountParsed = accountParser.parse(accountForm, bank, user);
		
		assertEquals(accountExpected, accountParsed);
		
	}

}
