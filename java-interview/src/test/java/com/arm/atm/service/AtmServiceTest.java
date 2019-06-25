package com.arm.atm.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.arm.atm.dto.Bill;
import com.arm.atm.entity.Account;

@RunWith(MockitoJUnitRunner.class)
public class AtmServiceTest {

	@Mock
	AccountService accountService;
	
	@InjectMocks
	AtmService atmService;
	
	@Test
	public void canDeposit() {

		when(accountService.findById(1l)).thenReturn(new Account(1l, 11l, null, new BigDecimal(500), "123", null));
		Account account = accountService.findById(1L);

		atmService.deposit(new BigDecimal(500), account);
		
		assertEquals(new BigDecimal(1000), account.getBalance());
	}
	
	@Test
	public void canWithdraw() {

		when(accountService.findById(1l)).thenReturn(new Account(1l, 11l, null, new BigDecimal(500), "123", null));
		Account account = accountService.findById(1L);

		atmService.withdraw(new BigDecimal(500), account);
		
		assertEquals(new BigDecimal(0), account.getBalance());
	}
	
	@Test(expected = RuntimeException.class)
	public void cantWithdraw() {

		when(accountService.findById(1l)).thenReturn(new Account(1l, 11l, null, new BigDecimal(500), "123", null));
		Account account = accountService.findById(1L);

		atmService.withdraw(new BigDecimal(600), account);
		
		assertEquals(new BigDecimal(500), account.getBalance());
	}

	@Test
	public void canCountNumberOfBills() {

		List<Bill> billsExpected = new ArrayList<Bill>();
		List<Bill> bills = new ArrayList<Bill>();
		
		billsExpected.add(new Bill(100, 5));
		billsExpected.add(new Bill(50, 1));
		billsExpected.add(new Bill(20, 2));
		billsExpected.add(new Bill(10, 0));
		
		bills = atmService.numberOfBills(new BigDecimal(590));
		
		assertEquals(billsExpected, bills);
	}
	
	@Test
	public void canCountNumberOfBillsIn10() {

		List<Bill> billsExpected = new ArrayList<Bill>();
		List<Bill> bills = new ArrayList<Bill>();
		
		billsExpected.add(new Bill(100, 0));
		billsExpected.add(new Bill(50, 0));
		billsExpected.add(new Bill(20, 0));
		billsExpected.add(new Bill(10, 1));
		
		bills = atmService.numberOfBills(new BigDecimal(10));
		
		assertEquals(billsExpected, bills);
	}
}
