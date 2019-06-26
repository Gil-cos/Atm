package com.arm.atm.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arm.atm.component.BankParser;
import com.arm.atm.dto.BankDto;
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

	@GetMapping(value = "/{page}/{size}")
	public Page<BankDto> listBanks(@PathVariable Integer page, @PathVariable Integer size) {
		
		Pageable pageable = PageRequest.of(page, size);

		Page<Bank> banks = bankService.findAll(pageable);

		return BankDto.convert(banks);
	}

}
