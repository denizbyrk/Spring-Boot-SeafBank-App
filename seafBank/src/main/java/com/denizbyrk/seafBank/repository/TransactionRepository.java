package com.denizbyrk.seafBank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denizbyrk.seafBank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
    List<Transaction> findByFromAccount_User_IdOrToAccount_User_Id(Long fromUserId, Long toUserId);
}