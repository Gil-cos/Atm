package com.arm.atm.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arm.atm.Form.DepositForm;
import com.arm.atm.component.Atm;
import com.arm.atm.dto.BillDto;
import com.arm.atm.dto.WithdrawResponse;
import com.arm.atm.entity.Account;
import com.arm.atm.service.AccountService;
import com.arm.atm.service.AtmService;

@RestController
@RequestMapping("api/atm")
public class AtmController {

	@Autowired
	private Atm atm;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AtmService atmService;

	@PostMapping(value = "/deposit")
	public ResponseEntity<String> deposit(@RequestBody DepositForm depositForm) {

		try {
			atm.authenticate(depositForm.getBankName(), depositForm.getAccountNumber(),
					depositForm.getPassword());

			Account account = accountService.findByBankNameAndAccount(depositForm.getBankName(),
					depositForm.getAccountNumber(), depositForm.getPassword());

			atmService.deposit(depositForm.getValue(), account);

		} catch (Exception e) {

			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return new ResponseEntity<String>("Deposit successfull", OK);
	}

	@PostMapping(value = "/withdraw")
	public ResponseEntity<WithdrawResponse> withdraw(@RequestBody DepositForm depositForm) {

		List<BillDto> bills;
		WithdrawResponse response = new WithdrawResponse();
		
		if (depositForm.getValue().intValue() % 10 == 0) {
			
			try {
				atm.authenticate(depositForm.getBankName(), depositForm.getAccountNumber(),
						depositForm.getPassword());
				
				Account account = accountService.findByBankNameAndAccount(depositForm.getBankName(),
						depositForm.getAccountNumber(), depositForm.getPassword());
				
				bills = atmService.numberOfBills(depositForm.getValue());
				
				atmService.withdraw(depositForm.getValue(), account);
				
				response.setMessage("Withdraw successfull");
				response.setData(bills);
				
			} catch (Exception e) {
				response.setMessage(e.getMessage());
				return ResponseEntity.badRequest().body(response);
			}
			
			return new ResponseEntity<WithdrawResponse>(response, OK);
			
		} else {
			response.setMessage("Invalid Value");
			return ResponseEntity.badRequest().body(response);
		}

	}

	@PostMapping(value = "/balance")
	public ResponseEntity<String> balance(@RequestBody DepositForm depositForm) {

		Account account;

		try {
			atm.authenticate(depositForm.getBankName(), depositForm.getAccountNumber(),
					depositForm.getPassword());

			account = accountService.findByBankNameAndAccount(depositForm.getBankName(), depositForm.getAccountNumber(),
					depositForm.getPassword());

		} catch (Exception e) {

			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return new ResponseEntity<String>("Balance: " + account.getBalance(), OK);
	}

	
}
