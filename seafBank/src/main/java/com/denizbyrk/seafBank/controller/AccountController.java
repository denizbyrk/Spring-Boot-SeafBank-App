package com.denizbyrk.seafBank.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denizbyrk.seafBank.DTO.AccountDTO;
import com.denizbyrk.seafBank.DTO.OwnAccountDTO;
import com.denizbyrk.seafBank.model.AccountType;
import com.denizbyrk.seafBank.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
    	
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public AccountDTO createAccount(@RequestParam AccountType type,
    							 @RequestParam(defaultValue = "0.0") double interestRate ) {
    	
        return accountService.createAccount(type, interestRate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
    	
        List<AccountDTO> accounts = accountService.getAllAccounts()
                .stream()
                .map(AccountDTO::fromEntity)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/myAccount")
    public List<OwnAccountDTO> getMyAccounts() {
    	
        return accountService.getAccountsForCurrentUser()
                .stream()
                .map(OwnAccountDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @PostMapping("/{accountId}/deposit")
    public OwnAccountDTO deposit(@PathVariable Long accountId, @RequestParam Double amount) {
    	
        return OwnAccountDTO.fromEntity(accountService.deposit(accountId, amount));
    }

    @PostMapping("/{accountId}/withdraw")
    public OwnAccountDTO withdraw(@PathVariable Long accountId, @RequestParam Double amount) {
    	
        return OwnAccountDTO.fromEntity(accountService.withdraw(accountId, amount));
    }
}