package com.arm.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arm.atm.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByNumberAndPassword(Long number, String password);

	@Query("FROM Account a WHERE a.number = :number AND a.password = :password AND a.bank.name = :bankName ")
	Account findByBankNameAndAccount(@Param("bankName") String bankName, @Param("number") Long number, @Param("password") String password);

	@Query("FROM Account a WHERE a.number = :number AND a.bank.id = :bankId")
	Account findByBankAndNumber(@Param("bankId") Long bankId, @Param("number") Long number);

	Account findByOwnerUserName(String name);
}
