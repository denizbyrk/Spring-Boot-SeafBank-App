package com.denizbyrk.seafBank.service;

import com.denizbyrk.seafBank.model.AccountType;

public interface CurrencyConversionService {

	public double convert(double amount, AccountType fromCurrency, AccountType toCurrency);
}