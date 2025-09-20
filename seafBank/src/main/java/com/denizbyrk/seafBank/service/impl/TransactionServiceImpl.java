package com.denizbyrk.seafBank.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.denizbyrk.seafBank.DTO.TransferRequest;
import com.denizbyrk.seafBank.model.Account;
import com.denizbyrk.seafBank.model.Transaction;
import com.denizbyrk.seafBank.model.TransactionType;
import com.denizbyrk.seafBank.repository.AccountRepository;
import com.denizbyrk.seafBank.repository.TransactionRepository;
import com.denizbyrk.seafBank.service.CurrencyConversionService;
import com.denizbyrk.seafBank.service.TransactionService;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CurrencyConversionService currencyConversionService;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, CurrencyConversionService currencyConversionService) {
    	
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.currencyConversionService = currencyConversionService;
    }

    @Transactional
    public Transaction transfer(TransferRequest request, Long currentUserId) {
    	
        Account from = this.accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        Account to = this.accountRepository.findById(request.getToAccountId())
                .orElseThrow(() -> new RuntimeException("Destination account not found"));
        
        if (!from.getUser().getId().equals(currentUserId)) {
        	
            throw new RuntimeException("You can only transfer money from your own accounts.");
        }

        if (from.getBalance() < request.getAmount()) {
        	
            throw new RuntimeException("Insufficient balance.");
        }

        from.setBalance(from.getBalance() - request.getAmount());
        
        double convertedAmount = request.getAmount();
        if (!from.getAccountType().equals(to.getAccountType())) {
        	
            convertedAmount = this.currencyConversionService.convert(
                    request.getAmount(),
                    from.getAccountType(),
                    to.getAccountType()
            );
        }
        
        to.setBalance(to.getBalance() + convertedAmount);

        this.accountRepository.save(from);
        this.accountRepository.save(to);
        
        Transaction tx = new Transaction();
        tx.setAmount(request.getAmount());
        tx.setType(TransactionType.TRANSFER);
        tx.setTimestamp(LocalDateTime.now());
        tx.setFromAccount(from);
        tx.setToAccount(to);
        
        return this.transactionRepository.save(tx);
    }
}