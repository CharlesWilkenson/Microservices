package org.OperationService.services;

import java.util.List;

import org.OperationService.entities.Transfert;
import org.OperationService.entities.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long>{
	@Query("SELECT  d FROM Withdrawal d WHERE d.account.accountNumber=:x")
	public List<Withdrawal>AllWithdrawalByAccountNumber(@Param("x") String accountNumber);
}
