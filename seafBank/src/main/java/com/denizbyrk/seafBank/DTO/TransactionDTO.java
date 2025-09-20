package com.denizbyrk.seafBank.DTO;

import java.time.LocalDateTime;

import com.denizbyrk.seafBank.model.Transaction;

public class TransactionDTO {

    private Long id;
    private double amount;
    private String type;
    private LocalDateTime timestamp;
    private Long targetAccountId;

    public static TransactionDTO fromEntity(Transaction transaction) {
    	
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setType(transaction.getType().name());
        dto.setTimestamp(transaction.getTimestamp());

        if (transaction.getToAccount() != null) {
        	
            dto.setTargetAccountId(transaction.getToAccount().getId());
            
        } else {
        	
            dto.setTargetAccountId(null);
        }
        
        return dto;
    }

	public Long getId() {
		
		return id;
	}

	public void setId(Long id) {
		
		this.id = id;
	}

	public double getAmount() {
		
		return amount;
	}

	public void setAmount(double amount) {
		
		this.amount = amount;
	}

	public String getType() {
		
		return type;
	}

	public void setType(String type) {
		
		this.type = type;
	}

	public LocalDateTime getTimestamp() {
		
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		
		this.timestamp = timestamp;
	}

	public Long getTargetAccountId() {
		
		return targetAccountId;
	}

	public void setTargetAccountId(Long targetAccountId) {
		
		this.targetAccountId = targetAccountId;
	}
}