package com.denizbyrk.seafBank.DTO;

import com.denizbyrk.seafBank.model.Account;

public class OwnAccountDTO {

    private Long id;
    private String accountType;
    private double balance;
    private double interestRate;

    public static OwnAccountDTO fromEntity(Account account) {
    	
        OwnAccountDTO dto = new OwnAccountDTO();
        dto.setId(account.getId());
        dto.setAccountType(account.getAccountType().name());
        dto.setBalance(account.getBalance());
        dto.setInterestRate(account.getInterestRate());
        
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
}