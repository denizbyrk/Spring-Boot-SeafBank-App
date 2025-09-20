package com.denizbyrk.seafBank.service;

import com.denizbyrk.seafBank.DTO.TransferRequest;
import com.denizbyrk.seafBank.model.Transaction;

public interface TransactionService {

	public Transaction transfer(TransferRequest request, Long currentUserId);
}