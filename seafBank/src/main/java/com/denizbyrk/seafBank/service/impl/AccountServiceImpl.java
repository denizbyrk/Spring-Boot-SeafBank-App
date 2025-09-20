package com.denizbyrk.seafBank.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.denizbyrk.seafBank.DTO.AccountDTO;
import com.denizbyrk.seafBank.model.Account;
import com.denizbyrk.seafBank.model.AccountType;
import com.denizbyrk.seafBank.model.Transaction;
import com.denizbyrk.seafBank.model.TransactionType;
import com.denizbyrk.seafBank.model.User;
import com.denizbyrk.seafBank.repository.AccountRepository;
import com.denizbyrk.seafBank.repository.TransactionRepository;
import com.denizbyrk.seafBank.repository.UserRepository;
import com.denizbyrk.seafBank.security.CustomUserDetails;
import com.denizbyrk.seafBank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
    	
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }
    
    public List<Account> getAllAccounts() {
    	
        return this.accountRepository.findAll();
    }
    
    private User getCurrentUser() {
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long currentUserId = userDetails.getId();
        
        return this.userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public AccountDTO createAccount(AccountType type, double interestRate) {
    	
        User user = this.getCurrentUser();

        Account account = new Account();
        account.setUser(user);
        account.setAccountType(type);
        account.setBalance(0.0);
        account.setInterestRate(interestRate);

        return AccountDTO.fromEntity(this.accountRepository.save(account));
    }

    public List<Account> getAccountsForCurrentUser() {
    	
    	User user = this.getCurrentUser();
    	
        return this.accountRepository.findByUserId(user.getId());
    }

    public Account deposit(Long accountId, Double amount) {
    	
        Account account = this.accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        
        User currentUser = this.getCurrentUser();

        if (!account.getUser().getId().equals(currentUser.getId())) {
        	
            throw new RuntimeException("You cannot access this account");
        }
        
        account.setBalance(account.getBalance() + amount);
        
        Transaction tx = new Transaction();
        tx.setAmount(amount);
        tx.setType(TransactionType.DEPOSIT);
        tx.setTimestamp(LocalDateTime.now());
        tx.setToAccount(account);
        this.transactionRepository.save(tx);
        
        return this.accountRepository.save(account);
    }

    public Account withdraw(Long accountId, Double amount) {
    	
        Account account = this.accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        User currentUser = this.getCurrentUser();
        
        if (!account.getUser().getId().equals(currentUser.getId())) {
        	
            throw new RuntimeException("You cannot access this account");
        }
        
        if (account.getBalance() < amount) {
        	
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        
        Transaction tx = new Transaction();
        tx.setAmount(amount);
        tx.setType(TransactionType.WITHDRAW);
        tx.setTimestamp(LocalDateTime.now());
        tx.setFromAccount(account);
        this.transactionRepository.save(tx);
        
        return this.accountRepository.save(account);
    }
}