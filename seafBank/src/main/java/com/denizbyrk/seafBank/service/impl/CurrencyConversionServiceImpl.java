package com.denizbyrk.seafBank.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.denizbyrk.seafBank.model.AccountType;
import com.denizbyrk.seafBank.service.CurrencyConversionService;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

	//as of september 2025
    private static final Map<AccountType, Double> exchangeRates = Map.of(
            AccountType.USD, 1.0,
            AccountType.EUR, 0.84,
            AccountType.TRY, 41.29
    );

    public double convert(double amount, AccountType fromCurrency, AccountType toCurrency) {
    	
        Double fromRate = exchangeRates.get(fromCurrency);
        Double toRate = exchangeRates.get(toCurrency);

        if (fromRate == null || toRate == null) {
        	
            throw new RuntimeException("Unsupported currency");
        }

        double amountInUSD = amount / fromRate;
        return amountInUSD * toRate;
    }
}