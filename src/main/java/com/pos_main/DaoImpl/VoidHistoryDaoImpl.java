package com.pos_main.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.VoidHistoryDao;
import com.pos_main.Domain.VoidHistory;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.VoidHistoryDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.VoidHistoryTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: VoidHistoryDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 5, 2025
 * @version 1.0
 **/

@Slf4j
@Repository
public class VoidHistoryDaoImpl extends BaseDaoImpl<VoidHistory> implements VoidHistoryDao {

	@Autowired
	VoidHistoryTransformer voidHistoryTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	public VoidHistoryDto save(VoidHistoryDto voidHistoryDto) {
		log.info("VoidHistoryDaoImpl.save() invoked.");
		VoidHistory voidHistory = voidHistoryTransformer.reverseTransform(voidHistoryDto);
		VoidHistory saveVoidHistory= saveOrUpdate(voidHistory);
		return voidHistoryTransformer.transform(saveVoidHistory);
	}
	
	@Override
	@Transactional
	public List<VoidHistoryDto> getAll() {
		log.info("VoidHistoryDaoImpl.gellAll() invoked");
		Criteria criteria = getCurrentSession().createCriteria(VoidHistory.class, "voidHistory");
		List<VoidHistoryDto> voidHistoryDtoList = null;
		List<VoidHistory> voidHistoryList = (List<VoidHistory>) criteria.list();
		if (voidHistoryList != null && !voidHistoryList.isEmpty()) {
			voidHistoryDtoList = new ArrayList<>();
			for (VoidHistory voidHistory : voidHistoryList) {
				voidHistoryDtoList.add(voidHistoryTransformer.transform(voidHistory));
			}
		}
		return voidHistoryDtoList;
	}
	
	@Override
	@Transactional
	public PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("VoidHistoryDaoImpl.getAllPage()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<VoidHistory> allVoidHistoryList = null;
		int recordCount = 0;
		String countString = "SELECT COUNT(*) FROM void_history";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(VoidHistory.class, "voidHistory");
				
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allVoidHistoryList = criteria.list();
		recordCount = allVoidHistoryList.size();
		if (allVoidHistoryList != null && !allVoidHistoryList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allVoidHistoryList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allVoidHistoryList.stream().map(Invoice -> {
				return voidHistoryTransformer.transform(Invoice);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}
	
	@Override
	@Transactional
	public PaginatedResponseDto getAllPageByUserId(int pageNumber, int pageSize, Integer userId, Map<String, String> searchParams) {
		log.info("VoidHistoryDaoImpl.getAllPageByUserId() invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<VoidHistory> allVoidHistoryList = null;
		
		String countString = "SELECT COUNT(*) FROM void_history WHERE user_id = ?";
		int count = jdbcTemplate.queryForObject(countString, Integer.class, userId);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(VoidHistory.class, "voidHistory");
		criteria.createAlias("User", "user");
		criteria.add(Restrictions.eq("user.id", userId));
				
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allVoidHistoryList = criteria.list();
		
		if (allVoidHistoryList != null && !allVoidHistoryList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allVoidHistoryList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allVoidHistoryList.stream().map(voidHistory -> {
				return voidHistoryTransformer.transform(voidHistory);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}
	
	@Override
	@Transactional
	public PaginatedResponseDto getAllPageByDate(int pageNumber, int pageSize, String date, Map<String, String> searchParams) {
		log.info("VoidHistoryDaoImpl.getAllPageByDate() invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<VoidHistory> allVoidHistoryList = null;
		
		String countString = "SELECT COUNT(*) FROM void_history WHERE DATE(date_time) = ?";
		int count = jdbcTemplate.queryForObject(countString, Integer.class, date);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(VoidHistory.class, "voidHistory");
		criteria.add(Restrictions.sqlRestriction("DATE(this_.date_time) = ?", date, org.hibernate.type.StringType.INSTANCE));
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allVoidHistoryList = criteria.list();
		
		if (allVoidHistoryList != null && !allVoidHistoryList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allVoidHistoryList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allVoidHistoryList.stream().map(voidHistory -> {
				return voidHistoryTransformer.transform(voidHistory);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}
	
}
