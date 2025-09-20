package com.denizbyrk.seafBank.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.denizbyrk.seafBank.model.Account;
import com.denizbyrk.seafBank.model.Transaction;
import com.denizbyrk.seafBank.model.TransactionType;
import com.denizbyrk.seafBank.repository.AccountRepository;
import com.denizbyrk.seafBank.repository.TransactionRepository;
import com.denizbyrk.seafBank.service.InterestService;

@Service
public class InterestServiceImpl implements InterestService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public InterestServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
    	
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    //run every 60 seconds
    @Scheduled(fixedRate = 60000)
    public void applyInterest() {
    	
        List<Account> accounts = this.accountRepository.findAll();

        for (Account account : accounts) {
        	
            double interest = (account.getBalance() * account.getInterestRate()) / 100;
            interest = Math.round(interest * 100.0) / 100.0;

            if (interest > 0) {

            	double newBalance = account.getBalance() + interest;
            	newBalance = Math.round(newBalance * 100.0) / 100.0;
                account.setBalance(newBalance);

                Transaction tx = new Transaction();
                tx.setAmount(interest);
                tx.setType(TransactionType.INTEREST);
                tx.setToAccount(account);
                tx.setTimestamp(LocalDateTime.now());

                this.transactionRepository.save(tx);
            }
        }

        this.accountRepository.saveAll(accounts);
    }
}