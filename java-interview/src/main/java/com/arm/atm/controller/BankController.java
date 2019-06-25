package com.arm.atm.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arm.atm.component.BankParser;
import com.arm.atm.entity.Bank;
import com.arm.atm.service.BankService;

@RestController
@RequestMapping("api/bank")
public class BankController {

	@Autowired
	private BankService bankService;

	@Autowired
	private BankParser bankParser;

	@PostMapping()
	public ResponseEntity<Bank> createBank(@RequestBody Bank bankForm) {

		Bank bank = bankParser.parse(bankForm);
		Bank bankDb = bankService.save(bank);

		return new ResponseEntity<Bank>(bankDb, OK);
	}

	@GetMapping()
	public ResponseEntity<List<Bank>> listBanks() {

		List<Bank> bankDb = bankService.findAll();

		return new ResponseEntity<List<Bank>>(bankDb, OK);
	}

}
