package com.pos_main.Transformer;

import org.springframework.stereotype.Component;

import com.pos_main.Domain.MinimamBanking;
import com.pos_main.Dto.MinimamBankingDto;


/**
 * Title: MinimamBankingTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 24 Apr 2025
 * @time 23:02:16
 * @version 1.0
 **/

@Component
public class MinimamBankingTransformer implements BaseTransformer<MinimamBanking, MinimamBankingDto>{

	@Override
	public MinimamBankingDto transform(MinimamBanking minimamBanking) {
		MinimamBankingDto minimamBankingDto= null;
		if (minimamBanking != null) {
			minimamBankingDto = new MinimamBankingDto();
			minimamBankingDto.setId(minimamBanking.getId());
			minimamBankingDto.setAmount(minimamBanking.getAmount());
			minimamBankingDto.setIsActive(minimamBanking.getIsActive());
		}
		return minimamBankingDto;
	}

	@Override
	public MinimamBanking reverseTransform(MinimamBankingDto minimamBankingDto) {
		MinimamBanking minimamBanking= null;
		if (minimamBankingDto != null) {
			minimamBanking = new MinimamBanking();
			minimamBanking.setId(minimamBankingDto.getId());
			minimamBanking.setAmount(minimamBankingDto.getAmount());
			minimamBanking.setIsActive(minimamBankingDto.getIsActive());
		}
		return minimamBanking;
	}
}
