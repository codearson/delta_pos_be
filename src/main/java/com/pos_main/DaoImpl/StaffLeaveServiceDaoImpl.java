package com.pos_main.DaoImpl;

import java.util.List;
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
import com.pos_main.Domain.StaffLeave;
import com.pos_main.Dto.StaffLeaveDto;
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
public class StaffLeaveServiceDaoImpl extends BaseDaoImpl<StaffLeave> implements StaffLeaveServiceDao{

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
        
        List<StaffLeave> taxList = getCurrentSession().createQuery(cq).getResultList();
        return taxList.stream()
            .map(staffLeaveTransformer::transform)
            .collect(Collectors.toList());
    }
	
}
