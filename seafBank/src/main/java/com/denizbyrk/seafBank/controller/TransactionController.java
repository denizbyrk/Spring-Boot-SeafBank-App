package com.denizbyrk.seafBank.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denizbyrk.seafBank.DTO.TransactionDTO;
import com.denizbyrk.seafBank.DTO.TransferRequest;
import com.denizbyrk.seafBank.model.Transaction;
import com.denizbyrk.seafBank.repository.TransactionRepository;
import com.denizbyrk.seafBank.security.CustomUserDetails;
import com.denizbyrk.seafBank.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionService transactionService, TransactionRepository transactionRepository) {
    	
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/transfer")
    public TransactionDTO transfer(@RequestBody TransferRequest request) {
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long currentUserId = userDetails.getId();
    	
        Transaction tx = transactionService.transfer(request, currentUserId);
        
        return TransactionDTO.fromEntity(tx);
    }

    @GetMapping("/myTransactions")
    public List<TransactionDTO> getMyTransactions() {
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long currentUserId = userDetails.getId();

        return transactionRepository.findByFromAccount_User_IdOrToAccount_User_Id(currentUserId, currentUserId)
                .stream()
                .map(TransactionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
    	
        return transactionRepository.findAll()
                .stream()
                .map(TransactionDTO::fromEntity)
                .collect(Collectors.toList());
    }
}