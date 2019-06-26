package com.arm.atm.validator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.arm.atm.entity.Account;
import com.arm.atm.form.AccountForm;
import com.arm.atm.service.AccountService;
import com.arm.atm.validator.AccountValidator;

@RunWith(MockitoJUnitRunner.class)
public class AccountValidatorTest {

	@InjectMocks
	AccountValidator accountValidator;
	
	@Mock
	AccountService accountService;

	@Test
	public void canValidateAccount() {

		when(accountService.findByBankAndNumber(1l, 11l))
				.thenReturn(new Account(1l, 11l, null, new BigDecimal(0), "123", null));

		AccountForm accountForm = new AccountForm(1l, "Carlos", 11l, "123");

		assertEquals(false, accountValidator.validateAccount(accountForm));

	}
	
	@Test
	public void cantValidateAccount() {

		when(accountService.findByBankAndNumber(4l, 11l))
				.thenReturn(null);

		AccountForm accountForm = new AccountForm(4l, "Carlos", 11l, "123");

		assertEquals(true, accountValidator.validateAccount(accountForm));

	}

}
