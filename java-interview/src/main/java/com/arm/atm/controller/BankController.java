package com.arm.atm.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.arm.atm.dto.BankDto;
import com.arm.atm.entity.Bank;
import com.arm.atm.form.BankForm;
import com.arm.atm.service.BankService;

@RestController
@RequestMapping("api/bank")
public class BankController {

	@Autowired
	private BankService bankService;

	@GetMapping(value = "/{page}/{size}")
	public Page<BankDto> listBanks(@PathVariable Integer page, @PathVariable Integer size) {

		Pageable pageable = PageRequest.of(page, size);

		Page<Bank> banks = bankService.findAll(pageable);

		return BankDto.convert(banks);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BankDto> getBank(@PathVariable Long id) {

		Optional<Bank> bank = bankService.findById(id);
		if (bank.isPresent()) {
			return ResponseEntity.ok(new BankDto(bank.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping()
	public ResponseEntity<BankDto> createBank(@RequestBody @Valid BankForm bankForm, UriComponentsBuilder uriBuilder) {

		Bank bank = bankForm.convert();
		Bank newBank = bankService.save(bank);

		URI uri = uriBuilder.path("/api/bank/{id}").buildAndExpand(newBank.getId()).toUri();

		return ResponseEntity.created(uri).body(new BankDto(newBank));
	}

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<BankDto> updateBank(@RequestBody @Valid BankForm bankForm, @PathVariable Long id) {

		Optional<Bank> optional =  bankService.findById(id);
		if (optional.isPresent()) {
			Bank bank = bankForm.update(id, bankService);
			return ResponseEntity.ok(new BankDto(bank));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteBank(@PathVariable Long id) {
		
		Optional<Bank> bank = bankService.findById(id);
		if (bank.isPresent()) {
			bankService.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
