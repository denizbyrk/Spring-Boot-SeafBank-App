package com.denizbyrk.seafBank.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private Account toAccount;
    
    public Transaction() {}
    	
	public Transaction(Long id, Double amount, TransactionType type, LocalDateTime timestamp, Account fromAccount, Account toAccount) {

		this.id = id;
		this.amount = amount;
		this.type = type;
		this.timestamp = timestamp;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
	}

	public Long getId() {
		
		return id;
	}

	public void setId(Long id) {
		
		this.id = id;
	}

	public Double getAmount() {
		
		return amount;
	}

	public void setAmount(Double amount) {
		
		this.amount = amount;
	}

	public TransactionType getType() {
		
		return type;
	}

	public void setType(TransactionType type) {
		
		this.type = type;
	}

	public LocalDateTime getTimestamp() {
		
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		
		this.timestamp = timestamp;
	}

	public Account getFromAccount() {
		
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		
		this.toAccount = toAccount;
	}
}