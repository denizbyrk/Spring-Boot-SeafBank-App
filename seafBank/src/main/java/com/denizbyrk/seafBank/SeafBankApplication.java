package com.denizbyrk.seafBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SeafBankApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SeafBankApplication.class, args);
	}
}