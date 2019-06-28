package com.arm.atm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arm.atm.component.Atm;
import com.arm.atm.dto.BillDto;
import com.arm.atm.dto.TransactionDto;
import com.arm.atm.dto.WithdrawDto;
import com.arm.atm.form.BalanceForm;
import com.arm.atm.form.DepositForm;
import com.arm.atm.form.WithdrawForm;
import com.arm.atm.service.AccountService;

@RestController
@RequestMapping("api/atm")
public class AtmController {

	@Autowired
	private Atm atm;

	@Autowired
	private AccountService accountService;
	

	@PostMapping(value = "/deposit")
	public ResponseEntity<TransactionDto> deposit(@RequestBody DepositForm depositForm) {

		TransactionDto transactionDto = new TransactionDto();

		try {

			atm.authenticate(depositForm.getBankName(), depositForm.getAccountNumber(), depositForm.getPassword());
			depositForm.deposit(accountService);
			transactionDto.setMessage("Deposit successfull");

			return ResponseEntity.ok(transactionDto);

		} catch (Exception e) {

			transactionDto.	setMessage(e.getMessage());
			return ResponseEntity.badRequest().body(transactionDto);
		}
	}

	@PostMapping(value = "/withdraw")
	public ResponseEntity<WithdrawDto> withdraw(@RequestBody WithdrawForm withdrawForm) {

		WithdrawDto withdrawDto = new WithdrawDto();

		try {
			atm.authenticate(withdrawForm.getBankName(), withdrawForm.getAccountNumber(), withdrawForm.getPassword());
			
			List<BillDto> bills = BillDto.numberOfBills(withdrawForm.getValue());

			withdrawForm.withdraw(accountService);
			withdrawDto.setBills(bills);
			withdrawDto.setMessage("Withdraw Sucessfull");
			
			return ResponseEntity.ok().body(withdrawDto);

		} catch (Exception e) {
			
			withdrawDto.setMessage(e.getMessage());
			return ResponseEntity.badRequest().body(withdrawDto);
		}
	}

	@PostMapping(value = "/balance")
	public ResponseEntity<TransactionDto> balance(@RequestBody BalanceForm balanceForm) {

		TransactionDto transactionDto = new TransactionDto();

		try {

			atm.authenticate(balanceForm.getBankName(), balanceForm.getAccountNumber(), balanceForm.getPassword());
			transactionDto.setMessage("Balance: " + balanceForm.balance(accountService));

			return ResponseEntity.ok(transactionDto);

		} catch (Exception e) {

			transactionDto.setMessage(e.getMessage());
			return ResponseEntity.badRequest().body(transactionDto);
		}
	}

}
