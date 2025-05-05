package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.Banking;
import com.pos_main.Dto.BankingDto;

/**
 * Title: BankingTransformer.java. Company: www.codearson.com | Copyright:
 * Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Apr 4, 2025.
 * @version 1.0
 **/

@Component
public class BankingTransformer implements BaseTransformer<Banking, BankingDto> {

	@Autowired
	UserTransfomer userTransfomer;

	@Override
	public BankingDto transform(Banking banking) {
		BankingDto bankingDto = null;
		if (banking != null) {
			bankingDto = new BankingDto();
			bankingDto.setId(banking.getId());
			bankingDto.setAmount(banking.getAmount());
			bankingDto.setDateTime(banking.getDateTime());
			bankingDto.setIsActive(banking.getIsActive());
			if (banking.getUser() != null) {
				bankingDto.setUserDto(userTransfomer.transform(banking.getUser()));
			}
			bankingDto.setGeneratedDateTime(banking.getGeneratedDateTime());
		}
		return bankingDto;
	}

	@Override
	public Banking reverseTransform(BankingDto bankingDto) {
		Banking banking = null;
		if (bankingDto != null) {
			banking = new Banking();
			banking.setId(bankingDto.getId());
			banking.setAmount(bankingDto.getAmount());
			banking.setDateTime(bankingDto.getDateTime());
			banking.setIsActive(bankingDto.getIsActive());
			if (bankingDto.getUserDto() != null) {
				banking.setUser(userTransfomer.reverseTransform(bankingDto.getUserDto()));
			}
			banking.setGeneratedDateTime(bankingDto.getGeneratedDateTime());
		}
		return banking;
	}

}
