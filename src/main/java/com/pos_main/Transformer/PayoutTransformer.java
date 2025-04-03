package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.Payout;
import com.pos_main.Dto.PayoutDto;

@Component
public class PayoutTransformer implements BaseTransformer<Payout, PayoutDto>{

	@Autowired
	UserTransfomer userTransformer;
	
	@Override
	public PayoutDto transform(Payout payout) {
		PayoutDto payoutDto = null;
		if (payout != null) {
			payoutDto = new PayoutDto();
			payoutDto.setId(payout.getId());
			payoutDto.setAmount(payout.getAmount());
			payoutDto.setType(payout.getType());
			payoutDto.setDateTime(payout.getDateTime());
			payoutDto.setIsActive(payout.getIsActive());
			if (payout.getUser() != null) {
				payoutDto.setUserDto(userTransformer.transform(payout.getUser()));
			}
		}
		return payoutDto;
	}

	@Override
	public Payout reverseTransform(PayoutDto payoutDto) {
		Payout payout = null;
		if (payoutDto != null) {
			payout = new Payout();
			payout.setId(payoutDto.getId());
			payout.setAmount(payoutDto.getAmount());
			payout.setType(payoutDto.getType());
			payout.setDateTime(payoutDto.getDateTime());
			payout.setIsActive(payoutDto.getIsActive());
			if (payoutDto.getUserDto() != null) {
				payout.setUser(userTransformer.reverseTransform(payoutDto.getUserDto()));
			}
		}
		return payout;
	}
}
