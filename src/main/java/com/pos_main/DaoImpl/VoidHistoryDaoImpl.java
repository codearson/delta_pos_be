package com.pos_main.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.VoidHistoryDao;
import com.pos_main.Domain.VoidHistory;
import com.pos_main.Dto.VoidHistoryDto;
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
	
}
