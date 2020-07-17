package org.OperationService.services;

import java.util.Date;
import java.util.List;

import org.OperationService.entities.Account;
import org.OperationService.entities.CheckingAccount;
import org.OperationService.entities.Deposit;
import org.OperationService.entities.Operation;
import org.OperationService.entities.SavingsAccount;
import org.OperationService.entities.Transfert;
import org.OperationService.entities.Withdrawal;

public interface Services {

	public void addAccount(Account  account);	
	//public void operation(Operation operation);
	public void Withdrawal(String  accountNumber, double amount,Date date);
	public void deposit(String  accountNumber, double amount,Date date);
	public void transfert(String  accountNumber,String  beneficiaryNumber, double amount,Date date);
	
	public List<Deposit> allDeposits();
	public List<Withdrawal>allWithdrals();
	public List<Transfert>allTransferts();
	
	public List<Deposit> allDeposits(String accountNumber);
	public List<Withdrawal>allWithdrals(String accountNumber);
	public List<Transfert>allTransferts(String accountNumber);
	
		
	public SavingsAccount findASaving(String number);
	public CheckingAccount findCheking(String number);
	public Account  findAccount(String number);


	public List<Account>allAccounts();
	public List<SavingsAccount>allSavings();
	public List<CheckingAccount>allCheckings();
	public void desactiverAccount(String account);
	public void reactiverAccount(String account);
}
