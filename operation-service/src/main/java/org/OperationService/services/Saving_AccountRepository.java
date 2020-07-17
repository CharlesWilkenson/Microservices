package org.OperationService.services;


import org.OperationService.entities.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Saving_AccountRepository extends JpaRepository<SavingsAccount, String> {

}
