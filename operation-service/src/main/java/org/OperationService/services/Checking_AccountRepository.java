package org.OperationService.services;


import org.OperationService.entities.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Checking_AccountRepository  extends JpaRepository<CheckingAccount, String> {

}
