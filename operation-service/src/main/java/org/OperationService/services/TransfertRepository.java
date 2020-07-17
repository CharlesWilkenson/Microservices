package org.OperationService.services;

import java.util.List;


import org.OperationService.entities.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransfertRepository extends JpaRepository<Transfert, Long>{
	@Query("SELECT  d FROM Transfert d WHERE d.account.accountNumber=:x")
	public List<Transfert>AllTransfertByAccountNumber(@Param("x") String accountNumber);
}
