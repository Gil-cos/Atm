package com.arm.atm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arm.atm.entity.Bank;
import com.arm.atm.repository.BankRepository;

@Service
public class BankService {

	@Autowired
	private BankRepository bankRepository;
	
	public Optional<Bank> findById(Long bankId) {
		return bankRepository.findById(bankId);
	}
	
	public Bank findByName(String name) {
		return bankRepository.findByName(name);
	}
	
	public Bank save(Bank newBank) {
		return bankRepository.save(newBank);
	}
	
	public Page<Bank> findAll(Pageable pageable){
		return bankRepository.findAll(pageable);
	}
	
}
