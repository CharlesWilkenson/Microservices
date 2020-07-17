package org.OperationService.services;

import java.util.List;

import org.OperationService.entities.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepositRepository extends JpaRepository<Deposit, Long>{
	
	@Query("SELECT d FROM Deposit d WHERE d.account.accountNumber=:x")
	public List<Deposit>AllDepositByAccountNumber(@Param("x") String accountNumber);

}
