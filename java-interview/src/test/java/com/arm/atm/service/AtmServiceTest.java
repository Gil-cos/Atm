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

import com.arm.atm.dto.BillDto;
import com.arm.atm.entity.Account;

@RunWith(MockitoJUnitRunner.class)
public class AtmServiceTest {

	@Mock
	AccountService accountService;
	
	@InjectMocks
	AtmService atmService;
	
	@Test
	public void canDeposit() {

		when(accountService.findById(1l).get()).thenReturn(new Account(1l, 11l, null, new BigDecimal(500), "123", null));
		Account account = accountService.findById(1L).get();

		System.out.println(account);
		
		atmService.deposit(new BigDecimal(500), account);
		
		assertEquals(new BigDecimal(1000), account.getBalance());
	}
	
	@Test
	public void canWithdraw() {

		when(accountService.findById(1l).get()).thenReturn(new Account(1l, 11l, null, new BigDecimal(500), "123", null));
		Account account = accountService.findById(1L).get();

		atmService.withdraw(new BigDecimal(500), account);
		
		assertEquals(new BigDecimal(0), account.getBalance());
	}
	
	@Test(expected = RuntimeException.class)
	public void cantWithdraw() {

		when(accountService.findById(1l).get()).thenReturn(new Account(1l, 11l, null, new BigDecimal(500), "123", null));
		Account account = accountService.findById(1L).get();

		atmService.withdraw(new BigDecimal(600), account);
		
		assertEquals(new BigDecimal(500), account.getBalance());
	}

	@Test
	public void canCountNumberOfBills() {

		List<BillDto> billsExpected = new ArrayList<BillDto>();
		List<BillDto> bills = new ArrayList<BillDto>();
		
		billsExpected.add(new BillDto(100, 5));
		billsExpected.add(new BillDto(50, 1));
		billsExpected.add(new BillDto(20, 1));
		billsExpected.add(new BillDto(10, 1));
		
		bills = atmService.numberOfBills(new BigDecimal(580));
		
		assertEquals(billsExpected, bills);
	}
	
}
