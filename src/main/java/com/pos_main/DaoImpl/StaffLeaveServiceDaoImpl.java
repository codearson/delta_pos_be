package com.pos_main.DaoImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.StaffLeaveServiceDao;
import com.pos_main.Domain.Customer;
import com.pos_main.Domain.StaffLeave;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.StaffLeaveDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.StaffLeaveTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StaffLeaveServiceDaoImpl.java. Company: www.codearson.com | Copyright:
 * Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Mar 26, 2025.
 * @version 1.0
 **/

@Slf4j
@Repository
public class StaffLeaveServiceDaoImpl extends BaseDaoImpl<StaffLeave> implements StaffLeaveServiceDao {

	@Autowired
	StaffLeaveTransformer staffLeaveTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public StaffLeaveDto save(StaffLeaveDto staffLeaveDto) {
		log.info("StaffLeaveServiceDaoImpl.save() invoked.");
		StaffLeave staffLeave = staffLeaveTransformer.reverseTransform(staffLeaveDto);
		StaffLeave saveStaffLeave = saveOrUpdate(staffLeave);
		return staffLeaveTransformer.transform(saveStaffLeave);
	}

	@Override
	@Transactional
	public StaffLeaveDto update(StaffLeaveDto staffLeaveDto) {
		log.info("StaffLeaveServiceDaoImpl.update() invoked");
		StaffLeave staffLeave = staffLeaveTransformer.reverseTransform(staffLeaveDto);
		StaffLeave updatedStaffLeave = saveOrUpdate(staffLeave);
		return staffLeaveTransformer.transform(updatedStaffLeave);
	}

	@Override
	@Transactional
	public StaffLeaveDto checkAvailability(Integer id) {
		Criteria criteria = getCurrentSession().createCriteria(StaffLeave.class, "staffLeave");
		criteria.add(Restrictions.eq("staffLeave.id", id));
		StaffLeave staffLeave = (StaffLeave) criteria.uniqueResult();
		StaffLeaveDto staffLeaveDto = null;

		if (staffLeave != null) {
			if (staffLeave.getEndDate() != null && staffLeave.getEndDate().isBefore(LocalDate.now())) {
				staffLeave.setIsActive(false);
				saveOrUpdate(staffLeave);
			}
			staffLeaveDto = staffLeaveTransformer.transform(staffLeave);
		}
		return staffLeaveDto;
	}

    @Override
    @Transactional
    public List<StaffLeaveDto> getAll() {
        log.info("StaffLeaveServiceDaoImpl.getAll() invoked");
 
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<StaffLeave> cq = cb.createQuery(StaffLeave.class);
        Root<StaffLeave> root = cq.from(StaffLeave.class);
 
        cq.select(root);
 
        List<StaffLeave> staffLeaveList = getCurrentSession().createQuery(cq).getResultList();
 
        staffLeaveList.forEach(staffLeave -> {
            if (staffLeave.getEndDate() != null && staffLeave.getEndDate().isBefore(LocalDate.now())) {
                staffLeave.setIsActive(false);
                saveOrUpdate(staffLeave);
            }
        });
 
        return staffLeaveList.stream()
                .map(staffLeaveTransformer::transform)
                .collect(Collectors.toList());
    }
    
	@Override
	@Transactional
	public PaginatedResponseDto getAllPageStaffLeave(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
		log.info("StaffLeaveDaoImpl.getAll()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<StaffLeave> allStaffLeaveList = null;
		int recordCount = 0;
		
		// Create a criteria for counting records with the same filters
		Criteria countCriteria = getCurrentSession().createCriteria(StaffLeave.class, "staffLeave");
		
		// Add status filter if provided
		if (status != null) {
			countCriteria.add(org.hibernate.criterion.Restrictions.eq("isActive", status));
		}
		
		// Get the count of records that match the filter
		int count = countCriteria.list().size();
		
		// If no records match the filter, return empty paginated response
		if (count == 0) {
			return HttpReqRespUtils.paginatedResponseMapper(null, pageNumber, pageSize, 0);
		}

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(StaffLeave.class, "staffLeave");
		
		// Add status filter if provided
		if (status != null) {
			criteria.add(org.hibernate.criterion.Restrictions.eq("isActive", status));
		}
		
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allStaffLeaveList = criteria.list();
		recordCount = allStaffLeaveList.size();
		if (allStaffLeaveList != null && !allStaffLeaveList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allStaffLeaveList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allStaffLeaveList.stream().map(Invoice -> {
				return staffLeaveTransformer.transform(Invoice);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}
}