package com.denizbyrk.seafBank.service;

import java.util.List;

import com.denizbyrk.seafBank.DTO.AccountDTO;
import com.denizbyrk.seafBank.model.Account;
import com.denizbyrk.seafBank.model.AccountType;

public interface AccountService {

	public List<Account> getAllAccounts();
	
	public AccountDTO createAccount(AccountType type, double interestRate);
	
	public List<Account> getAccountsForCurrentUser();
	
	public Account deposit(Long accountId, Double amount);
	
	public Account withdraw(Long accountId, Double amount);
}