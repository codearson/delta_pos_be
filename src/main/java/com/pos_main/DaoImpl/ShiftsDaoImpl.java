package com.pos_main.DaoImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.ShiftsDao;
import com.pos_main.Domain.Shifts;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ShiftsDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.ShiftsTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ShiftsDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 19:52:10
 * @version 1.0
 **/

@Slf4j
@Repository
public class ShiftsDaoImpl extends BaseDaoImpl<Shifts> implements ShiftsDao {
	
	@Autowired
	ShiftsTransformer shiftsTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public ShiftsDto save(ShiftsDto shiftsDto) {
		log.info("ShiftsDaoImpl.save() invoked.");
		Shifts shifts = shiftsTransformer.reverseTransform(shiftsDto);
		Shifts saveShifts = saveOrUpdate(shifts);
		return shiftsTransformer.transform(saveShifts);
	}
	
	@Override
	@Transactional
	public ShiftsDto update(ShiftsDto shiftsDto) {
		log.info("ShiftsDaoImpl.save() invoked.");
		Shifts shifts = shiftsTransformer.reverseTransform(shiftsDto);
		Shifts updateShifts = saveOrUpdate(shifts);
		return shiftsTransformer.transform(updateShifts);
	}
	
	@Override
	@Transactional
	public ShiftsDto checkShiftAvailability(Integer shiftId) {
		Criteria criteria = getCurrentSession().createCriteria(Shifts.class, "Shifts");
		criteria.add(Restrictions.eq("Shifts.id", shiftId));
		Shifts shifts = (Shifts) criteria.uniqueResult();
		ShiftsDto shiftsDto = null;
		if (shifts != null) {
			shiftsDto = shiftsTransformer.transform(shifts);
		}
		return shiftsDto;
	}
	
	@Override
	@Transactional
	public PaginatedResponseDto getAllPageShifts(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("ShiftsDaoImpl.getAll()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<Shifts> allShiftsList = null;
		int recordCount = 0;
		String countString = "SELECT COUNT(*) FROM shifts";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(Shifts.class, "shifts");
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allShiftsList = criteria.list();
		recordCount = allShiftsList.size();
		if (allShiftsList != null && !allShiftsList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allShiftsList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allShiftsList.stream().map(Invoice -> {
				return shiftsTransformer.transform(Invoice);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}

}
