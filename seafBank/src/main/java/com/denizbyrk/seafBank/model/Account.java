package com.denizbyrk.seafBank.model;

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
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Double balance;

    private double interestRate = 0.0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    public Account() { }

    public Account(Long id, AccountType accountType, Double balance, double interestRate, User user) {

		this.id = id;
		this.accountType = accountType;
		this.balance = balance;
		this.interestRate = interestRate;
		this.user = user;
	}

	public Long getId() {
    	
    	return id;
    }

    public AccountType getAccountType() {
    	
    	return accountType;
    }
    
    public void setAccountType(AccountType accountType) {
    	
    	this.accountType = accountType;
    }

    public Double getBalance() {
    	
    	return balance;
    }
    
    public void setBalance(Double balance) {
    	
    	this.balance = balance;
    }

    public double getInterestRate() {
    	
    	return interestRate;
    }
    
    public void setInterestRate(double interestRate) {
    	
    	this.interestRate = interestRate;
    }

    public User getUser() {
    	
    	return user;
    }
    
    public void setUser(User user) {
    	
    	this.user = user;
    }
}