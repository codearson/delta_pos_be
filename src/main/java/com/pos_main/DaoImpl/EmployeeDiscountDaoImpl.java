package com.pos_main.DaoImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.EmployeeDiscountDao;
import com.pos_main.Domain.EmployeeDiscount;
import com.pos_main.Dto.EmployeeDiscountDto;
import com.pos_main.Transformer.EmployeeDiscountTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: EmployeeDiscountDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 22:57:15
 * @version 1.0
 **/

@Slf4j
@Repository
public class EmployeeDiscountDaoImpl extends BaseDaoImpl<EmployeeDiscount> implements EmployeeDiscountDao {
    
    @Autowired
    EmployeeDiscountTransformer employeeDiscountTransformer;
    
    @Autowired
	private JdbcTemplate jdbcTemplate;
    
    @Override
    @Transactional
    public EmployeeDiscountDto save(EmployeeDiscountDto employeeDiscountDto) {
        log.info("EmployeeDiscountDaoImpl.save() invoked.");
        EmployeeDiscount discount = employeeDiscountTransformer.reverseTransform(employeeDiscountDto);
        EmployeeDiscount savedDiscount = saveOrUpdate(discount);
        return employeeDiscountTransformer.transform(savedDiscount);
    }
    
    @Override
    @Transactional
    public EmployeeDiscountDto update(EmployeeDiscountDto employeeDiscountDto) {
        log.info("EmployeeDiscountDaoImpl.update() invoked");
        EmployeeDiscount discount = employeeDiscountTransformer.reverseTransform(employeeDiscountDto);
        EmployeeDiscount updatedDiscount = saveOrUpdate(discount);
        return employeeDiscountTransformer.transform(updatedDiscount);
    }
    
    @Override
    @Transactional
    public List<EmployeeDiscountDto> getAll() {
        log.info("EmployeeDiscountDaoImpl.getAll() invoked");
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<EmployeeDiscount> cq = cb.createQuery(EmployeeDiscount.class);
        Root<EmployeeDiscount> root = cq.from(EmployeeDiscount.class);
        
        cq.select(root);
        
        List<EmployeeDiscount> discountList = getCurrentSession().createQuery(cq).getResultList();
        return discountList.stream()
            .map(employeeDiscountTransformer::transform)
            .collect(Collectors.toList());
    }
}