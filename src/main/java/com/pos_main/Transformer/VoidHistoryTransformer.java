package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.VoidHistory;
import com.pos_main.Dto.VoidHistoryDto;

/**
 * Title: VoidHistoryTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 5, 2025
 * @version 1.0
 **/
@Component
public class VoidHistoryTransformer implements BaseTransformer<VoidHistory, VoidHistoryDto> {
	
	@Autowired
	UserTransfomer userTransformer;

	@Override
	public VoidHistoryDto transform(VoidHistory voidHistory) {
		VoidHistoryDto voidHistoryDto = null;
		if (voidHistory != null) {
			voidHistoryDto = new VoidHistoryDto();
			voidHistoryDto.setId(voidHistory.getId());
			voidHistoryDto.setItemName(voidHistory.getItemName());
			voidHistoryDto.setQuantity(voidHistory.getQuantity());
			voidHistoryDto.setPrice(voidHistory.getPrice());
			voidHistoryDto.setTotal(voidHistory.getTotal());
			voidHistoryDto.setDateTime(voidHistory.getDateTime());
			if (voidHistory.getUser() != null) {
				voidHistoryDto.setUserDto(userTransformer.transform(voidHistory.getUser()));
			}
		}
		return voidHistoryDto;
	}
	
	@Override
	public VoidHistory reverseTransform(VoidHistoryDto voidHistoryDto) {
		VoidHistory voidHistory = null;
		if (voidHistoryDto != null) {
			voidHistory = new VoidHistory();
			voidHistory.setId(voidHistoryDto.getId());
			voidHistory.setItemName(voidHistoryDto.getItemName());
			voidHistory.setQuantity(voidHistoryDto.getQuantity());
			voidHistory.setPrice(voidHistoryDto.getPrice());
			voidHistory.setTotal(voidHistoryDto.getTotal());
			voidHistory.setDateTime(voidHistoryDto.getDateTime());
			if (voidHistoryDto.getUserDto() != null) {
				voidHistory.setUser(userTransformer.reverseTransform(voidHistoryDto.getUserDto()));
			}
		}
		return voidHistory;
	}
	
}
