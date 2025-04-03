package com.pos_main.Service;

import com.pos_main.Dto.PayoutDto;
import com.pos_main.Dto.ResponseDto;

/**
 * Title: BranchService.java. Company: www.codearson.com Copyright: Copyright (c)
 * 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

public interface PayoutService {

	ResponseDto getAllPayout();
	
	public ResponseDto save(PayoutDto payoutDto);
	
	ResponseDto updatePayout(PayoutDto payoutDto);
	
	public ResponseDto updatePayoutStatus(Integer id, Boolean status);
	

}