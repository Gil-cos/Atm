package com.arm.atm.component;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.arm.atm.entity.Bank;

public class BankParserTest {

	@Test
	public void canBuildBank() {
		
		BankParser bankParser = new BankParser();
		Bank bank = new Bank(null, "Santander");
		Bank bankParsed;
		
		bankParsed = bankParser.parse(bank);
	
		assertEquals(bank, bankParsed);
	
	}
	
	@Test(expected = AssertionError.class)
	public void cantBuildBank() {
		
		BankParser bankParser = new BankParser();
		Bank bank = new Bank(2L, "Santander");
		Bank bankParsed;
		
		bankParsed = bankParser.parse(bank);
		
		assertEquals(bank, bankParsed);
	
	}
}
