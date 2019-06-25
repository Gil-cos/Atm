package com.arm.atm.validator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.arm.atm.dto.AccountForm;
import com.arm.atm.entity.Bank;
import com.arm.atm.service.BankService;

@RunWith(MockitoJUnitRunner.class)
public class BankValidatorTest {

	@InjectMocks
	BankValidator bankValidator;

	@Mock
	BankService bankService;

	@Test
	public void canValidateBank() {

		when(bankService.findById(1l)).thenReturn(new Bank(1l, "Bradesco"));

		AccountForm accountForm = new AccountForm(1l, "Carlos", 11l, "123");
		Bank bank = new Bank(1l, "Bradesco");
		
		assertEquals(bank, bankValidator.validateBank(accountForm));
	}
	
	@Test(expected = RuntimeException.class)
	public void cantValidateBank() {

		when(bankService.findById(1l)).thenReturn(null);

		AccountForm accountForm = new AccountForm(1l, "Carlos", 11l, "123");
		
		bankValidator.validateBank(accountForm);
	}
}
