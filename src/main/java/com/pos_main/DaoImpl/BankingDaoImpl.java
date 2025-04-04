package com.pos_main.DaoImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.BankingDao;
import com.pos_main.Domain.Banking;
import com.pos_main.Dto.BankingDto;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.BankingTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BankingDaoImpl.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Apr 4, 2025.
 * @version 1.0
 **/

@Slf4j
@Repository
public class BankingDaoImpl extends BaseDaoImpl<Banking> implements BankingDao{
	
	@Autowired
	BankingTransformer bankingTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	public BankingDto save(BankingDto bankingDto) {
		log.info("BankingDaoImpl.save() invoked.");
		Banking banking = bankingTransformer.reverseTransform(bankingDto);
		Banking saveBanking= saveOrUpdate(banking);
		return bankingTransformer.transform(saveBanking);
	}

	@Override
	@Transactional
	public PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("BankingDaoImpl.getAllPage()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<Banking> allBankingList = null;
		int recordCount = 0;
		String countString = "SELECT COUNT(*) FROM banking";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(Banking.class, "banking");
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allBankingList = criteria.list();
		recordCount = allBankingList.size();
		if (allBankingList != null && !allBankingList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allBankingList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allBankingList.stream().map(Invoice -> {
				return bankingTransformer.transform(Invoice);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}

}


