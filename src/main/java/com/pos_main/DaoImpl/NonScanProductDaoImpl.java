package com.pos_main.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.NonScanProductDao;
import com.pos_main.Domain.NonScanProduct;
import com.pos_main.Dto.NonScanProductDto;
import com.pos_main.Transformer.NonScanProductTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: NonScanProductDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 19:25:07
 * @version 1.0
 **/

@Slf4j
@Repository
public class NonScanProductDaoImpl extends BaseDaoImpl<NonScanProduct> implements NonScanProductDao {

    @Autowired
    NonScanProductTransformer nonScanProductTransformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public List<NonScanProductDto> getAll() {
        log.info("NonScanProductDaoImpl.getAll() invoked");
        Criteria criteria = getCurrentSession().createCriteria(NonScanProduct.class, "nonScanProduct");
        criteria.addOrder(Order.asc("id"));
        List<NonScanProduct> nonScanProductList = criteria.list();
        List<NonScanProductDto> nonScanProductDtoList = new ArrayList<>();
        if (nonScanProductList != null && !nonScanProductList.isEmpty()) {
            for (NonScanProduct nonScanProduct : nonScanProductList) {
                nonScanProductDtoList.add(nonScanProductTransformer.transform(nonScanProduct));
            }
        }
        return nonScanProductDtoList;
    }

    @Override
    @Transactional
    public List<NonScanProductDto> getAllPageNonScanProduct(int pageNumber, int pageSize) {
        log.info("NonScanProductDaoImpl.getAllPageNonScanProduct() invoked");
        String countString = "SELECT COUNT(*) FROM non_scan_product";
        int count = jdbcTemplate.queryForObject(countString, Integer.class);
        
        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(NonScanProduct.class, "nonScanProduct");
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        List<NonScanProduct> nonScanProductList = criteria.list();
        
        return nonScanProductList.stream()
                .map(nonScanProductTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NonScanProductDto save(NonScanProductDto nonScanProductDto) {
        log.info("NonScanProductDaoImpl.save() invoked");
        NonScanProduct nonScanProduct = nonScanProductTransformer.reverseTransform(nonScanProductDto);
        NonScanProduct savedNonScanProduct = saveOrUpdate(nonScanProduct);
        return nonScanProductTransformer.transform(savedNonScanProduct);
    }

    @Override
    @Transactional
    public NonScanProductDto update(NonScanProductDto nonScanProductDto) {
        log.info("NonScanProductDaoImpl.update() invoked");
        NonScanProduct nonScanProduct = nonScanProductTransformer.reverseTransform(nonScanProductDto);
        NonScanProduct updatedNonScanProduct = saveOrUpdate(nonScanProduct);
        return nonScanProductTransformer.transform(updatedNonScanProduct);
    }

    @Override
    @Transactional
    public List<NonScanProductDto> getAllByName(String nonScanProduct) {
        log.info("NonScanProductDaoImpl.getAllByName() invoked");
        Criteria criteria = getCurrentSession().createCriteria(NonScanProduct.class, "nonScanProduct");
        criteria.add(Restrictions.eq("nonScanProduct", nonScanProduct));
        criteria.add(Restrictions.eq("isActive", true));
        
        List<NonScanProduct> nonScanProductList = criteria.list();
        return nonScanProductList.stream()
                .map(nonScanProductTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NonScanProductDto checkAvailability(Integer id) {
        log.info("NonScanProductDaoImpl.checkAvailability() invoked");
        Criteria criteria = getCurrentSession().createCriteria(NonScanProduct.class, "nonScanProduct");
        criteria.add(Restrictions.eq("id", id));
        NonScanProduct nonScanProduct = (NonScanProduct) criteria.uniqueResult();
        return nonScanProduct != null ? nonScanProductTransformer.transform(nonScanProduct) : null;
    }
}