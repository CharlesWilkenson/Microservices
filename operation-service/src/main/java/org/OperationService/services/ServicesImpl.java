package org.OperationService.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.OperationService.entities.Account;
import org.OperationService.entities.CheckingAccount;
import org.OperationService.entities.Deposit;
import org.OperationService.entities.SavingsAccount;
import org.OperationService.entities.Transfert;
import org.OperationService.entities.Withdrawal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServicesImpl implements Services{
@Autowired
private OperationRepository operationRepository;
@Autowired
private WithdrawalRepository withdrawalRepository;
@Autowired
private DepositRepository depositRepository;
@Autowired
private TransfertRepository transfertRepository;

@Autowired 
private AccountRepository accountRepository;
	
@Autowired
private Checking_AccountRepository checking_AccountRepository;

@Autowired
private Saving_AccountRepository saving_AccountRepository;

@Override
public void transfert(String  accountNumber,String  beneficiaryNumber, double amount,Date date) {
	if(accountNumber.equals(beneficiaryNumber))
		throw new RuntimeException("Impossible");
	
	//Make the withdrawal
	Account withdrawal=findAccount(accountNumber);
	withdrawal.setBalance(withdrawal.getBalance()-amount);
	accountRepository.save(withdrawal);
	
	//Make the deposit
	Account deposit=findAccount(beneficiaryNumber);
	deposit.setBalance(deposit.getBalance()+amount);
	accountRepository.save(deposit);
	
	
	
	//deposit(beneficiaryNumber, amount,new Date());
	operationRepository.save(new Transfert(amount, withdrawal , new Date(), beneficiaryNumber));
}

@Override
public void Withdrawal(String  accountNumber, double amount,Date date) {
	Account ac=findAccount(accountNumber);
	if(ac==null)
	throw new RuntimeException("Invalid account number");
	ac.setBalance(ac.getBalance()-amount);
	accountRepository.save(ac);
	operationRepository.save(new Withdrawal(amount,  ac, new Date()));
	
}

@Override
public void deposit(String  accountNumber, double amount,Date date) {
	Account ac=findAccount(accountNumber);
	if(ac==null)
		throw new RuntimeException("Invalid account number");
	ac.setBalance(ac.getBalance()+amount);
	accountRepository.save(ac);
	operationRepository.save(new Deposit(amount,  ac, new Date()));
}


	@Override
	public List<Deposit> allDeposits() {
		// TODO Auto-generated method stub
		return depositRepository.findAll();
	}

	@Override
	public List<Withdrawal> allWithdrals() {
		// TODO Auto-generated method stub
		return withdrawalRepository.findAll();
	}

	@Override
	public List<Transfert> allTransferts() {
		// TODO Auto-generated method stub
		return transfertRepository.findAll();
	}

	
	
	
	
	
	@Override
	public List<Deposit> allDeposits(String accountNumber) {
		// TODO Auto-generated method stub
		return depositRepository.AllDepositByAccountNumber(accountNumber);
	}

	@Override
	public List<Withdrawal> allWithdrals(String accountNumber) {
		// TODO Auto-generated method stub
		return withdrawalRepository.AllWithdrawalByAccountNumber(accountNumber);
	}

	@Override
	public List<Transfert> allTransferts(String accountNumber) {
		// TODO Auto-generated method stub
		return transfertRepository.AllTransfertByAccountNumber(accountNumber);
	}

	@Override
	public void addAccount(Account account) {
accountRepository.save(account);
		
	}

	@Override
	public SavingsAccount findASaving(String number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckingAccount findCheking(String number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccount(String number) {
		Account account = null;
Optional<Account> optional=accountRepository.findById(number);
if(optional.isEmpty())
	throw new RuntimeException("Account not found");
if(optional.isPresent())
	account=optional.get();
		return account;
	}

	@Override
	public List<Account> allAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public List<SavingsAccount> allSavings() {
		// TODO Auto-generated method stub
		return saving_AccountRepository.findAll();
	}

	@Override
	public List<CheckingAccount> allCheckings() {
		// TODO Auto-generated method stub
		return checking_AccountRepository.findAll();
	}

	@Override
	public void desactiverAccount(String account) {
		Account ac=findAccount(account);
		ac.setActive(false);
		accountRepository.save(ac);		
	}

	@Override
	public void reactiverAccount(String account) {
		Account ac=findAccount(account);
		ac.setActive(true);
		accountRepository.save(ac);
		
	}





}
