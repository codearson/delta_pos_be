package com.pos_main.DaoImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
	public PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
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
		
		// Add status filter if provided
		if (status != null) {
			criteria.add(Restrictions.eq("isActive", status));
		}
		
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
	
	@Override
	@Transactional
	public Double getTotalBanking() {
		log.info("BankingDaoImpl.getTotalBanking() invoked");
		Double totalAmount = 0.0;
		int count = 0;
		
		try {
			// Get all banking records ordered by ID
			Criteria criteria = getCurrentSession().createCriteria(Banking.class);
			criteria.addOrder(Order.asc("id"));
			List<Banking> allBankingList = criteria.list();
			
			if (allBankingList != null && !allBankingList.isEmpty()) {
				// Check if any record has generatedDateTime
				boolean hasGeneratedDateTime = allBankingList.stream()
						.anyMatch(banking -> banking.getGeneratedDateTime() != null);
				
				if (hasGeneratedDateTime) {
					// Find the last record with generatedDateTime
					Banking lastGeneratedRecord = null;
					int lastGeneratedIndex = -1;
					
					for (int i = 0; i < allBankingList.size(); i++) {
						Banking banking = allBankingList.get(i);
						if (banking.getGeneratedDateTime() != null) {
							lastGeneratedRecord = banking;
							lastGeneratedIndex = i;
						}
					}
					
					// Sum amounts from records after the last generatedDateTime
					for (int i = lastGeneratedIndex + 1; i < allBankingList.size(); i++) {
						Banking banking = allBankingList.get(i);
						if (banking.getAmount() != null) {
							totalAmount += banking.getAmount();
							count++;
						}
					}
					
					log.info("Found last generatedDateTime at ID: {}, summing {} records after this", 
							lastGeneratedRecord.getId(), count);
				} else {
					// Sum all amounts if no record has generatedDateTime
					for (Banking banking : allBankingList) {
						if (banking.getAmount() != null) {
							totalAmount += banking.getAmount();
							count++;
						}
					}
					log.info("No generatedDateTime found, summing all {} records", count);
				}
			}
			
			log.info("Total amount: {}, Count of records summed: {}", totalAmount, count);
			
		} catch (Exception e) {
			log.error("Exception occurs while calculating total banking amount.", e);
		}
		
		return totalAmount;
	}

	@Override
	@Transactional
	public Integer getBankingCount(LocalDateTime startDate, LocalDateTime endDate) {
		log.info("BankingDaoImpl.getBankingCount() invoked for period: {} to {}", startDate, endDate);
		try {
			Criteria criteria = getCurrentSession().createCriteria(Banking.class);
			criteria.add(Restrictions.between("dateTime", startDate, endDate));
			criteria.add(Restrictions.eq("isActive", true));
			return criteria.list().size();
		} catch (Exception e) {
			log.error("Error counting banking transactions: {}", e.getMessage(), e);
			return 0;
		}
	}

}


