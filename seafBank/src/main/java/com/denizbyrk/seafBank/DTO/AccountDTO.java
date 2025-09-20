package com.denizbyrk.seafBank.DTO;

import com.denizbyrk.seafBank.model.Account;

public class AccountDTO {

    private Long id;
    private String accountType;
    private double balance;
    private double interestRate;
    private Long userId;
    private String username;
    private String email;

    public static AccountDTO fromEntity(Account account) {
    	
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setAccountType(account.getAccountType().name());
        dto.setBalance(account.getBalance());
        dto.setInterestRate(account.getInterestRate());
        dto.setUserId(account.getUser().getId());
        dto.setUsername(account.getUser().getUsername());
        dto.setEmail(account.getUser().getEmail());
        return dto;
    }

	public Long getId() {
		
		return id;
	}

	public void setId(Long id) {
		
		this.id = id;
	}

	public String getAccountType() {
		
		return accountType;
	}

	public void setAccountType(String accountType) {
		
		this.accountType = accountType;
	}

	public double getBalance() {
		
		return balance;
	}

	public void setBalance(double balance) {
		
		this.balance = balance;
	}

	public double getInterestRate() {
		
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		
		this.interestRate = interestRate;
	}

	public Long getUserId() {
		
		return userId;
	}

	public void setUserId(Long userId) {
		
		this.userId = userId;
	}

	public String getUsername() {
		
		return username;
	}

	public void setUsername(String username) {
		
		this.username = username;
	}

	public String getEmail() {
		
		return email;
	}

	public void setEmail(String email) {
		
		this.email = email;
	}
}