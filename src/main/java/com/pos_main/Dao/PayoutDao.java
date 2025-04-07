package com.pos_main.Dao;

import java.util.List;
import java.util.Map;

import com.pos_main.Domain.Payout;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.PayoutDto;

/**
 * Title: BranchDao.java. Company: www.codearson.com Copyright: Copyright (c)
 * 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

public interface PayoutDao extends BaseDao<Payout> {
	
	List<PayoutDto> getAllPayout();
	
	PaginatedResponseDto getAllPagePayout(int pageNumber, int pageSize, Map<String, String> searchParams);
	
	PayoutDto save (PayoutDto payoutDto);
	
	PayoutDto updatePayout(PayoutDto payoutDto);

	PayoutDto checkPayoutAvailability(Integer payoutId);





}
